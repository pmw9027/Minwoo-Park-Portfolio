<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="Board.*"%>
<jsp:useBean id="EUN" class="Board.viewDao" />
<%
	int idx = Integer.parseInt(request.getParameter("idx"));
	EUN.viewDao_abc(idx);
	board bo = EUN.getList();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ���</title>
</head>
<body>
	</tr>
	<tr>
		<table>
			<tr>
				<td>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr
							style="background: url('table_mid.gif') repeat-x; text-align: center;">
							<td width="5"><img src="img/table_left.gif" width="5"
								height="30" /></td>
							<td>�� ��</td>
							<td width="5"><img src="table_right.gif" width="5"
								height="30" /></td>
						</tr>
					</table>
					<form action="reply_ok.jsp" method="POST">
						<table width="413">

							<tr>
								<td width="0">&nbsp;</td>
								<td align="center" width="76">�۹�ȣ</td>
								<td width="319"><%=idx%></td>
								<td width="0">&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4" width="407"></td>
							</tr>
							<tr>
								<td width="0">&nbsp;</td>
								<td align="center" width="76">�̸�</td>
								<td width="319"><%=bo.getUser()%></td>
								<td width="0">&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4" width="407"></td>
							</tr>
							<tr>
								<td width="0">&nbsp;</td>
								<td align="center" width="76">�ۼ���</td>
								<td width="319"><%=bo.getDate()%></td>
								<td width="0">&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4" width="407"></td>
							</tr>
							<tr>
								<td width="0">&nbsp;</td>
								<td align="center" width="76">����</td>
								<td width="319"><%=bo.getTitle()%></td>
								<td width="0">&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4" width="407"></td>
							</tr>
							<td width="0">&nbsp;</td>
							<td width="399" colspan="2" height="200"><%=bo.getContent()%>
							</tr>
							</tr>
							<td width="0">&nbsp;</td>
							<td width="413" colspan="2" height="200"><%=bo.getReply()%>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4" width="407"></td>
							</tr>

							<tr height="1" bgcolor="#82B5DF">
								<td colspan="4" width="407"></td>
							</tr>
							<tr align="center">
								<td width="0">&nbsp;</td>
								<td colspan="2" width="399"><input type=button value="�۾���"
									OnClick="window.location='write.jsp'"> <input
									type=button value="���"
									OnClick="window.location='reply.jsp?idx=<%=idx%>'"> <input
									type=button value="���" OnClick="window.location='board.jsp'">
									<input type=button value="����"
									OnClick="window.location='delete.jsp?idx=<%=idx%>'">
								<td width="0">&nbsp;</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
</body>
</html>