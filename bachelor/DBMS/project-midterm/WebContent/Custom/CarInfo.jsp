<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<jsp:useBean id="search" scope="request" class="Custom.SearchDao" />
<title>Insert title here</title>
</head>
<body>
	<%
		int radio = Integer.parseInt(request.getParameter("radiobox"));
		String Carname = null;
		String Carcolor = null;
		String DATE = (String) request.getParameter("date");
		String AREA = (String) request.getParameter("area");
		int DAY = Integer.parseInt(request.getParameter("day"));
	%>
	<form action="CarRent.jsp" method="POST" name="CarSearch">
		<table width="800" height="200">
			<tbody>
				<tr height="20%">
					<td width="40%" height="10%"><span><img
							src="http://i57.tinypic.com/b4hv29.gif"
							alt="Image and video hosting by TinyPic" width="600"onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')"></span><br>
					</td>
				</tr>
				<tr height="50">
					<table width="500" height="500">
						<tr>
							<td>
								<%
									search.SetColorName(radio);

									int i = 0;
									for (Custom.Search sc : search.getList()) {
								%> <%
 	i++;
 %> <%
 	Carname = sc.getCar_kind();
 %> <%
 	Carcolor = sc.getcolor();
 %> <%
 	}
 %> <%
 	if (Carname.equals("��Ƽ��")) {
 %>
							
							<td><img src="CarMTG.JPG"></td>

							<%
								}
							%>

							<%
								if (Carname.equals("SM5")) {
							%>
							<td><img src="SM5.jpg"></td>

							<%
								}
							%>

							<%
								if (Carname.equals("ī�Ϲ�")) {
							%>
							<td><img src="carnival.jpg"></td>

							<%
								}
							%>

							<%
								if (Carname.equals("K5")) {
							%>
							<td><img src="K7.jpg"></td>

							<%
								}
							%>

							<%
								if (Carname.equals("�ڶ���")) {
							%>
							<td><img src="Corando.jpg"></td>

							<%
								}
							%>

							<%
								if (Carname.equals("�Ǿ�Ʈ")) {
							%>
							<td><img src="Piate.jpg"></td>

							<%
								}
							%>

							<%
								if (Carname.equals("Ƽ��")) {
							%>
							<td>Ƽ�ڴ� �̹��� �������� �ʽ��ϴ�</td>

							<%
								}
							%>


							<%
								if (Carname.equals("�ҳ�Ÿ")) {
							%>
							<td>�ҳ�Ÿ�� �̹��� �������� �ʽ��ϴ�</td>

							<%
								}
							%>
							
																			<%
								if (Carname.equals("��Ÿ��")) {
							%>
							<td><img src="Santa.jpg"></td>

							<%
								}
							%>
		
							<td></td>
							</td>
						</tr>
					</table>
				<tr>
					<td><input type=hidden name=date value=<%=DATE%>> <input
						type=hidden name=area value=<%=AREA%>> <input type=hidden
						name=day value=<%=DAY%>> <input type=hidden name=radio
						value=<%=radio%>> <input type="submit" value="��Ʈ�ϱ�">&nbsp;
						<input type="button" value="�ڷΰ���"
						onclick="javascript:location.replace('http://localhost:8080/NPNC/Custom/CarSearchjsp.jsp')">
					</td>
				</tr>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>