
// ChildView.h : CChildView 클래스의 인터페이스
//


#pragma once

#include "Shape.h"

#define MAX_SHAPE	100

// CChildView 창

class CChildView : public CWnd
{
	// 생성입니다.d
public:
	CChildView();

	// 특성입니다.
public:
	CShape *m_Shape[MAX_SHAPE];
	int m_ShapeCount;

	CPoint m_ShapeMovePoint;

	bool m_isShapeMove;

	int m_ShapeSelected;

	bool m_pen=false;
	bool m_eraser = false;
	bool m_out=true;
	CSquare *m_ResizeTag;
	bool m_isTagMove;

	// 작업입니다.
public:

	// 재정의입니다.
protected:
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);

	// 구현입니다.
public:
	virtual ~CChildView();

	// 생성된 메시지 맵 함수
protected:
	afx_msg void OnPaint();
	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnPen();
	afx_msg void OnEraser();
};

