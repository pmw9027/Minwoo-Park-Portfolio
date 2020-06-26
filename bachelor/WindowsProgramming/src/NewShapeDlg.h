#pragma once


// CNewShapeDlg 대화 상자입니다.

class CNewShapeDlg : public CDialog
{
	DECLARE_DYNAMIC(CNewShapeDlg)

public:
	CNewShapeDlg(CWnd* pParent = NULL);   // 표준 생성자입니다.
	virtual ~CNewShapeDlg();

// 대화 상자 데이터입니다.
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_DLG_NEW_SHAPE };
#endif

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 지원입니다.

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
