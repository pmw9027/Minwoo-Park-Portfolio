// ShapeListDlg.cpp : implementation file
//

#include "stdafx.h"
#include "MidEx2.h"
#include "ShapeListDlg.h"
#include "afxdialogex.h"
#include "MainFrm.h"
#include "ChildView.h"

// CShapeListDlg dialog

IMPLEMENT_DYNAMIC(CShapeListDlg, CDialog)

CShapeListDlg::CShapeListDlg(CWnd* pParent /*=NULL*/)
	: CDialog(IDD_DLG_SHAPE_LIST, pParent)
{

}

CShapeListDlg::~CShapeListDlg()
{
}

void CShapeListDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
}

void CShapeListDlg::UpdateList()
{
	CListBox * listBox = (CListBox*)GetDlgItem(IDC_LIST1);
	listBox->ResetContent();
	CMainFrame * wndMain = (CMainFrame *)AfxGetMainWnd();
	for (int i = wndMain->m_wndView.m_ShapeCount - 1; i >= 0; i--)
	{
		CString str, str1;
		str.Format(_T("%d:"), i);
		CShape* shape = wndMain->m_wndView.m_Shape[i];
		switch (shape->m_type)
		{
		case ST_CIRCLE:
			str1.Format(_T("원(%d,%d:%d)"), shape->m_cx, shape->m_cy, ((CCircle*)shape)->m_radius);
			break;
		case ST_RECTANGLE:
			str1.Format(_T("사각형(%d,%d:%d,%d)"), shape->m_cx, shape->m_cy, ((CRectangle*)shape)->m_width, ((CRectangle*)shape)->m_height);
			break;
		case ST_SQUARE:
			str1.Format(_T("정사각형(%d,%d:%d)"), shape->m_cx, shape->m_cy, ((CSquare*)shape)->m_width);
			break;
		case ST_TRIANGLE:
			str1.Format(_T("삼각형(%d,%d:%d,%d)"), shape->m_cx, shape->m_cy, ((CTriangle*)shape)->m_width, ((CTriangle*)shape)->m_height);
			break;
		case ST_TEXT:
			str1.Format(_T("글상자(%d,%d)"), shape->m_cx, shape->m_cy);
			break;
		case ST_LINE:
			str1.Format(_T("선(%d,%d:%d,%d)"), shape->m_cx, shape->m_cy,((CLine*)shape)->m_width, ((CLine*)shape)->m_height);
			break;
		}
		listBox->AddString(str + str1);
	}
}

BEGIN_MESSAGE_MAP(CShapeListDlg, CDialog)
	ON_WM_CLOSE()
	ON_BN_CLICKED(IDC_BUTTON_TOP, &CShapeListDlg::OnBnClickedButtonTop)
	ON_BN_CLICKED(IDC_BUTTON_BOTTOM, &CShapeListDlg::OnBnClickedButtonBottom)
	ON_BN_CLICKED(IDC_BUTTON_UP, &CShapeListDlg::OnBnClickedButtonUp)
	ON_BN_CLICKED(IDC_BUTTON_DOWN, &CShapeListDlg::OnBnClickedButtonDown)
	ON_BN_CLICKED(IDC_BUTTON_REFRESH, &CShapeListDlg::OnBnClickedButtonRefresh)
	ON_BN_CLICKED(IDC_BUTTON_DELETE, &CShapeListDlg::OnBnClickedButtonDelete)
END_MESSAGE_MAP()


// CShapeListDlg message handlers


BOOL CShapeListDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// TODO:  여기에 추가 초기화 작업을 추가합니다.
	UpdateList();

	return TRUE;  // return TRUE unless you set the focus to a control
				  // 예외: OCX 속성 페이지는 FALSE를 반환해야 합니다.
}


void CShapeListDlg::OnClose()
{
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.
	DestroyWindow();

	//CDialog::OnClose();
}


