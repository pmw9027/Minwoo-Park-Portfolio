<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="Que.Query"%>
<jsp:useBean id="EUN" class="Que.Query" />
<%
	request.setCharacterEncoding("euc-kr");
	String title = (String) request.getParameter("title");
	String user = (String) request.getParameter("name");
	String pw = (String) request.getParameter("password");
	String content = (String) request.getParameter("memo");
	String reply = (String) request.getParameter("reply");
	int num = EUN.writeDao(user, title, pw, content);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>