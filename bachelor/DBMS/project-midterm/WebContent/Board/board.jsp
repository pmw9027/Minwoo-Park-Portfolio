<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<jsp:useBean id="board" class="Board.boardDao" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
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
	<%
		//db연동 소스 넣어야됨
	%>
	<span><img src="http://i57.tinypic.com/b4hv29.gif"
		alt="Image and video hosting by TinyPic" width="600">
		<span />
		<form action="member_submit.jsp" method="POST">
			<table width="800" height="200">
				<tbody>
					<tr height="50">
						
						<td width="40">
							<table width="100%">
								<thead>
									<tr
										style="background: url('table_mid.gif') repeat-x; text-align: center;">
										<th width="5"></th>
										<th width="50">번호</th>
										<th width="379">제목</th>
										<th width="73">작성자</th>
										<th width="164">작성일</th>
										<th width="7"></th>
									</tr>
								</thead>
								<tbody>
									<%
										for (Board.board ab : board.getList()) {
									%>
									<tr height="25" align="center">
										<td>&nbsp;</td>
										<td align="left"><%=ab.getNumber()%></td>
										<td align="center"><a a
											href="view.jsp?idx=<%=ab.getNumber()%>"><%=ab.getTitle()%></td>
										<td align="center"><%=ab.getUser()%></td>
										<td align="center"><%=ab.getDate()%></td>
										<td>&nbsp;</td>
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
							<td><input type=button value="글쓰기"
								OnClick="window.location='write.jsp'"></td>
						</tr>
					</table>

				</tbody>
			</table>
		</form>
</body>
</html>
