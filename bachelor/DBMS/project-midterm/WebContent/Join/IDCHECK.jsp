<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "Que.Query" %>
    <jsp:useBean id="idcheck" class="Que.Query"/>
    <%

 String id = (String)request.getParameter("id");
 int number = idcheck.IdCheck(id);

%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

</head>
<body>
<form action ="Join2.jsp">
 <table align=center width="300" height="300" >
 <tr>
 <td>
<%
if(id == null){
%><td align=center>
���̵� �Է����ּ���.

<input type="submit" value="Ȯ��"
onClick="self.close()"> 
<% }

else if(id.length()<6){
%><td align=center>
���̵�� 6�� �̻����� ^^
<br>
<input align=center type="submit" value="Ȯ��" 
onClick="self.close()"> 
<% }


else if(number == 1){
%><td align=center>
�̹� �ִ� ���̵��Դϴ�.
<br>
<input type="submit" value="Ȯ��"
onClick="self.close()"> 
<%}else{ %><td align=center>
����ϼŵ� �Ǵ� ���̵��Դϴ�.

<br>
<input type="submit" value="Ȯ��"
onClick="self.close()"> 
<%} %>


 </td>
 </tr>
 </table>
 </form>
</body>
</html>