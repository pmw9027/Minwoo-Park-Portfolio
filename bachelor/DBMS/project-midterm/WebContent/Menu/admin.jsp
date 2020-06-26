<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Admin 메뉴</title>
</head>
<body>
	<%
		//
		String Session_BRANCH = (String) session
				.getAttribute("session_BRANCH");
		String Session_POSITION = (String) session
				.getAttribute("session_POSITION");
		String Session_NAME = (String) session.getAttribute("session_NAME");
	%>
	<table width="800" height="200">
		<tbody>
			<tr height="20%">

				<td width="40%" height="10%"><img src="../img/main.JPG"
					onclick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')"
					width="600" style='cursor: pointer'><br>
			<tr>
				<td align="left">
					<li style="padding-right: 5px;"><%=Session_BRANCH%>지점 <%=Session_POSITION%>
						<%=Session_NAME%>님 안녕하세요!&nbsp;" <input type="button" value="로그아웃"
						style='cursor: pointer'
						onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')">
				</td>
				</li>
			<tr height="50">
				<table height=600 width=800 align=center>
					<td align=center><img src="../img/allow.JPG" width="500"
						onclick="javascript:location.replace('http://localhost:8080/NPNC/Admin/Allow/allow.jsp')"
						style='cursor: pointer'></td>
					<td align=center><img src="../img/member.JPG" width="500"
						onclick="javascript:location.replace('http://localhost:8080/NPNC/Admin/member/member.jsp')"
						style='cursor: pointer'></td>
					<tr>
						<td align=center><img src="../img/branchinfo.JPG" width="500"
							onclick="javascript:location.replace('http://localhost:8080/NPNC/Admin/Branch/branch.jsp')"
							style='cursor: pointer'></td>
						<td align=center><img src="../img/board.JPG" width="500"
							onclick="javascript:location.replace('http://localhost:8080/NPNC/Board/board.jsp')"
							style='cursor: pointer'></td>
					</tr>
				</table>
			</tr>
		</tbody>
	</table>
</body>
</html>