
// MainFrm.cpp : CMainFrame 클래스의 구현
//

#include "stdafx.h"
#include "MidEx2.h"
#include "MainFrm.h"
#include "NewShapeDlg.h"
#include "ShapeListDlg.h"
#include "ShapeEditDlg.h"
#ifdef _DEBUG
#define new DEBUG_NEW
#endif

// CMainFrame

IMPLEMENT_DYNAMIC(CMainFrame, CFrameWnd)

BEGIN_MESSAGE_MAP(CMainFrame, CFrameWnd)
	ON_WM_CREATE()
	ON_WM_SETFOCUS()
	ON_WM_LBUTTONDOWN()
	ON_COMMAND(ID_NEW_SHAPE, &CMainFrame::OnNewShape)
	ON_COMMAND(ID_SHAPE_LIST, &CMainFrame::OnShapeList)
	ON_COMMAND(ID_SHAPE_EDIT, &CMainFrame::OnShapeEdit)
END_MESSAGE_MAP()

// CMainFrame 생성/소멸
static UINT indicators[] =
{
	ID_SEPARATOR,           // 상태 줄 표시기
	ID_INDICATOR_POS,
	ID_INDICATOR_CAPS,
	ID_INDICATOR_NUM,
	ID_INDICATOR_SCRL,
};

CMainFrame::CMainFrame() : m_dlgShapeList(NULL)
{
	// TODO: 여기에 멤버 초기화 코드를 추가합니다.
}

CMainFrame::~CMainFrame()
{
}

int CMainFrame::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
	if (CFrameWnd::OnCreate(lpCreateStruct) == -1)
		return -1;

	// 프레임의 클라이언트 영역을 차지하는 뷰를 만듭니다.
	if (!m_wndView.Create(NULL, NULL, AFX_WS_DEFAULT_VIEW,
		CRect(0, 0, 0, 0), this, AFX_IDW_PANE_FIRST, NULL))
	{
		TRACE0("뷰 창을 만들지 못했습니다.\n");
		return -1;
	}
	if (!m_wndStatusBar.Create(this))
	{
		TRACE0("상태 표시줄을 만들지 못했습니다.\n");
		return -1;      // 만들지 못했습니다.
	}
	m_wndStatusBar.SetIndicators(indicators, sizeof(indicators) / sizeof(UINT));
	//m_wndStatusBar.GetPaneInfo(
	m_wndStatusBar.SetPaneInfo(1, ID_INDICATOR_POS, SBPS_NORMAL, 200);
	return 0;
}

BOOL CMainFrame::PreCreateWindow(CREATESTRUCT& cs)
{
	if( !CFrameWnd::PreCreateWindow(cs) )
		return FALSE;
	// TODO: CREATESTRUCT cs를 수정하여 여기에서
	//  Window 클래스 또는 스타일을 수정합니다.

	cs.dwExStyle &= ~WS_EX_CLIENTEDGE;
	cs.lpszClass = AfxRegisterWndClass(0);
	return TRUE;
}

// CMainFrame 진단

#ifdef _DEBUG
void CMainFrame::AssertValid() const
{
	CFrameWnd::AssertValid();
}

void CMainFrame::Dump(CDumpContext& dc) const
{
	CFrameWnd::Dump(dc);
}
#endif //_DEBUG


// CMainFrame 메시지 처리기

void CMainFrame::OnSetFocus(CWnd* /*pOldWnd*/)
{
	// 뷰 창으로 포커스를 이동합니다.
	m_wndView.SetFocus();
}

BOOL CMainFrame::OnCmdMsg(UINT nID, int nCode, void* pExtra, AFX_CMDHANDLERINFO* pHandlerInfo)
{
	// 뷰에서 첫째 크랙이 해당 명령에 나타나도록 합니다.
	if (m_wndView.OnCmdMsg(nID, nCode, pExtra, pHandlerInfo))
		return TRUE;

	// 그렇지 않으면 기본 처리합니다.
	return CFrameWnd::OnCmdMsg(nID, nCode, pExtra, pHandlerInfo);
}
void CMainFrame::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.

	CFrameWnd::OnLButtonDown(nFlags, point);
}
void CMainFrame::OnNewShape()
{
	CNewShapeDlg dlg(this);
	if (dlg.DoModal() == IDOK)
	{
		switch (dlg.m_ShapeType)
		{
		case ST_CIRCLE:
			m_wndView.m_Shape[m_wndView.m_ShapeCount] = new CCircle(dlg.m_Cx, dlg.m_Cy, dlg.m_Width);
			break;
		case ST_RECTANGLE:
			m_wndView.m_Shape[m_wndView.m_ShapeCount] = new CRectangle(dlg.m_Cx, dlg.m_Cy, dlg.m_Width, dlg.m_Height);
			break;
		case ST_TRIANGLE:
			m_wndView.m_Shape[m_wndView.m_ShapeCount] = new CTriangle(dlg.m_Cx, dlg.m_Cy, dlg.m_Width, dlg.m_Height);
			break;
		case ST_SQUARE:
			m_wndView.m_Shape[m_wndView.m_ShapeCount] = new CSquare(dlg.m_Cx, dlg.m_Cy, dlg.m_Width);
			break;
		case ST_TEXT:
			m_wndView.m_Shape[m_wndView.m_ShapeCount] = new CText(dlg.m_Cx, dlg.m_Cy, _T("Text box"));
			break;
		case ST_LINE:
			m_wndView.m_Shape[m_wndView.m_ShapeCount] = new CLine(dlg.m_Cx, dlg.m_Cy);
			break;
		}
		m_wndView.m_Shape[m_wndView.m_ShapeCount]->m_colorLine = dlg.m_colorLine;
		m_wndView.m_Shape[m_wndView.m_ShapeCount]->m_colorBrush = dlg.m_colorBrush;
		m_wndView.m_ShapeCount++;
		m_wndView.Invalidate();
	}
}


void CMainFrame::OnShapeList()
{
#if 0
	CShapeListDlg dlg(this);
	dlg.DoModal();
#else
	if (m_dlgShapeList == NULL)
	{
		m_dlgShapeList = new CShapeListDlg();
		m_dlgShapeList->Create(IDD_DLG_SHAPE_LIST);
		m_dlgShapeList->ShowWindow(SW_SHOW);
	}
	else
	{
		m_dlgShapeList->SetFocus();
	}
#endif
}


void CMainFrame::OnShapeEdit()
{
	// TODO: 여기에 명령 처리기 코드를 추가합니다.

	CShapeEditDlg dlg(this);
#if !0
	if (dlg.DoModal() == IDOK)
		m_wndView.Invalidate();
#else
	if (m_dlgShapeEdit == NULL)
	{

		m_dlgShapeEdit = new CShapeEditDlg();
		m_dlgShapeEdit->Create(IDD_DLG_SHAPE_EDIT);
		m_dlgShapeEdit->ShowWindow(SW_SHOW);
	}
	else
	{
		m_dlgShapeEdit->SetFocus();
	}
#endif
}
