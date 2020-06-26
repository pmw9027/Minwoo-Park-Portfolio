// Park
// ChildView.cpp : CChildView 클래스의 구현
//

#include "stdafx.h"
#include "MidEx2.h"
#include "ChildView.h"
#include "MainFrm.h"
#include "Shape.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CChildView

CChildView::CChildView(): 
	m_ShapeCount(0), 
	m_isShapeMove(false)
	, m_ShapeSelected(-1)
	, m_isTagMove(false)
{
	m_Shape[m_ShapeCount++] = new CRectangle(0, 0, 250, 220);
	m_Shape[m_ShapeCount++] = new CCircle(0, 0, 100);
	m_Shape[m_ShapeCount++] = new CTriangle(0, 0, 250, 220);
	m_Shape[m_ShapeCount++] = new CSquare(0, 0, 200);	

	m_ResizeTag = new CSquare(0, 0, 10);
}

CChildView::~CChildView()
{
	delete m_ResizeTag;	
	for (int i = 0; i < m_ShapeCount; i++)
		delete m_Shape[i];
}


BEGIN_MESSAGE_MAP(CChildView, CWnd)
	ON_WM_PAINT()
	ON_WM_MOUSEMOVE()
	ON_WM_LBUTTONDOWN()
	ON_WM_LBUTTONUP()
	ON_COMMAND(ID_PEN, &CChildView::OnPen)
	ON_COMMAND(ID_ERASER, &CChildView::OnEraser)
END_MESSAGE_MAP()



// CChildView 메시지 처리기

BOOL CChildView::PreCreateWindow(CREATESTRUCT& cs)
{
	if (!CWnd::PreCreateWindow(cs))
		return FALSE;

	cs.dwExStyle |= WS_EX_CLIENTEDGE;
	cs.style &= ~WS_BORDER;
	cs.lpszClass = AfxRegisterWndClass(CS_HREDRAW | CS_VREDRAW | CS_DBLCLKS,
		::LoadCursor(NULL, IDC_ARROW), reinterpret_cast<HBRUSH>(COLOR_WINDOW + 1), NULL);

	return TRUE;
}

void CChildView::OnPaint()
{
	CPaintDC dc(this); // device context for painting

	for (int i = 0; i < m_ShapeCount; i++)
	{
		CPen penLine(PS_SOLID, 1, m_Shape[i]->m_colorLine);
		CPen *oldPen = dc.SelectObject(&penLine);
		CBrush brush;
		brush.CreateSolidBrush(m_Shape[i]->m_colorBrush);
		CBrush *oldBrush = dc.SelectObject(&brush);

		m_Shape[i]->draw(&dc);

		dc.SelectObject(oldBrush);
		dc.SelectObject(oldPen);
	}
	if (m_ShapeSelected >= 0)
	{
		int sx, sy;
		switch (m_Shape[m_ShapeSelected]->m_type)
		{
		case ST_CIRCLE:
			sx = m_Shape[m_ShapeSelected]->m_cx + ((CCircle*)m_Shape[m_ShapeSelected])->m_radius;
			sy = m_Shape[m_ShapeSelected]->m_cy;
			break;
		case ST_RECTANGLE:
			sx = m_Shape[m_ShapeSelected]->m_cx + ((CRectangle*)m_Shape[m_ShapeSelected])->m_width / 2;
			sy = m_Shape[m_ShapeSelected]->m_cy + ((CRectangle*)m_Shape[m_ShapeSelected])->m_height / 2;
			break;
		case ST_SQUARE:
			sx = m_Shape[m_ShapeSelected]->m_cx + ((CSquare*)m_Shape[m_ShapeSelected])->m_width / 2;
			sy = m_Shape[m_ShapeSelected]->m_cy;
			break;
		case ST_TRIANGLE:
			sx = m_Shape[m_ShapeSelected]->m_cx + ((CTriangle*)m_Shape[m_ShapeSelected])->m_width / 2;
			sy = m_Shape[m_ShapeSelected]->m_cy + ((CTriangle*)m_Shape[m_ShapeSelected])->m_height / 2;
			break;
		case ST_TEXT:
			sx = m_Shape[m_ShapeSelected]->m_cx + ((CText*)m_Shape[m_ShapeSelected])->m_width / 2;
			sy = m_Shape[m_ShapeSelected]->m_cy + ((CText*)m_Shape[m_ShapeSelected])->m_height / 2;
			break;
		case ST_LINE:
			sx = m_Shape[m_ShapeSelected]->m_cx + ((CLine*)m_Shape[m_ShapeSelected])->m_width / 2;
			sy = m_Shape[m_ShapeSelected]->m_cy + ((CLine*)m_Shape[m_ShapeSelected])->m_height / 2;
			break;
		}
		m_ResizeTag->setCenterPosition(sx, sy);
		m_ResizeTag->draw(&dc);
	}
}



