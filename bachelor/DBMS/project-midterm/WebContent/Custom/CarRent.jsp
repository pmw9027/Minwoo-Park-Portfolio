<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
     <%request.setCharacterEncoding("euc-kr"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<jsp:useBean id="search" scope="request" class="Custom.SearchDao" />
<%String Session_ID =(String)session.getAttribute("session_ID"); 
String DATE = (String)request.getParameter("date");
String AREA = (String)request.getParameter("area");
int DAY =  Integer.parseInt(request.getParameter("day"));
int CARNUM = Integer.parseInt(request.getParameter("radio"));
search.InsertRent(Session_ID, CARNUM, DATE, DAY, AREA);
%>
</head>
<body>

<table width="800" height="200">
		<tbody>
			<tr height="20%">
				<td width="40%" height="10%"><span><img
						src="http://i57.tinypic.com/b4hv29.gif"
						alt="Image and video hosting by TinyPic" width="600"onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')"></span><br>
						
					
				</td>
			</tr>
			<tr height="50">
			<td>
			<img src="rentS.png">
					<table width="100%">
					
				<tr>
				<td><input type="button" value="돌아가기"  onclick =	"javascript:location.replace('http://localhost:8080/NPNC/Menu/custom.jsp')">
				</td>
				</tr>		
						

					</table>
			</td>
			</tr>
		</tbody>
		
	</table>
</body>
</html>