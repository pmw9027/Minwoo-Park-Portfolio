<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ȸ������ â</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">
	function chk() {
		var req = document.form.req.checked;
		var num = 0;
		if (req == true) {
			num = 1;
		}
		if (num == 1) {
			document.form.submit();

		} else {
			alert("�������� ����� �����ϼž� �մϴ�.");
		}
	}
	function nochk() {
		alert("�����Ͻʽÿ�. �������� �����ø� ���ԺҰ����մϴ�.");
	}
</script>





</head>
<body>
	<form action="Join2.jsp" name="form" method="post">

		<table width="1400" height="650">
			<tr>
				<td width="10%" height="10%"><span style="padding-left: 160px"><img
						src="http://i57.tinypic.com/b4hv29.gif" border="0"
						alt="Image and video hosting by TinyPic" width="290" height="100"
						border="0" align="middle"
						onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')"></span>&nbsp;
					<br>
					<hr></td>
			</tr>
			<tr>
				<td width="100%" height="50%" align="center">
					<p align="left">
						<span style="padding-left: 160px"> �������</span>
					</p> <br> <textarea rows="20" cols="150">��. �����ϴ� ���������� �׸�ù°, ȸ��� ȸ���� ��, ��Ȱ�� �����, ���� ������ ������ ���� ���� ȸ������ ��� �Ʒ��� ���� �ּ����� ���������� �ʼ��׸����� �����ϰ� �ֽ��ϴ�.
ȸ������
- �̸�, �������, ����, ���̵�, ��й�ȣ, ����, ����ó(�����ּ�, �޴��� ��ȣ �� ����), ������������
��14�� �̸� �Ƶ� ȸ������ 
- �̸�, �������, ����, �����븮�� ����, ���̵�, ��й�ȣ, ����ó (�����ּ�, �޴��� ��ȣ �� ����), ������������
��ü���̵� ȸ������ 
- ��ü���̵�, ȸ���, ��ǥ�ڸ�, ��ǥ ��ȭ��ȣ, ��ǥ �̸��� �ּ�, ��ü�ּ�, ������ ���̵�, ������ ����ó, ������ �μ�/����
- �����׸� : ��ǥ Ȩ������, ��ǥ �ѽ���ȣ
��°, ���� �̿�����̳� ���ó�� �������� �Ʒ��� ���� �������� �ڵ����� �����Ǿ� ������ �� �ֽ��ϴ�.
- IP Address, ��Ű, �湮 �Ͻ�, ���� �̿� ���, �ҷ� �̿� ���
��°, ���̹� ���̵� �̿��� �ΰ� ���� �� ����� ���� �̿� �Ǵ� �̺�Ʈ ���� �������� �ش� ������ �̿��ڿ� ���ؼ��� �������� �߰� ������ �߻��� �� ������, �̷��� ��� ������ ���Ǹ� �޽��ϴ�. 
��°, ����������, ����/���� �� �Ϻ� ���� �̿�� ���� ���� �ؼ��� ���� ���������� �ʿ��� ���, �Ʒ��� ���� �������� ������ �� �ֽ��ϴ�. 
- �̸�, �������, ����, �ߺ�����Ȯ������(DI), ��ȣȭ�� ������ �ĺ�����(CI), �޴��� ��ȣ(����), ������ ��ȣ(������ �̿��), ��/�ܱ��� ����
�ټ�°, ���� ���� �̿� �������� �Ʒ��� ���� ���� �������� ������ �� �ֽ��ϴ�. 
- �ſ�ī�� ������ : ī����, ī���ȣ ��
- �޴���ȭ ������ : �̵���ȭ��ȣ, ��Ż�, �������ι�ȣ ��
- ������ü�� : �����, ���¹�ȣ ��
- ��ǰ�� �̿�� : ��ǰ�� ��ȣ
��. �������� �������ȸ��� ������ ���� ������� ���������� �����մϴ�. 
- Ȩ������, ������, �ѽ�, ��ȭ, ��� �Խ���, �̸���, �̺�Ʈ ����, ��ۿ�û
- ����ȸ��κ����� ���� 
- �������� ���� ���� ���� ����
   </textarea> <br> <input type="checkbox" name="req"> �������� ���� �� �̿뿡
					�����մϴ�.
				</td>
			</tr>
			<tr>
				<td align="center" valign="top"><input type="button" value="����"
					onclick="chk()" />&nbsp;&nbsp;&nbsp; <input type="button"
					value="���"
					onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
