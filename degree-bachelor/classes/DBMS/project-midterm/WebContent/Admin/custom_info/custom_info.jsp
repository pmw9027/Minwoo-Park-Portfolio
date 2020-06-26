<%@page import="Admin.info.customInfo"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<jsp:useBean id="customInfoDao" scope="request"
	class="Admin.info.customInfoDao" />
<jsp:useBean id="customInfo" scope="request"
	class="Admin.info.customInfo" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<%
	String idx = (String) request.getParameter("idx");
	customInfo ab = customInfoDao.customInfo(idx);
%>
<body >
<br>
	<table align="center" background="../../img/car.jpg">
	<tr >
	<td ><strong><font color="brown" size="7"> 고객 정보</font></strong> </td>
	
	</tr>
	
		<tr>
		<tr>
			<td><strong>이름</strong></td>
			<td><font color="blue" size="5"><%=ab.getName()%></font></td>
		</tr>
		<tr>
			<td><strong>나이</strong></td>
			<td><font color="blue" size="5">24</font></td>
		</tr>
		<tr>
			<td><strong>전화번호</strong></td>
			<td><font color="blue" size="5"><%=ab.getPhone()%></font></td>
		</tr>
		<tr>
			<td><strong>주소</strong></td>
			<td><font color="blue" size="5"><%=ab.getAdrres()%></font></td>
		</tr>
		<tr>
			<td><strong>면허증</strong></td>
			<td><font color="blue" size="5"><%=ab.getLicense()%></font></td>
		</tr>
		
	</table>
	<br>
	<table align="center" >
	<tr>
	<td>
	<input type="button" value="확인" onclick="self.close()"/>
	</td></tr>
	</table>
</body>
</html>