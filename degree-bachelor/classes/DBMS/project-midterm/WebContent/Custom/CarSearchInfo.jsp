<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">



</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<jsp:useBean id="search" scope="request" class="Custom.SearchDao" />
<title>Insert title here</title>
</head>
<%request.setCharacterEncoding("euc-kr"); %>
<%
String AREA = (String)request.getParameter("area");
String KIND = (String)request.getParameter("kind");
String DATE = (String)request.getParameter("date");
int DAY =  Integer.parseInt(request.getParameter("day"));
%>
<body>
			<form action="CarInfo.jsp" method="POST" name = "CarSearch">
	<table width="800" height="200">
		<tbody>
			<tr height="20%">
				<td width="40%" height="10%"><span><img
						src="http://i57.tinypic.com/b4hv29.gif"
						alt="Image and video hosting by TinyPic" width="600"onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')"></span><br>
						
						<input type = hidden name = date value =<%=DATE %> >
						<input type = hidden name = day value =<%=DAY %> >
						<input type = hidden name = area value =<%=AREA %> >
				</td>
			</tr>
			<tr height="50">
					<table width="100%">

						<tr>
							<th width="5"><img src="img/table_left.gif" width="5"
								height="30" /></th>
							<th align=center width="70">차이름</th>
							<th align=center width="70">차색상</th>
							<th align=center width="73">연비</th>
							<th align=center width="70">가격</th>
						</tr>
		
											<%
											search.Query(AREA,KIND);
		
							int i = 0;
							for (Custom.Search sc : search.getList()) {
						%>
							<%i++;%>
						<tr>
							<td><input type=radio name="radiobox" value =<%=sc.getNumber()%>></td>
							<td align=center><%=sc.getName()%>
							<td align=center><%=sc.getcolor()%></td>
							<td align=center><%=sc.getfuel()%></td>
							<td align=center><%=sc.getPrice()%></td>
							</tr>
						<%
							}
						%>
						
						
	
						
						
					</table>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="4" height="5"></td>
						</tr>
						<tr>
							<p align=center>
								<input type="submit" value="상세정보">&nbsp;
								<input type="button" value="뒤로가기"  onclick ="javascript:location.replace('http://localhost:8080/NPNC/Custom/CarSearchjsp.jsp')">
						</tr>
					</table>
			
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>