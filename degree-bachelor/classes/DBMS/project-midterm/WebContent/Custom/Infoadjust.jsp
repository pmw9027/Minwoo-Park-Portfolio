<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"  %>
<%request.setCharacterEncoding("euc-kr");  // 한글안깨지게해줌%>
<%
String Session_ID =(String)session.getAttribute("session_ID");
String Session_PWD = (String)session.getAttribute("session_PWD");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
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
		 alert("중복확인 버튼을 눌러주세요");
		 onsubmit("return false");
		 return false;
	 } */
	 
	 if(pwd.length<6 || pwd==null){
		  alert("비밀번호는 6자 이상으로 ^^");
		  onsubmit="return false";
		  
		  return false;
	}
	 
	if (pwd2.length<6 || pwd2==null) {
		  alert("확인비밀번호를 입력하세요");
		  onsubmit="return false";
		  return false;
		 }
	 if (pwd1 != pwd2) {
		 alert("비밀번호가 서로 다릅니다.");
		 onsubmit="return false";
		 return false;
		 } 
	 
	if(NAME.length <2 || NAME==null){
		  alert("이름을 바르게 입력하세요");
		  onsubmit="return false";
		  return false;
	 }

	 if(Pho1.length <4 || Pho1==null ){
		  alert("핸드폰번호를 바르게 입력하세요");
		  onsubmit="return false";
		  return false;
	 }
	if(Address.length <5 || Address ==null){
		  alert("주소를 바르게 입력하세요");
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
    비밀번호 : <input type="password" size="15" maxlength="20" name="password1"><br><br>
    비밀번호 확인 : <input type="password" size="15" maxlength="20" name="password2"><br><br>
    이름 : <input type="text" size="13" maxlength="12" name="Name"><br><br>
    휴대폰 :  <input type="text" size="15" name="phone"><br><br>
    주소 : <input type="text" name="address" size="15" maxlength="15"><br> <br> 
    면허증 : <input type="text" name="license" size="15" maxlength="15">
   </td>
  </tr>
  <tr>
  <td align ="center"><br>
  <input type="submit" value="수정" onclick = "return CheckSubmit()">&nbsp;
  <input type="button" value="회원탈퇴" onclick ="javascript:location.replace('http://localhost:8080/NPNC/Custom/DeleteProc.jsp')">>&nbsp;
  <input type="button" value="취소" onclick ="no()">
  
  <tr>
   <td height="60%" align="left">
   </td>
  </tr>

 </table>
 </form>
</body>
</html>
