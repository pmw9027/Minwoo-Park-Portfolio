package Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Admin.allow;
import Home.home;

public class memberDao {

	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<allow> array = new ArrayList<allow>();

	public memberDao() {

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "Select * from tb_rent";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				allow ab = new allow();
				ab.setCar_kind(rs.getString(3));
				ab.setCustom(rs.getString(3));
				ab.setNumber(rs.getString(1));
				ab.setR_car(rs.getString(4));
				ab.setR_day(rs.getString(5));
				ab.setR_employee(rs.getString(6));
				Arrayadd(ab);
			}

		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("Driver load에 에러가 있습니다.\n" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("conn에 에러가 있습니다.\n" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void Arrayadd(allow ab) {
		array.add(ab);
	}

	public ArrayList<allow> getList() {
		return array;
	}
}
