#pragma once


class CShapeEditDlg :
	public CDialog
{
	DECLARE_DYNAMIC(CShapeEditDlg)

public:
	CShapeEditDlg(CWnd* pParent = NULL);   // standard constructor
	~CShapeEditDlg();
	void UpdateList();
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_DLG_SHAPE_EDIT };
#endif
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support

	//DECLARE_MESSAGE_MAP()

public:
	int m_ShapeType;
	int m_Cx, m_Cy, m_Width, m_Height;

	COLORREF m_colorLine, m_colorBrush;
	virtual BOOL OnInitDialog();
	DECLARE_MESSAGE_MAP()
	afx_msg void OnLbnSelchangeEditList();

	afx_msg void OnBnClickedButtonEditLineEcolor();
	afx_msg void OnBnClickedButtonEditBrushEcolor();
	afx_msg void OnBnClickedOk();
};

