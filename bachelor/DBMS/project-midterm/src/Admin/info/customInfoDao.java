package Admin.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Home.home;

public class customInfoDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;

	public customInfo customInfo(String id) {
		customInfo ab = new customInfo();
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "Select c_name,c_pnum,c_addres,c_license from tb_custom where l_id='"
					+ id + "'";
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			ab.setAdrres(rs.getString(3));
			ab.setName(rs.getString(1));
			ab.setPhone(rs.getString(2));
			ab.setLicense(rs.getString(4));

		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("Driver load에 에러가 있습니다.\n" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("conn에 에러가 있습니다.\n" + ex.getMessage());
			ex.printStackTrace();
		}
		return ab;
	}

}
