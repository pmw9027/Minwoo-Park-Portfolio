<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="Que.Query"%>
<jsp:useBean id="Lee" class="Que.Query" />

<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

<%//
			String ID = (String) request.getParameter("_id");
			String PWD = (String) request.getParameter("_pw");
			session.setAttribute("session_ID", ID);
			session.setAttribute("session_PWD", PWD);
			String[] sessions = new String[3];

			String msg = "";
			int num = 0;%>
	<%if (Lee.Login(ID, PWD) == 1) {

				sessions = Lee.getSession(ID,0);
				session.setAttribute("session_NAME", sessions[0]);
				msg = "로그인성공";
				num = 1;%>
	<%} else if (Lee.Login(ID, PWD) == 2) {
				sessions = Lee.getSession(ID,1);
				session.setAttribute("session_POSITION", sessions[0]);
				session.setAttribute("session_NAME", sessions[1]);
				session.setAttribute("session_BRANCH", sessions[2]);
				msg = "관리자 로그인성공";
				num = 2;%>
	<%} else if (Lee.Login(ID, PWD) == 0) {
				msg = "로그인 실패";

			}%>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</script>
</head>
<body>
	<script>
alert("<%=msg%>");
<%if (num == 1) {%>
location.href="http://localhost:8080/NPNC/Menu/custom.jsp";
<%} else if (num == 2) {%>
location.href="http://localhost:8080/NPNC/Menu/admin.jsp";
<%} else {%>
location.href="http://localhost:8080/NPNC/Home/home.jsp";
<%}%>
</script>
</body>
</html>