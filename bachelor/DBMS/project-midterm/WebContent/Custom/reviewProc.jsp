<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script>
<% String pwd1 = (String)request.getParameter("password");
String pwd2 = (String)request.getParameter("password2");%>

<% 
if(!pwd1.equals(pwd2)) {%>
alert("��й�ȣ ����!");
location.href="http://localhost:8080/NPNC/Custom/MyinfoReview.jsp";
<%} if(pwd1.equals(pwd2)) {%>
location.href="http://localhost:8080/NPNC/Custom/Infoadjust.jsp";

<% }%>
</script>
</body>
</html>