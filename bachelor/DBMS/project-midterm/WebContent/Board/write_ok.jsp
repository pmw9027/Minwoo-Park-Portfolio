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
<title>게시판</title>
</head>
<body>
	<table width="1000" height="300" border=1>
		<tr>
			<td align="center"><%=(String) request.getParameter("name")%> <%
 	if (num == 1) {
 %> 님의 글을 저장하였습니다. <%
 	} else {
 %> 글저장오류 고객센터에 문의해주시길 바랍니다. <%
 	}
 %> <br> <br> <br> <br> <br> <br>
		<tr align="center">
			<input type=button value="4초후에 게시판목록으로 이동합니다"
				OnClick="javascript:history.back(-2)">
		</tr>
		</td>
		</tr>
		</html>
		<script language=javascript>
			self.window.alert("입력한 글을 저장하였습니다.");
			location.href = "board.jsp";
		</script>