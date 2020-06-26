<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="Board.*"%>
<jsp:useBean id="EUN" class="Board.viewDao" />
<%
	int idx = Integer.parseInt(request.getParameter("idx"));
	EUN.viewDao_abc(idx);
	board bo = EUN.getList();
%>
<script language="javascript">
	// 자바 스크립트 시작
	function replyCheck() {
		var form = document.relpyform;
		if (!form.reply.value) // form 에 있는 name 값이 없을 때
		{
			alert("댓글을 적어주세요"); // 경고창 띄움
			form.reply.focus(); // form 에 있는 name 위치로 이동
			return;
		}

		form.submit();
	}
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>게시판</title>
</head>
<body>
	</tr>
	<tr>
		<table>
			<form action="http://localhost:8080/NPNC/Board/reply_ok.jsp?idx=<%=idx%>"
				name="form" method="post" onsubmit="replyCheck()">
				<tr>
					<td>
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr
								style="background: url('table_mid.gif') repeat-x; text-align: center;">
								<td width="5"><img src="img/table_left.gif" width="5"
									height="30" /></td>
								<td>답 글 쓰 기</td>
								<td width="5"><img src="table_right.gif" width="5"
									height="30" /></td>
							</tr>
						</table>
						<table width="413">
							<tr>
								<td>&nbsp;</td>
								<td align="center">글번호</td>
								<td><input type=hidden name="title" size="50"
									maxlength="100"><%=idx%></td>
								<td>&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td align="center">이름</td>
								<td><input type=hidden name="name" size="50" maxlength="50"><%=bo.getUser()%></td>
								<td>&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td align="center">날짜</td>
								<td><input type=hidden name="date" size="50" maxlength="50"><%=bo.getDate()%></td>
								<td>&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td align="center">내용</td>
								<td><intput type=hidden name="memo" cols="50" rows="13"><%=bo.getContent()%></intput></td>
								<td>&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4" width="407"></td>
							</tr>
							<td>&nbsp;</td>
							<td align="center">REPLY</td>
							<td><textarea name="reply" cols="50" rows="13"></textarea></td>
							<td>&nbsp;</td>
							</tr>
							<tr height="1" bgcolor="#dddddd">
								<td colspan="4" width="407"></td>
							</tr>

							<tr height="1" bgcolor="#82B5DF">
								<td colspan="4" width="407"></td>
							</tr>
							<tr align="center">
								<td width="0">&nbsp;</td>
								<td colspan="2" width="399"><input type=submit value="리플달기"
									OnClick="javascript:replyCheck()";> <input type=button
									value="목록" OnClick="window.location='board.jsp'">
								<td width="0">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
		</table>
</body>
</html>