#pragma once

enum ST{
	ST_CIRCLE,
	ST_RECTANGLE,
	ST_TRIANGLE,
	ST_SQUARE,
	ST_TEXT,
	ST_LINE,

	ST_COUNT
};
class CShape
{
public:
	int m_type;
	int m_cx;
	int m_cy;
	CPen pen;
	COLORREF m_colorLine;
	COLORREF m_colorBrush;
public:
	CShape(void);
	~CShape(void);
	void setCenterPosition(int x, int y);
	void move(int dx, int dy);
	virtual void draw(CDC *dc);
	virtual double getArea();
	virtual double getPerimeter();
	virtual void setScale(double scale);
	virtual bool isIn(int x, int y);
};

class CRectangle : public CShape
{
public:
	int m_width;
	int m_height;
public:
	CRectangle(void);
	~CRectangle(void);
	CRectangle(int x, int y, int width, int height);
	void init(int width, int height);
	virtual void draw(CDC *dc);
	virtual double getArea();
	virtual double getPerimeter();
	virtual void setScale(double scale);
	virtual bool isIn(int x, int y);
};

class CSquare : public CRectangle
{
public:
	CSquare(void);
	~CSquare(void);
	CSquare(int x, int y, int width);
	//void init(int width, int height);
	//virtual void draw(CDC *dc);
	//virtual double getArea();
};

class CCircle : public CShape
{
public:
	int m_radius;
public:
	CCircle(void);
	~CCircle(void);
	CCircle(int x, int y, int radius);
	void init(int raius);
	virtual void draw(CDC *dc);
	virtual double getArea();
	virtual double getPerimeter();
	virtual void setScale(double scale);
	virtual bool isIn(int x, int y);
};

class CTriangle : public CShape
{
public:
	int m_width;
	int m_height;
public:
	CTriangle(void);
	~CTriangle(void);
	CTriangle(int x, int y, int width, int height);
	void init(int width, int height);
	virtual void draw(CDC *dc);
	virtual double getArea();
	virtual double getPerimeter();
	virtual void setScale(double scale);
	virtual bool isIn(int x, int y);

};
class CText : public CShape
{
public:
	int m_width;
	int m_height;
	CString m_text;
public:
	CText(void);
	~CText(void);
	CText(int x, int y, CString text);
	void init(int width, int height);
	virtual void draw(CDC *dc);
	virtual bool isIn(int x, int y);

};

class CLine : public CShape
{
public:
	int m_width;
	int m_height;
public:
	CLine(void);
	~CLine(void);
	CLine(int x, int y);
	void init(int width, int height);
	virtual void draw(CDC *dc);
	virtual bool isIn(int x, int y);
};