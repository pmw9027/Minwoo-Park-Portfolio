#pragma once


// CShapeListDlg dialog

class CShapeListDlg : public CDialog
{
	DECLARE_DYNAMIC(CShapeListDlg)

public:
	CShapeListDlg(CWnd* pParent = NULL);   // standard constructor
	virtual ~CShapeListDlg();
	void UpdateList();

	// Dialog Data
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_DLG_SHAPE_LIST };
#endif

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support

	DECLARE_MESSAGE_MAP()
public:
	virtual BOOL OnInitDialog();
	afx_msg void OnClose();
	virtual void PostNcDestroy();
	virtual void OnOK();
	afx_msg void OnBnClickedButtonTop();
	afx_msg void OnBnClickedButtonBottom();
	afx_msg void OnBnClickedButtonUp();
	afx_msg void OnBnClickedButtonDown();
	afx_msg void OnBnClickedButtonRefresh();
	afx_msg void OnBnClickedButtonDelete();
};
