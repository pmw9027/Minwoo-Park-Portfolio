<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"  %>
<%request.setCharacterEncoding("euc-kr");  // �ѱ۾ȱ���������%>
<%
String Session_ID =(String)session.getAttribute("session_ID");
String Session_PWD = (String)session.getAttribute("session_PWD");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ������</title>
<script type="text/javascript">

function no(){
	document.location.replace('http://localhost:8080/NPNC/Home/home.jsp');
	
}

function IDcheck(){
	var id =document.form.idset.value;
	var url ="IDCHECK.jsp?id="+id;	 
	window.open(url,'idcheck','width=300,height=300,toolbar=no,menubar=no,location=no,scrollbars=no,status=no');
}

	



function CheckSubmit(){
	var id = document.form.idset.value;
	var idform = /^[a-zA-Z0-9]*$/;
	var pwd = document.form.password1.value;
	var pwd2 = document.form.password2.value;
	var NAME = document.form.name.value;
	var Pho1 = document.form.phone.value;
	var Address = document.form.address.value;

	
/* 	 if(!id.length==0 || !url){
		 alert("�ߺ�Ȯ�� ��ư�� �����ּ���");
		 onsubmit("return false");
		 return false;
	 } */
	 
	 if(pwd.length<6 || pwd==null){
		  alert("��й�ȣ�� 6�� �̻����� ^^");
		  onsubmit="return false";
		  
		  return false;
	}
	 
	if (pwd2.length<6 || pwd2==null) {
		  alert("Ȯ�κ�й�ȣ�� �Է��ϼ���");
		  onsubmit="return false";
		  return false;
		 }
	 if (pwd1 != pwd2) {
		 alert("��й�ȣ�� ���� �ٸ��ϴ�.");
		 onsubmit="return false";
		 return false;
		 } 
	 
	if(NAME.length <2 || NAME==null){
		  alert("�̸��� �ٸ��� �Է��ϼ���");
		  onsubmit="return false";
		  return false;
	 }

	 if(Pho1.length <4 || Pho1==null ){
		  alert("�ڵ�����ȣ�� �ٸ��� �Է��ϼ���");
		  onsubmit="return false";
		  return false;
	 }
	if(Address.length <5 || Address ==null){
		  alert("�ּҸ� �ٸ��� �Է��ϼ���");
		  onsubmit="return false";
		  return false;
	 }
	
	 
}

</script>



</head>
<body>
<form action ="http://localhost:8080/NPNC/Custom/adjustProc.jsp" name ="form" method ="post" onsubmit ="CheckSubmit()">
 <table width="1400" height="650" >
  <tr>
   <td width="0%" height="10%" ><img src="http://i57.tinypic.com/b4hv29.gif" border="0" alt="Image and video hosting by TinyPic" width="290" height="100" border="0"
    align="middle">&nbsp;
    <br>
    <hr>
   </td>
  </tr>
  <tr>
   <td height="60%" align="left">
  ID : <%=Session_ID %>  <br><br>
    ��й�ȣ : <input type="password" size="15" maxlength="20" name="password1"><br><br>
    ��й�ȣ Ȯ�� : <input type="password" size="15" maxlength="20" name="password2"><br><br>
    �̸� : <input type="text" size="13" maxlength="12" name="Name"><br><br>
    �޴��� :  <input type="text" size="15" name="phone"><br><br>
    �ּ� : <input type="text" name="address" size="15" maxlength="15"><br> <br> 
    ������ : <input type="text" name="license" size="15" maxlength="15">
   </td>
  </tr>
  <tr>
  <td align ="center"><br>
  <input type="submit" value="����" onclick = "return CheckSubmit()">&nbsp;
  <input type="button" value="ȸ��Ż��" onclick ="javascript:location.replace('http://localhost:8080/NPNC/Custom/DeleteProc.jsp')">>&nbsp;
  <input type="button" value="���" onclick ="no()">
  
  <tr>
   <td height="60%" align="left">
   </td>
  </tr>

 </table>
 </form>
</body>
</html>
