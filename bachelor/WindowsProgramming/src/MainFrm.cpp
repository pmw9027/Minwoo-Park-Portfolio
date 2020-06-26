
// MainFrm.cpp : CMainFrame Ŭ������ ����
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

// CMainFrame ����/�Ҹ�
static UINT indicators[] =
{
	ID_SEPARATOR,           // ���� �� ǥ�ñ�
	ID_INDICATOR_POS,
	ID_INDICATOR_CAPS,
	ID_INDICATOR_NUM,
	ID_INDICATOR_SCRL,
};

CMainFrame::CMainFrame() : m_dlgShapeList(NULL)
{
	// TODO: ���⿡ ��� �ʱ�ȭ �ڵ带 �߰��մϴ�.
}

CMainFrame::~CMainFrame()
{
}

int CMainFrame::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
	if (CFrameWnd::OnCreate(lpCreateStruct) == -1)
		return -1;

	// �������� Ŭ���̾�Ʈ ������ �����ϴ� �並 ����ϴ�.
	if (!m_wndView.Create(NULL, NULL, AFX_WS_DEFAULT_VIEW,
		CRect(0, 0, 0, 0), this, AFX_IDW_PANE_FIRST, NULL))
	{
		TRACE0("�� â�� ������ ���߽��ϴ�.\n");
		return -1;
	}
	if (!m_wndStatusBar.Create(this))
	{
		TRACE0("���� ǥ������ ������ ���߽��ϴ�.\n");
		return -1;      // ������ ���߽��ϴ�.
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
	// TODO: CREATESTRUCT cs�� �����Ͽ� ���⿡��
	//  Window Ŭ���� �Ǵ� ��Ÿ���� �����մϴ�.

	cs.dwExStyle &= ~WS_EX_CLIENTEDGE;
	cs.lpszClass = AfxRegisterWndClass(0);
	return TRUE;
}

// CMainFrame ����

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


// CMainFrame �޽��� ó����

void CMainFrame::OnSetFocus(CWnd* /*pOldWnd*/)
{
	// �� â���� ��Ŀ���� �̵��մϴ�.
	m_wndView.SetFocus();
}

BOOL CMainFrame::OnCmdMsg(UINT nID, int nCode, void* pExtra, AFX_CMDHANDLERINFO* pHandlerInfo)
{
	// �信�� ù° ũ���� �ش� ��ɿ� ��Ÿ������ �մϴ�.
	if (m_wndView.OnCmdMsg(nID, nCode, pExtra, pHandlerInfo))
		return TRUE;

	// �׷��� ������ �⺻ ó���մϴ�.
	return CFrameWnd::OnCmdMsg(nID, nCode, pExtra, pHandlerInfo);
}
void CMainFrame::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: ���⿡ �޽��� ó���� �ڵ带 �߰� ��/�Ǵ� �⺻���� ȣ���մϴ�.

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
	// TODO: ���⿡ ��� ó���� �ڵ带 �߰��մϴ�.

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
