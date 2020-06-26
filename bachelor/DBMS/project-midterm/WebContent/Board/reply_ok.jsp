<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*"%>
<%@ page import="Board.*"%>
<%@ page import="Que.Query"%>
<jsp:useBean id="EUN" class="Que.Query" />
<%
	request.setCharacterEncoding("euc-kr");
int idx = Integer.parseInt(request.getParameter("idx"));
	String reply = (String) request.getParameter("reply");
	int num = EUN.replyDao(idx,reply);
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
<script language=javascript>
			self.window.alert("댓글을 저장하였습니다.");
			location.href = "board.jsp";
	</script>