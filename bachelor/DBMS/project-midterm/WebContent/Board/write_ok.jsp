<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="Board.*"%>
<%@ page import="Que.Query"%>
<jsp:useBean id="EUN" class="Que.Query" />
<%
	request.setCharacterEncoding("euc-kr");
	String title = (String) request.getParameter("title");
	String user = (String) request.getParameter("name");
	String pw = (String) request.getParameter("password");
	String content = (String) request.getParameter("memo");
	int num = EUN.writeDao(user, title, pw, content);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ���</title>
</head>
<body>
	<table width="1000" height="300" border=1>
		<tr>
			<td align="center"><%=(String) request.getParameter("name")%> <%
 	if (num == 1) {
 %> ���� ���� �����Ͽ����ϴ�. <%
 	} else {
 %> ��������� �����Ϳ� �������ֽñ� �ٶ��ϴ�. <%
 	}
 %> <br> <br> <br> <br> <br> <br>
		<tr align="center">
			<input type=button value="4���Ŀ� �Խ��Ǹ������ �̵��մϴ�"
				OnClick="javascript:history.back(-2)">
		</tr>
		</td>
		</tr>
		</html>
		<script language=javascript>
			self.window.alert("�Է��� ���� �����Ͽ����ϴ�.");
			location.href = "board.jsp";
		</script>