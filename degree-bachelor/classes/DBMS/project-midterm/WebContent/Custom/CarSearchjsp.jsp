<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%request.setCharacterEncoding("euc-kr"); %>


</script>
</head>
<body>
	<%
     
      String Session_NAME = (String) session.getAttribute("session_NAME");
   %>
	<form name=form action="CarSearchInfo.jsp" method="post">
		<table width="500" height="200">
			<tbody>
				<tr height="20%">
					<td width="40%" height="10%"><span><img
							src="http://i57.tinypic.com/b4hv29.gif"
							onclick="javascript:location.replace('http://localhost:8080/NPNC/Menu/custom.jsp')"
							alt="Image and video hosting by TinyPic" width="600"></span><br>
					</td>
				</tr>
				<tr>
					<td align="left">
						<li style="padding-right: 5px;"><%=Session_NAME%>��
							�ȳ��ϼ���!&nbsp;" <input type="button" value="�α׾ƿ�"
							style='cursor: pointer'
							onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')">
					</li>
					</td>
				</tr>
				<tr height="50">
					<table border=1 height=0 width=500 align=center>
						<tr>
							<td width="400" height="300" valign="top"><a><img
									src="http://i57.tinypic.com/y341e.png"
									alt="Image and video hosting by TinyPic"></a> <br> <br>
								<font face="sans-serif" size="3"> �뿩�Ⱓ(��-��-��)<br> <select
									name="date">
										<option>2015-06-01
										<option>2015-06-02
										<option>2015-06-03
										<option>2015-06-04
										<option>2015-06-06
										<option>2015-06-07
										<option>2015-06-08
										<option>2015-06-09
										<option>2015-06-10
										<option>2015-06-11
										<option>2015-06-12
										<option>2015-06-13
										<option>2015-06-14
										<option>2015-06-15
								</select> <br> <br> <br> �뿩 ��(day) <br> <input
									type=text name=day size=3>
							</font></td>


							<td width="400" height="300" valign="top"><a><img
									src="http://i61.tinypic.com/2ywg56d.png"
									alt="Image and video hosting by TinyPic"></a> <font
								face="sans-serif" size="3"> <br> <br> �뿩����<br>
									<select name="area">
										<option>����
										<option>�뱸
										<option>����
										<option>�λ�
										<option>��õ
								</select> <br> <br>
							</font> <br> <font face="sans-serif" size="1"> ����� �������� ���񽺸�
									�̿��ϰ��ֽ��ϴ� <br> *�������� ���񽺶�? <br> �������� ���񽺴� ������ ��ô°�����
									���� ���ⷻ��ī�� ã�ư��� �����Դϴ�.
							</font></td>

							<br>





							<td width="400" height="300" valign="top"><img
								src="http://i62.tinypic.com/zxpil4.png"
								alt="Image and video hosting by TinyPic"></a> <font
								face="sans-serif" size="3"> <br> <br> ����<br>
									<select name="kind">
										<option>SUV
										<option>����
										<option>����
								</select> <br> <br> <br> <br> <br> <br> <br>
									<br> &nbsp;&nbsp;<input type="submit" value="ã��">
									<br> &nbsp;<input type="button" value="Ȩ���� ���ư���"
									onclick="javascript:location.replace('http://localhost:8080/NPNC/Menu/custom.jsp')">


							</font>
					</table>


				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>