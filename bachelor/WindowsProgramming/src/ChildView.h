
// ChildView.h : CChildView Ŭ������ �������̽�
//


#pragma once

#include "Shape.h"

#define MAX_SHAPE	100

// CChildView â

class CChildView : public CWnd
{
	// �����Դϴ�.d
public:
	CChildView();

	// Ư���Դϴ�.
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

	// �۾��Դϴ�.
public:

	// �������Դϴ�.
protected:
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);

	// �����Դϴ�.
public:
	virtual ~CChildView();

	// ������ �޽��� �� �Լ�
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

