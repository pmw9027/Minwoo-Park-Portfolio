#pragma once


// CNewShapeDlg ��ȭ �����Դϴ�.

class CNewShapeDlg : public CDialog
{
	DECLARE_DYNAMIC(CNewShapeDlg)

public:
	CNewShapeDlg(CWnd* pParent = NULL);   // ǥ�� �������Դϴ�.
	virtual ~CNewShapeDlg();

// ��ȭ ���� �������Դϴ�.
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_DLG_NEW_SHAPE };
#endif

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV �����Դϴ�.

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedRadio1();
	virtual BOOL OnInitDialog();
	afx_msg void OnBnClickedOk();
public:
	int m_ShapeType;
	int m_Cx, m_Cy, m_Width, m_Height;
	COLORREF m_colorLine, m_colorBrush;
	afx_msg void OnBnClickedButtonLineColor();
	afx_msg void OnBnClickedButtonBrushColor();
};
