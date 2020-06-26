
// NewShapeDlg.cpp : ���� �����Դϴ�.
//

#include "stdafx.h"
#include "Shape.h"
#include "MidEx2.h"
#include "NewShapeDlg.h"
#include "afxdialogex.h"


// CNewShapeDlg ��ȭ �����Դϴ�.

IMPLEMENT_DYNAMIC(CNewShapeDlg, CDialog)

CNewShapeDlg::CNewShapeDlg(CWnd* pParent /*=NULL*/): CDialog(IDD_DLG_NEW_SHAPE, pParent)
	, m_ShapeType(-1)
	, m_Cx(0)
	, m_Cy(0)
	, m_Width(0)
	, m_Height(0)
{

}

CNewShapeDlg::~CNewShapeDlg()
{
}

void CNewShapeDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
}


BEGIN_MESSAGE_MAP(CNewShapeDlg, CDialog)
	ON_BN_CLICKED(IDC_RADIO1, &CNewShapeDlg::OnBnClickedRadio1)
	ON_BN_CLICKED(IDOK, &CNewShapeDlg::OnBnClickedOk)
	ON_BN_CLICKED(IDC_BUTTON_LINE_COLOR, &CNewShapeDlg::OnBnClickedButtonLineColor)
	ON_BN_CLICKED(IDC_BUTTON_BRUSH_COLOR, &CNewShapeDlg::OnBnClickedButtonBrushColor)
END_MESSAGE_MAP()


// CNewShapeDlg �޽��� ó�����Դϴ�.


void CNewShapeDlg::OnBnClickedRadio1()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
}


BOOL CNewShapeDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// TODO:  ���⿡ �߰� �ʱ�ȭ �۾��� �߰��մϴ�.
	((CButton*)GetDlgItem(IDC_RADIO_CIRCLE))->SetCheck(TRUE);
	return TRUE;  // return TRUE unless you set the focus to a control
				  // ����: OCX �Ӽ� �������� FALSE�� ��ȯ�ؾ� �մϴ�.
}
void CNewShapeDlg::OnBnClickedOk()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
	int nType;
	bool checkbox[6];
	checkbox[0] = ((CButton*)GetDlgItem(IDC_RADIO_CIRCLE))->GetCheck();
	checkbox[1] = ((CButton*)GetDlgItem(IDC_RADIO_SQUARE))->GetCheck();
	checkbox[2] = ((CButton*)GetDlgItem(IDC_RADIO_RECTANGLE))->GetCheck();
	checkbox[3] = ((CButton*)GetDlgItem(IDC_RADIO_TRIANGLE))->GetCheck();
	checkbox[4] = ((CButton*)GetDlgItem(IDC_RADIO_TEXT))->GetCheck();
	checkbox[5] = ((CButton*)GetDlgItem(IDC_RADIO_Line))->GetCheck();

	for (int i = 0; i < 6; i++)
		if (checkbox[i])
			m_ShapeType = ST(i);
		
	m_Cx = GetDlgItemInt(IDC_EDIT_CX);
	m_Cy = GetDlgItemInt(IDC_EDIT_CY);
	m_Width = GetDlgItemInt(IDC_EDIT_WIDTH);
	m_Height = GetDlgItemInt(IDC_EDIT_HEIGHT);

	m_colorLine = GetDlgItemInt(IDC_EDIT_LINE_COLOR);
	m_colorBrush = GetDlgItemInt(IDC_EDIT_BRUSH_COLOR);

	CDialog::OnOK();
}


void CNewShapeDlg::OnBnClickedButtonLineColor()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.

	CMFCColorDialog dlg;
	if (dlg.DoModal() == IDOK)
	{
		SetDlgItemInt(IDC_EDIT_LINE_COLOR, dlg.GetColor());
	}
}


void CNewShapeDlg::OnBnClickedButtonBrushColor()
{
	// TODO: ���⿡ ��Ʈ�� �˸� ó���� �ڵ带 �߰��մϴ�.
	CMFCColorDialog dlg;
	if (dlg.DoModal() == IDOK)
	{
		SetDlgItemInt(IDC_EDIT_BRUSH_COLOR, dlg.GetColor());
	}
}