void CChildView::OnMouseMove(UINT nFlags, CPoint point)
{
	CString str;
	str.Format(_T("%d,%d"), point.x, point.y);
	CMainFrame *pMainFrame = (CMainFrame*)AfxGetMainWnd();
	pMainFrame->m_wndStatusBar.SetPaneText(1, str);
	pMainFrame->m_wndStatusBar.SetWindowText(str);
	if (m_pen == true) {
		if (nFlags == MK_LBUTTON) {
			CPen pen(PS_SOLID, 5, BLACK_PEN);
			CClientDC dc(this);
			dc.SelectObject(&pen);
			dc.MoveTo(m_ShapeMovePoint);
			dc.LineTo(point);
		}m_ShapeMovePoint = point;
	}
	if (m_eraser == true) {
		if (nFlags == MK_LBUTTON) {
			CClientDC dc(this);
			CPen pen(PS_SOLID, 10, RGB(255, 255, 255));
			dc.SelectObject(&pen);
			dc.MoveTo(m_ShapeMovePoint);
			dc.LineTo(point);
		}
		m_ShapeMovePoint = point;
	}


	if (m_isTagMove)
	{
		switch (m_Shape[m_ShapeSelected]->m_type)
		{
		case ST_CIRCLE:
			((CCircle*)m_Shape[m_ShapeSelected])->m_radius = point.x - m_Shape[m_ShapeSelected]->m_cx;
			break;
		case ST_RECTANGLE:
			((CRectangle*)m_Shape[m_ShapeSelected])->m_width = (point.x - m_Shape[m_ShapeSelected]->m_cx) * 2;
			((CRectangle*)m_Shape[m_ShapeSelected])->m_height = (point.y - m_Shape[m_ShapeSelected]->m_cy) * 2;
			break;
		case ST_SQUARE:
			((CSquare*)m_Shape[m_ShapeSelected])->m_width =
				((CSquare*)m_Shape[m_ShapeSelected])->m_height = (point.x - m_Shape[m_ShapeSelected]->m_cx) * 2;
			break;
		case ST_TRIANGLE:
			((CTriangle*)m_Shape[m_ShapeSelected])->m_width = (point.x - m_Shape[m_ShapeSelected]->m_cx) * 2;
			((CTriangle*)m_Shape[m_ShapeSelected])->m_height = (point.y - m_Shape[m_ShapeSelected]->m_cy) * 2;
			break;
		case ST_TEXT:
			((CText*)m_Shape[m_ShapeSelected])->m_width = (point.x - m_Shape[m_ShapeSelected]->m_cx) * 2;
			((CText*)m_Shape[m_ShapeSelected])->m_height = (point.y - m_Shape[m_ShapeSelected]->m_cy) * 2;
			break;
		case ST_LINE:
			((CLine*)m_Shape[m_ShapeSelected])->m_width = (point.x - m_Shape[m_ShapeSelected]->m_cx) * 2;
			((CLine*)m_Shape[m_ShapeSelected])->m_height = (point.y - m_Shape[m_ShapeSelected]->m_cy) * 2;
			break;
		}
		Invalidate();
	}
	else if (m_isShapeMove)
	{
		switch (m_Shape[m_ShapeSelected]->m_type) {
			case ST_LINE:
				m_Shape[m_ShapeSelected]->move(point.x - m_ShapeMovePoint.x, point.y - m_ShapeMovePoint.y);
				((CLine*)m_Shape[m_ShapeSelected])->m_width += point.x - m_ShapeMovePoint.x;
				((CLine*)m_Shape[m_ShapeSelected])->m_height += point.y - m_ShapeMovePoint.y;
				break;
			default:
				m_Shape[m_ShapeSelected]->move(point.x - m_ShapeMovePoint.x, point.y - m_ShapeMovePoint.y);
				break;
		}
		
		m_ShapeMovePoint = point;
		Invalidate();
	}

	CWnd::OnMouseMove(nFlags, point);
}


void CChildView::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.

	if (m_ShapeSelected >= 0)
	{
		if (m_ResizeTag->isIn(point.x, point.y))
		{
			m_isTagMove = true;
			m_ShapeMovePoint = point;
		}
	}
	if (!m_isTagMove)
	{
		for (int i = m_ShapeCount - 1; i >= 0; i--)
		{
			if (m_Shape[i]->isIn(point.x, point.y))
			{
				m_isShapeMove = true;
				m_ShapeSelected = i;
				m_ShapeMovePoint = point;
				Invalidate();
				break;
			}
		}
	}

	CWnd::OnLButtonDown(nFlags, point);
}


void CChildView::OnLButtonUp(UINT nFlags, CPoint point)
{
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.
	if (m_isTagMove)
	{
		m_isTagMove = false;
	}
	else if (m_isShapeMove)
	{
		m_isShapeMove = false;
	}

	CWnd::OnLButtonUp(nFlags, point);


}


void CChildView::OnPen()
{

	// TODO: 여기에 명령 처리기 코드를 추가합니다.
	if (m_pen == true) {
		m_pen = false;
		//SetCursor(LoadCursor(NULL, IDC_HAND));
	}
	else {
		m_pen = true;
		m_eraser = false;
		//SetCursor(LoadCursor(NULL, IDC_ARROW));
	}
}


void CChildView::OnEraser()
{
	// TODO: 여기에 명령 처리기 코드를 추가합니다.
	if (m_eraser == true) {
		m_eraser = false;
		//SetCursor(mh_ok_cursor);
	}
	else {
		m_eraser = true;
		m_pen = false;
	}
}
