#include "stdafx.h"
#include "MidEx2.h"
#include "ShapeEditDlg.h"
#include "afxdialogex.h"
#include "MainFrm.h"
#include "ChildView.h"

IMPLEMENT_DYNAMIC(CShapeEditDlg, CDialog)

CShapeEditDlg::CShapeEditDlg(CWnd* pParent /*=NULL*/)
	: CDialog(IDD_DLG_SHAPE_EDIT, pParent)
{
}


CShapeEditDlg::~CShapeEditDlg()
{
}
void CShapeEditDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
}
void CShapeEditDlg::UpdateList()
{
	CListBox * listBox = (CListBox*)GetDlgItem(IDC_EDIT_LIST);
	listBox->ResetContent();
	CMainFrame * wndMain = (CMainFrame *)AfxGetMainWnd();
	for (int i = wndMain->m_wndView.m_ShapeCount - 1; i >= 0; i--)
	{
		CString str, str1;
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
			str1.Format(_T("��(%d,%d:%d,%d)"), shape->m_cx, shape->m_cy, ((CLine*)shape)->m_width, ((CLine*)shape)->m_height);
			break;
		}
		listBox->AddString(str + str1);
	}
}
BOOL CShapeEditDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// TODO:  ���⿡ �߰� �ʱ�ȭ �۾��� �߰��մϴ�.
	UpdateList();

	return TRUE;  // return TRUE unless you set the focus to a control
				  // ����: OCX �Ӽ� �������� FALSE�� ��ȯ�ؾ� �մϴ�.
}BEGIN_MESSAGE_MAP(CShapeEditDlg, CDialog)
ON_LBN_SELCHANGE(IDC_EDIT_LIST, &CShapeEditDlg::OnLbnSelchangeEditList)
ON_BN_CLICKED(IDC_BUTTON_EDIT_LINE_ECOLOR, &CShapeEditDlg::OnBnClickedButtonEditLineEcolor)
ON_BN_CLICKED(IDC_BUTTON_EDIT_BRUSH_ECOLOR, &CShapeEditDlg::OnBnClickedButtonEditBrushEcolor)
ON_BN_CLICKED(IDOK, &CShapeEditDlg::OnBnClickedOk)
END_MESSAGE_MAP()


void CShapeEditDlg::OnLbnSelchangeEditList()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
	CListBox * listBox = (CListBox*)GetDlgItem(IDC_EDIT_LIST);
	CMainFrame * wndMain = (CMainFrame *)AfxGetMainWnd();
	CChildView* temp;
	CShape* shape = wndMain->m_wndView.m_Shape[listBox->GetCurSel()];

	CEditView *editview = (CEditView*)GetDlgItem(IDC_EDIT_EHEIGHT);
	//editview->GetEditCtrl;
		
	SetDlgItemInt(IDC_EDIT_eCX, shape->m_cx);
	SetDlgItemInt(IDC_EDIT_eCY,shape->m_cy);

	switch (shape->m_type)
	{
	case ST_CIRCLE:
		SetDlgItemInt(IDC_EDIT_EWIDTH, ((CCircle*)shape)->m_radius);
		break;
	case ST_RECTANGLE:
		SetDlgItemInt(IDC_EDIT_EWIDTH, ((CRectangle*)shape)->m_width);
		SetDlgItemInt(IDC_EDIT_EHEIGHT, ((CRectangle*)shape)->m_height);
		break;
	case ST_SQUARE:
		SetDlgItemInt(IDC_EDIT_EWIDTH, ((CSquare*)shape)->m_width);
		break;
	case ST_TRIANGLE:
		SetDlgItemInt(IDC_EDIT_EWIDTH, ((CTriangle*)shape)->m_width);
		SetDlgItemInt(IDC_EDIT_EHEIGHT, ((CTriangle*)shape)->m_height);
		break;
	case ST_TEXT:
		SetDlgItemTextW(IDC_EDIT_ETEXT, ((CText*)shape)->m_text);
		break;
	case ST_LINE:
		SetDlgItemInt(IDC_EDIT_EWIDTH, ((CLine*)shape)->m_width);
		SetDlgItemInt(IDC_EDIT_EHEIGHT, ((CLine*)shape)->m_height);
		break;

	}
	SetDlgItemInt(IDC_EDIT_LINE_ECOLOR,shape->m_colorLine);
	SetDlgItemInt(IDC_EDIT_BRUSH_ECOLOR,shape->m_colorBrush);

}


void CShapeEditDlg::OnBnClickedButtonEditLineEcolor()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
	CMFCColorDialog dlg;
	if (dlg.DoModal() == IDOK)
	{
		SetDlgItemInt(IDC_EDIT_LINE_ECOLOR, dlg.GetColor());
	}
}


void CShapeEditDlg::OnBnClickedButtonEditBrushEcolor()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
	CMFCColorDialog dlg;
	if (dlg.DoModal() == IDOK)
	{
		SetDlgItemInt(IDC_EDIT_BRUSH_ECOLOR, dlg.GetColor());
	}
}


void CShapeEditDlg::OnBnClickedOk()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
	CListBox* listbox = (CListBox*)GetDlgItem(IDC_EDIT_LIST);
	CMainFrame * wndMain = (CMainFrame *)AfxGetMainWnd();
	CChildView* temp;
	CShape* shape = wndMain->m_wndView.m_Shape[listbox->GetCurSel()];
	shape->m_cx = GetDlgItemInt(IDC_EDIT_eCX);
	shape->m_cy = GetDlgItemInt(IDC_EDIT_eCY);
	m_Width = GetDlgItemInt(IDC_EDIT_EWIDTH);
	m_Height = GetDlgItemInt(IDC_EDIT_EHEIGHT);
	shape->m_colorLine= GetDlgItemInt(IDC_EDIT_LINE_ECOLOR);
	shape->m_colorBrush=GetDlgItemInt(IDC_EDIT_BRUSH_ECOLOR);
	switch (shape->m_type)
	{
	case ST_CIRCLE:
		((CCircle*)shape)->m_radius = GetDlgItemInt(IDC_EDIT_EWIDTH);
		break;
	case ST_RECTANGLE:
		((CRectangle*)shape)->m_width = GetDlgItemInt(IDC_EDIT_EWIDTH);
		((CRectangle*)shape)->m_height = GetDlgItemInt(IDC_EDIT_EHEIGHT);
		break;
	case ST_SQUARE:
		((CSquare*)shape)->m_width = GetDlgItemInt(IDC_EDIT_EWIDTH);
		break;
	case ST_TRIANGLE:
		((CTriangle*)shape)->m_width=GetDlgItemInt(IDC_EDIT_EWIDTH);
		((CTriangle*)shape)->m_height=GetDlgItemInt(IDC_EDIT_EHEIGHT);
		break;
	case ST_TEXT:
		GetDlgItemText(IDC_EDIT_ETEXT, ((CText*)shape)->m_text);
		break;
	case ST_LINE:
		((CLine*)shape)->m_width = GetDlgItemInt(IDC_EDIT_EWIDTH);
		((CLine*)shape)->m_height = GetDlgItemInt(IDC_EDIT_EHEIGHT);
		break;

	}
	CDialog::OnOK();
}
