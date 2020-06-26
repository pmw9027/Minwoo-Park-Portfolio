#include "StdAfx.h"
#include "Shape.h"
#define _USE_MATH_DEFINES
#include <cmath>

// CShape class
CShape::CShape(void)
	: m_cx(0)
	, m_cy(0)
	, m_colorLine(RGB(0, 0, 0))
	, m_colorBrush(RGB(255, 255, 255))
{
}

CShape::~CShape(void)
{
}

void CShape::setCenterPosition(int x, int y)
{
	m_cx = x;
	m_cy = y;
}

void CShape::move(int dx, int dy)
{
	m_cx += dx;
	m_cy += dy;
}

void CShape::draw(CDC *dc)
{
}

double CShape::getArea()
{
	return .0;
}

double CShape::getPerimeter()
{
	return .0;
}

void CShape::setScale(double scale)
{
}
bool CShape::isIn(int x, int y)
{
	return false;
}

/////////////////////////////////////////////////
// CRectangle class
CRectangle::CRectangle(void)
{
	m_type = ST_RECTANGLE;
}

CRectangle::~CRectangle(void)
{
}

CRectangle::CRectangle(int x, int y, int width, int height)
{
	m_type = ST_RECTANGLE;
	setCenterPosition(x, y);
	init(width, height);
}

void CRectangle::init(int width, int height)
{
	m_width = width;
	m_height = height;
}

void CRectangle::draw(CDC *dc)
{
	dc->Rectangle(m_cx - m_width / 2, m_cy - m_height / 2, m_cx + m_width / 2, m_cy + m_height / 2);
}

double CRectangle::getArea()
{
	return (double)(m_width * m_height);
}

double CRectangle::getPerimeter()
{
	return (double)(m_width + m_height) * 2;
}

void CRectangle::setScale(double scale)
{
	m_width = (int)((double)m_width * scale);
	m_height = (int)((double)m_height * scale);
}
bool CRectangle::isIn(int x, int y)
{
	return (x >= (m_cx - m_width / 2) && x <= (m_cx + m_width / 2)
		&& y >= (m_cy - m_height / 2) && y <= (m_cy + m_height / 2));
}
/////////////////////////////////////////////////
// CSquare class
CSquare::CSquare(void)
{
	m_type = ST_SQUARE;
}

CSquare::~CSquare(void)
{
}

CSquare::CSquare(int x, int y, int width)
{
	m_type = ST_SQUARE;
	setCenterPosition(x, y);
	CRectangle::init(width, width);
}


/////////////////////////////////////////////////
// CCircle class
CCircle::CCircle(void)
{
	m_type = ST_CIRCLE;
}

CCircle::~CCircle(void)
{
}

CCircle::CCircle(int x, int y, int radius)
{
	m_type = ST_CIRCLE;
	setCenterPosition(x, y);
	init(radius);
}

void CCircle::init(int radius)
{
	m_radius = radius;
}
	
void CCircle::draw(CDC *dc)
{
	dc->Ellipse(m_cx - m_radius, m_cy - m_radius, m_cx + m_radius, m_cy + m_radius);
	
}

double CCircle::getArea()
{
	return (m_radius * m_radius * M_PI);
}

double CCircle::getPerimeter()
{
	return (2.0 * m_radius * M_PI);
}

void CCircle::setScale(double scale)
{
	m_radius = (int)((double)m_radius * scale);
}
bool CCircle::isIn(int x, int y)
{
	return (sqrt(pow(double(x - m_cx), 2) + pow(double(y - m_cy), 2)) <= m_radius);
}

/////////////////////////////////////////////////
// CTriangle class
CTriangle::CTriangle(void)
{
	m_type = ST_TRIANGLE;
}

CTriangle::~CTriangle(void)
{
}

CTriangle::CTriangle(int x, int y, int width, int height)
{
	m_type = ST_TRIANGLE;
	setCenterPosition(x, y);
	init(width, height);
}

void CTriangle::init(int width, int height)
{
	m_width = width;
	m_height = height;
}

void CTriangle::draw(CDC *dc)
{
	POINT pts[3] = { { m_cx, m_cy - m_height / 2 },{ m_cx + m_width / 2, m_cy + m_height / 2 },{ m_cx - m_width / 2, m_cy + m_height / 2 } };
	dc->Polygon(pts, 3);
}

double CTriangle::getArea()
{
	return (double)(m_width * m_height * 0.5);
}

double CTriangle::getPerimeter()
{
	return (std::sqrt(((double)m_height*m_height) + (m_width / 2)*(m_width / 2)) * 2 + m_width);
}

void CTriangle::setScale(double scale)
{
	m_width = (int)((double)m_width * scale);
	m_height = (int)((double)m_height * scale);
}
bool CTriangle::isIn(int x, int y)
{
	return (x >= (m_cx - m_width / 2) && x <= (m_cx + m_width / 2)
		&& y >= (m_cy - m_height / 2) && y <= (m_cy + m_height / 2));
}

CText::CText(void)
{
	m_type = ST_TEXT;
}

CText::~CText(void)
{
}

CText::CText(int x, int y, CString text)
{
	m_type = ST_TEXT;
	m_text = text;
	setCenterPosition(x, y);
	init(100, 100);
}

void CText::init(int width, int height)
{
	m_width = width;
	m_height = height;
}

void CText::draw(CDC *dc)
{
	dc->SetBkColor(m_colorBrush);
	dc->SetTextColor(m_colorLine);
	dc->TextOut(m_cx, m_cy, m_text);
}
bool CText::isIn(int x, int y)
{
	return ((m_cx<x&&x<m_cx+100)&&(m_cy<y&&y<m_cy+50));
}

CLine::CLine(void)
{
	m_type = ST_LINE;
}

CLine::~CLine(void)
{
}

CLine::CLine(int x, int y)
{
	m_type = ST_LINE;
	setCenterPosition(x, y);
	init(100, 100);
}

void CLine::init(int width, int height)
{
	m_width = width;
	m_height = height;
}

void CLine::draw(CDC *dc)
{
	dc->SetBkColor(m_colorBrush);
	dc->SetTextColor(m_colorLine);
	dc->MoveTo(m_cx, m_cy);
	dc->LineTo(m_width, m_height);
}
bool CLine::isIn(int x, int y)
{
	return (y == (m_cy - m_height) / (m_cx - m_width)*x + (m_cy - ((m_cy - m_height) / (m_cx - m_width))*m_cx));
}