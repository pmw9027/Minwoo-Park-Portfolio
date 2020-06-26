package Home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class home {

	public static String Login_sessin = null;
	public final static String server = "121.168.72.142:3306";
	public final static String database = "car";
	public final static String user_name = "mw";
	public final static String dbpassword = "goodmusic";

	PreparedStatement ps = null;
	Statement st;
	Connection con = null;
	ResultSet rs = null;

}
