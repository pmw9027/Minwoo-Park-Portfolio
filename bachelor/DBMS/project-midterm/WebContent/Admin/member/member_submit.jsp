<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="Admin.allowDao"%>
<jsp:useBean id="admin" scope="request" class="Admin.allowDao" />
<%
	request.setCharacterEncoding("euc-kr");
	String[] checkbox = request.getParameterValues("checkbox");
	admin.member_submitDao(checkbox);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%
		for (int i = 0; i < checkbox.length; i++) {
	%>
	<h1>
		<%=checkbox[i]%>
	</h1>
	<%
		}
	%>

	<script language=javascript>
		self.window.alert("üũ�ƿ� �Ϸ�Ǿ����ϴ�");
		location.href = "member.jsp";
	</script>
</body>
</html>