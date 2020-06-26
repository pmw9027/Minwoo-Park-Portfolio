package Login;

public class Login {
	private String userid;
	private String passwd;

	final String _userid = "myuser";
	final String _passwd = "1234";

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserid() {
		return userid;
	}

	public String getPasswd() {
		return passwd;
	}
}
