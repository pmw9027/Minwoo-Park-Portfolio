<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   <%@ page import = "java.sql.*" %>
<%@ page import = "Que.Query" %>
    <jsp:useBean id="Lee" class="Que.Query"/>
   
    <%request.setCharacterEncoding("euc-kr"); 
    String ID = (String)request.getParameter("idset");
    String PWD = (String)request.getParameter("password1");
    String PWD2 = (String)request.getParameter("password2");
    String NAME = (String)request.getParameter("Name");
	String Phone = (String)request.getParameter("phone");
	String ADDRESS =(String)request.getParameter("address");
	String LICENSE =(String)request.getParameter("license");
	String Checknumber = (String)request.getParameter("CHECK");
	int number = Lee.IdCheck(ID);
	int roup=0;
    %> 
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

</head>
<body>
 <table width="1000" height="300" border =1>
 <tr>
 <td align="center">
 <script>
 
<%if(number ==1) {%>
alert("중복체크를 해주세요.");
javascript:location.replace('http://localhost:8080/NPNC/Join/Join2.jsp');
<%}
if(ID.length() <6 ||ID.equals("") ){%>
alert("아이디는 6자 이상으로해주세요.");
javascript:location.replace('http://localhost:8080/NPNC/Join/Join2.jsp');
<%}
if(PWD.length() <6){%>
alert("패스워드는 6자 이상으로해주세요");
javascript:location.replace('http://localhost:8080/NPNC/Join/Join2.jsp');
<%}
if(PWD2.equals(PWD)){%>
<%}
else{%>
alert("확인 패스워드가 다릅니다.");
javascript:location.replace('http://localhost:8080/NPNC/Join/Join2.jsp');

<%}%>

</script>
<%if(number!=1) {%>
	<%int num =Lee.JoinInfo(ID,PWD,NAME,Phone,ADDRESS,LICENSE);%>
	<%if(num == 1){ %>

		회원가입이 정상적으로 처리되셨습니다.
		<% }else{ %>
		회원가입오류고객센터에문의해주시길 바랍니다.
		<% }}%>






	


 <br><br><br><br><br><br>

 <input type="submit" value="홈으로가기" onclick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')">&nbsp;

 </td>
 </tr>
 </table>
</body>
</html>