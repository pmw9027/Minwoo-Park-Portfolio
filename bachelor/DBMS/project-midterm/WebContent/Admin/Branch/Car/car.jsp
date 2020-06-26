<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<jsp:useBean id="branch_car" scope="request"
	class="Admin.branch.branch_carDao" />

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
	$(document)
			.ready(
					function() {

						console.log("1234");
						$("#c_name")
								.click(
										function() {
											window
													.open(
															"http://localhost:8080/NPNC/Admin/custom_info/custom_info.jsp?idx=#c_name",
															"name",
															"width=500px,height=300px,left=100px,top=100px");
											window.focus();
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
	<span><img src="http://i57.tinypic.com/b4hv29.gif"
		alt="Image and video hosting by TinyPic" width="600"
		onClick="javascript:location.replace('http://localhost:8080/NPNC/Menu/admin.jsp')">
		<span />
		<form action="allow_submit.jsp" method="POST">
			<table width="800" height="200">
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
										href="http://localhost:8080/NPNC/Admin/member/member.jsp">반납수락</a></td>
								</tr>
								<tr>
									<td align="center"><a
										href="http://localhost:8080/NPNC/Board/board.jsp">게시판</a></td>
								</tr>
							</table>
						</td>
						<td width="40">
							<table width="100%">
								<thead>
									<tr
										style="background: url('table_mid.gif') repeat-x; text-align: center;">
										<th width="5"></th>
										<th align=center width="70">차번호</th>
										<th align=center width="73">차종</th>
										<th align=center width="70">차색</th>
										<th align=center width="100">대여상태</th>
									</tr>

								</thead>
								<tbody>
									<%
										String Session_ID = (String) session.getAttribute("session_ID");
										branch_car.branch_carinfo(Session_ID);
										for (Admin.branch.branch_car ab : branch_car.getList()) {
									%>

									<tr>
										<td><input type=checkbox name="checkbox"
											value="<%=ab.getNum()%>"></td>
										<td align="center" href="#"
											onclick="window.open('http://localhost:8080/NPNC/Admin/custom_info/custom_info.jsp?idx=<%=ab.getKind()%>', '', 'width=400, height=430'); return false;"
											style='cursor: pointer'background-color: #F9EDA5;><%=ab.getNum()%></td>
										<td align=center><%=ab.getKind()%></td>
										<td align=center><%=ab.getColor()%></td>
										<td align=center><%=ab.getCheck()%></td>
									</tr>
								</tbody>
								<%
									}
								%>

							</table>
						</td>
					<tr align="center" bgcolor="#FFFFFF" height="30">
					</tr>
					<tr height="25" align="center">
						<td>&nbsp;</td>
					</tr>
					<tr height="1" bgcolor="#D2D2D2">
						<td colspan="6"></td>
					</tr>

					<tr height="1" bgcolor="#82B5DF">
						<td colspan="6" width="752"></td>
					</tr>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="4" height="5"></td>
						</tr>
						<tr align="center">
							<input type="submit" value="확인">
						</tr>
					</table>

				</tbody>
			</table>
		</form>
</body>
</html>