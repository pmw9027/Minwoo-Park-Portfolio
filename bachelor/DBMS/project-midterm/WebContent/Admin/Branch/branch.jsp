<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<jsp:useBean id="allow" scope="request" class="Admin.allowDao" />
<%
	String session_id = (String) session.getAttribute("session_ID");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>메뉴</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#menu_list tr').mouseover(function() {
			$(this).addClass('selectedRow');
		}).mouseout(function() {
			$(this).removeClass('selectedRow');
		});
	});
</script>

<style>
#menu_list {
	margin-top: 80px;
}

#menu_list td {
	height: 50px;
	width: 150px;
}

.selectedRow {
	background-color: #F9EDA5;
	cursor: pointer;
}

a {
	text-decoration: none
}
</style>
</head>
<body>
	<img src="http://i57.tinypic.com/b4hv29.gif"
		alt="Image and video hosting by TinyPic" width="600"
		style='cursor: pointer'
		onclick="javascript:location.replace('http://localhost:8080/NPNC/Menu/admin.jsp')">
	<form action="allow_submit.jsp" method="POST">
		<li style="padding-right: 5px;"><%=session_id %>님 안녕하세요!&nbsp;" <input
			type="button" value="로그아웃" style='cursor: pointer'
			onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')">
			</td>
		</li>
		<table width="1400" height="200">
			<tbody>
				<tr height="50">
					<td width="20%">


						<table id="menu_list" align=center border="1" rules="none">
							<tr>
								<td align="center"><a
									href="http://localhost:8080/NPNC/Admin/Branch/branch.jsp">지점정보</a></td>
							</tr>
							<tr>
								<td align="center"><a
									href="http://localhost:8080/NPNC/Admin/Allow/allow.jsp">렌트수락</a></td>
							</tr>
							<tr>
								<td align="center"><a
									href="http://localhost:8080/NPNC/Admin/Allow/allow.jsp">반납수락</a></td>
							</tr>
							<tr>
								<td align="center"><a
									href="http://localhost:8080/NPNC/Admin/Allow/allow.jsp">게시판</a></td>
							</tr>
						</table>

					</td>

					<td align=center><img src="../../img/supervise.jpg"
						width="350"
						onclick="javascript:location.replace('http://localhost:8080/NPNC/Admin/Branch/Car/car.jsp')"
						style='cursor: pointer'></td>

					<td align=center><img src="../../img/admininfo.jpg"
						width="350"
						onclick="javascript:location.replace('http://localhost:8080/NPNC/Admin/Branch/Employee/employee.jsp')"
						style='cursor: pointer'></td>


					<td align=right><img src="../../img/record.jpg" width="350"
						onclick="javascript:location.replace('http://localhost:8080/NPNC/Admin/member.jsp')"
						style='cursor: pointer'></td>

				</tr>

			</tbody>
		</table>
	</form>

</body>
</html>