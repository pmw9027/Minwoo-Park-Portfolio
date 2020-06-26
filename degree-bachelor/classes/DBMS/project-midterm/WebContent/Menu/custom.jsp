<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Custom 메뉴</title>
</head>
<body>
	<%
      String Session_NAME = (String) session.getAttribute("session_NAME");
   %>
	<table width="800" height="200">
		<tbody>
			<tr height="20%">

				<td width="40%" height="10%"><img src="../img/main.JPG"
					width="600" style='cursor: pointer'><br>
					</td>
			<tr>
				<td align="left">
					<li style="padding-right: 5px;">
						<%=Session_NAME%>님 안녕하세요!&nbsp;" <input type="button" value="로그아웃"
						style='cursor: pointer'
						onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')">
				</li>
				</td>
			</tr>
			<tr height="50">
				<table height=600 width=800 align=center>
					<td align=center><img src="search.JPG" width="500"
						onclick="javascript:location.replace('http://localhost:8080/NPNC/Custom/CarSearchjsp.jsp')"
						style='cursor: pointer'></td>

					<td align=center><img src="info.JPG" width="500"
						onclick="javascript:location.replace('http://localhost:8080/NPNC/Custom/MyInfo.jsp')"
						style='cursor: pointer'></td>

					<tr>

						<td align=center><img src="status.JPG" width="500"
							onclick="javascript:location.replace('http://localhost:8080/NPNC/Custom/RentInfo.jsp')"
							style='cursor: pointer'></td>

						<td align=center><img src="board.JPG" width="500"
							onclick="javascript:location.replace('http://localhost:8080/NPNC/Board/board.jsp')"
							style='cursor: pointer'></td>
							</tr>
				</table>
			</tr>
		</tbody>
	</table>
</body>
</html>