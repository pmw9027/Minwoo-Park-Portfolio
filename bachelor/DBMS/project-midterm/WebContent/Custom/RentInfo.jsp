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
<%
	request.setCharacterEncoding("euc-kr");
%>
<%
	String Session_ID = (String) session.getAttribute("session_ID");
%>
<body>
	<%
		String Session_NAME = (String) session.getAttribute("session_NAME");
	%>
	<form action="CarInfo.jsp" method="POST" name="CarSearch">
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
				<tr height="50">
					<table width="100%">

						<tr>
							<th width="5"><img src="img/table_left.gif" width="5"
								height="30" /></th>

							<th align=center width="70">지점</th>
							<th align=center width="70">차종류</th>
							<th align=center width="73">렌트날짜</th>
							<th align=center width="70">렌트시간(day)</th>
							<th align=center width="70">수락여부</th>
						</tr>

						<%
							search.MyRentInfo(Session_ID);

							int i = 0;
							for (Custom.Search sc : search.getList()) {
						%>
						<%
							i++;
						%>
						<tr>
							<th align=center width="70"></th>
							<td align=center><%=sc.getArea()%></td>
							<td align=center><%=sc.getCar_kind()%></td>
							<td align=center><%=sc.getDate()%></td>
							<td align=center><%=sc.getday()%></td>
							<td align=center>
								<%
									if (sc.getCheck() == 0) {
								%> X<%
									} else if (sc.getCheck() == 1) {
								%> 0 <%
									}
								%>

							</td>

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

								<input type="button" value="돌아가기"
									onclick="javascript:location.replace('http://localhost:8080/NPNC/Menu/custom.jsp')">
						</tr>
					</table>

				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>