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
			str1.Format(_T("��(%d,%d:%d)"), shape->m_cx, shape->m_cy, ((CCircle*)shape)->m_radius);
			break;
		case ST_RECTANGLE:
			str1.Format(_T("�簢��(%d,%d:%d,%d)"), shape->m_cx, shape->m_cy, ((CRectangle*)shape)->m_width, ((CRectangle*)shape)->m_height);
			break;
		case ST_SQUARE:
			str1.Format(_T("���簢��(%d,%d:%d)"), shape->m_cx, shape->m_cy, ((CSquare*)shape)->m_width);
			break;
		case ST_TRIANGLE:
			str1.Format(_T("�ﰢ��(%d,%d:%d,%d)"), shape->m_cx, shape->m_cy, ((CTriangle*)shape)->m_width, ((CTriangle*)shape)->m_height);
			break;
		case ST_TEXT:
			str1.Format(_T("�ۻ���(%d,%d)"), shape->m_cx, shape->m_cy);
			break;
		case ST_LINE:
			str1.Format(_T("��(%d,%d:%d,%d)"), shape->m_cx, shape->m_cy,((CLine*)shape)->m_width, ((CLine*)shape)->m_height);
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

	// TODO:  ���⿡ �߰� �ʱ�ȭ �۾��� �߰��մϴ�.
	UpdateList();

	return TRUE;  // return TRUE unless you set the focus to a control
				  // ����: OCX �Ӽ� �������� FALSE�� ��ȯ�ؾ� �մϴ�.
}


void CShapeListDlg::OnClose()
{
	// TODO: ���⿡ �޽��� ó���� �ڵ带 �߰� ��/�Ǵ� �⺻���� ȣ���մϴ�.
	DestroyWindow();

	//CDialog::OnClose();
}


void CShapeListDlg::PostNcDestroy()
{
	// TODO: ���⿡ Ư��ȭ�� �ڵ带 �߰� ��/�Ǵ� �⺻ Ŭ������ ȣ���մϴ�.
	((CMainFrame*)AfxGetMainWnd())->m_dlgShapeList = NULL;
	delete this;

	CDialog::PostNcDestroy();
}


void CShapeListDlg::OnOK()
{
	// TODO: ���⿡ Ư��ȭ�� �ڵ带 �߰� ��/�Ǵ� �⺻ Ŭ������ ȣ���մϴ�.
	DestroyWindow();

	//CDialog::OnOK();
}


void CShapeListDlg::OnBnClickedButtonTop()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.

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
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
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
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
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
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
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
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
}


void CShapeListDlg::OnBnClickedButtonDelete()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.

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
