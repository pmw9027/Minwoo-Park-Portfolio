<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<jsp:useBean id="allow" scope="request" class="Admin.allowDao" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>메뉴</title>
</head>
<body>
	<table width="800" height="200">
		<tbody>
			<tr height="20%">
				<td width="40%" height="10%"><span><img
						src="http://i57.tinypic.com/b4hv29.gif"
						alt="Image and video hosting by TinyPic" width="600"></span><br>
				</td>
			<tr height="50">
				<table>
					<tr>

						<th width="73">번호
						</td>
						<th width="379">차종
						</td>
						<th width="73">고객
						</td>
						<th width="164">차번호
						</td>
						<th width="58">신청일
						</td>
					</tr>
					<%
						for (Admin.allow ab : allow.getList()) {
					%>
					<tr>

						<td><%=ab.getNumber()%></td>
						<td><%=ab.getCar_kind()%></td>
						<td><%=ab.getCustom()%></td>
						<td><%=ab.getR_car()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</tr>
		</tbody>
	</table>
</body>
</html>