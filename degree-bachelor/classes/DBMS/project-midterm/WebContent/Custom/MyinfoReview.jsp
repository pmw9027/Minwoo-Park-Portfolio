<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>비밀번호확인</title>
<script type="text/javascript">

<%request.setCharacterEncoding("euc-kr"); %>
function noo(){
	document.location.replace('http://localhost:8080/NPNC/Menu/custom.jsp');
	
}
var pwd = document.form1.password.value;
function Check(){



	 if(pwd != password2 || pwd ==null){
		  alert("비밀번호 오류입니다");
		  onsubmit="return false";
		  return false;
		 }
	 <%request.setCharacterEncoding("euc-kr");  // 한글안깨지게해줌%>





	 </script>

<%
String Session_ID =(String)session.getAttribute("session_ID");
String Session_PWD = (String)session.getAttribute("session_PWD");
%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Custom 메뉴</title>
</head>
<body>
	<%
     
      String Session_NAME = (String) session.getAttribute("session_NAME");
   %>
	<form action="http://localhost:8080/NPNC/Custom/reviewProc.jsp"
		name="form1" method="post" onsubmit="Check()">
		<table width="500" height="200">
			<tbody>
				<tr height="20%">
					<td width="40%" height="10%"><span><img
							src="http://i57.tinypic.com/b4hv29.gif"
							onclick="javascript:location.replace('http://localhost:8080/NPNC/Menu/custom.jsp')"
							alt="Image and video hosting by TinyPic" width="600"></span><br>
					</td>
				</tr>
				<tr>
					<td align="left">
						<li style="padding-right: 5px;"><%=Session_NAME%>님
							안녕하세요!&nbsp;" <input type="button" value="로그아웃"
							style='cursor: pointer'
							onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')">
					</li>
					</td>
				</tr>

				<table height=300 width=300 align=center border=3>
					<tr>
						<td align=center>보안을 위해서 비밀번호를 입력해주시길 바랍니다. <br> 비밀번호 :
							<input type="password" size="15" maxlength="20" name="password">
							<br> <br> <input type=hidden name=password2
							value=<%=Session_PWD%>> <input type="submit" value="확인 "
							onclick="return Check() return false;">&nbsp; <input
							type="button" value="돌아가기" onclick="no()">


						</td>
					</tr>


				</table>

			</tbody>
		</table>
	</form>
</body>
</html>