void CShapeListDlg::PostNcDestroy()
{
	// TODO: 여기에 특수화된 코드를 추가 및/또는 기본 클래스를 호출합니다.
	((CMainFrame*)AfxGetMainWnd())->m_dlgShapeList = NULL;
	delete this;

	CDialog::PostNcDestroy();
}


void CShapeListDlg::OnOK()
{
	// TODO: 여기에 특수화된 코드를 추가 및/또는 기본 클래스를 호출합니다.
	DestroyWindow();

	//CDialog::OnOK();
}


void CShapeListDlg::OnBnClickedButtonTop()
{
	// TODO: 여기에 컨트롤 알림 처리기 코드를 추가합니다.

	CListBox* listbox = (CListBox*)GetDlgItem(IDC_LIST1);

	int idx = listbox->GetCurSel();

	CShape* tarShape = ((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx];

	for (int i = idx; i > 0; i--)
		((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[i] = ((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[i - 1];
	((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[0] = tarShape;

	UpdateList();
	listbox->SetCurSel(0);
	((CMainFrame *)AfxGetMainWnd())->m_wndView.Invalidate();
	
}


void CShapeListDlg::OnBnClickedButtonBottom()
{
	// TODO: 여기에 컨트롤 알림 처리기 코드를 추가합니다.
	CListBox* listbox = (CListBox*)GetDlgItem(IDC_LIST1);

	int idx = listbox->GetCurSel();

	CShape* tarShape = ((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx];

	for (int i = idx; i < listbox->GetCount()-1; i++)
		((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[i] = ((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[i + 1];
	((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[listbox->GetCount() - 1] = tarShape;

	UpdateList();
	listbox->SetCurSel(listbox->GetCount() - 1);
	((CMainFrame *)AfxGetMainWnd())->m_wndView.Invalidate();
	
}


void CShapeListDlg::OnBnClickedButtonUp()
{
	// TODO: 여기에 컨트롤 알림 처리기 코드를 추가합니다.
	CListBox* listbox = (CListBox*)GetDlgItem(IDC_LIST1);

	int idx = listbox->GetCurSel();
	if (idx < 1) {
		return;
	}
	CShape* temp=((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx];
	
	((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx] = ((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx - 1];
	((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx - 1] = temp;

	UpdateList();
	listbox->SetCurSel(idx -1);
	((CMainFrame *)AfxGetMainWnd())->m_wndView.Invalidate();
}


void CShapeListDlg::OnBnClickedButtonDown()
{
	// TODO: 여기에 컨트롤 알림 처리기 코드를 추가합니다.
	CListBox* listbox = (CListBox*)GetDlgItem(IDC_LIST1);
	int idx = listbox->GetCurSel();
	if (idx < 0||idx==(listbox->GetCount())-1) {
		return;
	}
	CShape* temp = ((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx];

	((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx] = ((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx + 1];
	((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[idx + 1] = temp;

	UpdateList();
	listbox->SetCurSel(idx + 1);
	((CMainFrame *)AfxGetMainWnd())->m_wndView.Invalidate();
}


void CShapeListDlg::OnBnClickedButtonRefresh()
{
	UpdateList();
	// TODO: 여기에 컨트롤 알림 처리기 코드를 추가합니다.
}


void CShapeListDlg::OnBnClickedButtonDelete()
{
	// TODO: 여기에 컨트롤 알림 처리기 코드를 추가합니다.

	CListBox* listbox = (CListBox*)GetDlgItem(IDC_LIST1);
	int idx = listbox->GetCurSel();
	if (idx == -1)
		return;
	for (int i = idx; i < listbox->GetCount() - 1; i++)
		((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[i] = ((CMainFrame *)AfxGetMainWnd())->m_wndView.m_Shape[i + 1];
	((CMainFrame *)AfxGetMainWnd())->m_wndView.m_ShapeCount--;

	UpdateList();
	((CMainFrame *)AfxGetMainWnd())->m_wndView.Invalidate();
}
