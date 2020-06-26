<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Lee" scope="request" class="Custom.SearchDao" />
<%
	String pwd1 = (String) request.getParameter("password1");
	String Session_ID = (String) session.getAttribute("session_ID");
	String pwd2 = (String) request.getParameter("password2");
%>
<%
	String ID = (String) request.getParameter("idset");
	String PWD = (String) request.getParameter("password1");
	String NAME = (String) request.getParameter("Name");
	String Phone = (String) request.getParameter("phone");
	String ADDRESS = (String) request.getParameter("address");
	String LICENSE = (String) request.getParameter("license");
	int num = Lee.adjustInfo(NAME, PWD, Phone, ADDRESS, LICENSE,
			Session_ID);
%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<script>
		
	<%if (pwd1.equals(pwd2)) {%>
		
	<%if (num == 1) {%>
		alert("수정하셨습니다.");
		location.href = "http://localhost:8080/NPNC/Custom/MyInfo.jsp";
	<%} else {%>
		alert("수정 오류입니다. 고객센터에 전화주세요 010-4264-8964");
		location.href = "http://localhost:8080/NPNC/Custom/Infoadjust.jsp";
	<%}%>
		
	<%}
			if (!pwd1.equals(pwd2) || pwd1.equals("") || pwd2.equals("")) {%>
		alert("비밀번호를 확인해주세요.")
		location.href = "http://localhost:8080/NPNC/Custom/Infoadjust.jsp";
	<%}%>
		
	</script>
</body>
</html>