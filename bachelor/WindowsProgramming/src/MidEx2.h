
// MidEx2.h : MidEx2 ���� ���α׷��� ���� �� ��� ����
//
#pragma once

#ifndef __AFXWIN_H__
	#error "PCH�� ���� �� ������ �����ϱ� ���� 'stdafx.h'�� �����մϴ�."
#endif

#include "resource.h"       // �� ��ȣ�Դϴ�.


// CMidEx2App:
// �� Ŭ������ ������ ���ؼ��� MidEx2.cpp�� �����Ͻʽÿ�.
//

class CMidEx2App : public CWinApp
{
public:
	CMidEx2App();


// �������Դϴ�.
public:
	virtual BOOL InitInstance();
	virtual int ExitInstance();

// �����Դϴ�.

public:
	afx_msg void OnAppAbout();
	DECLARE_MESSAGE_MAP()
};

extern CMidEx2App theApp;
