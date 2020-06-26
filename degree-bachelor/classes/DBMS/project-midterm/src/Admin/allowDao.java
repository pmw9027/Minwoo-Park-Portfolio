package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Home.home;

public class allowDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<allow> array = new ArrayList<allow>();

	public allowDao() {

	}

	public void allow_add(String Session_ID) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "SELECT tb_rent.r_no,tb_rent.r_custom,tb_rent.r_car,tb_rent.r_rdate,tb_carinfo.c_kind FROM tb_rent,tb_carinfo WHERE tb_rent.r_branch=(select e_branch from tb_employee where l_id ='"
					+ Session_ID
					+ "')AND r_cdate IS NULL and tb_rent.r_car = tb_carinfo.c_num";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				allow ab = new allow();
				ab.setCar_kind(rs.getString(5));
				ab.setCustom(rs.getString(2));
				ab.setNumber(rs.getString(1));
				ab.setR_car(rs.getString(3));
				ab.setR_day(rs.getString(4));
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

	public void member_add(String Session_ID) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "SELECT * FROM tb_rent WHERE r_cdate is null AND r_employee ='"
					+ Session_ID + "'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println(Session_ID);
			while (rs.next()) {
				allow ab = new allow();
				ab.setCar_kind(rs.getString(2));
				ab.setCustom(rs.getString(4));
				ab.setNumber(rs.getString(1));
				ab.setR_car(rs.getString(5));
				ab.setR_day(rs.getString(6));
				ab.setR_employee(rs.getString(3));
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

	public void allow_submitDao(String Session_ID, String[] checkbox) {
		String sql = null;
		String sql2 = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);
			stmt = con.createStatement();

			for (int i = 0; i < checkbox.length; i++) {
				sql = "update tb_rent set r_employee='" + Session_ID
						+ "' where  r_no= '" + checkbox[i] + "'";
				sql2 = "update tb_carinfo set c_check='1' where c_num=(select r_car from tb_rent where r_no='"
						+ checkbox[i] + "')";
				stmt.executeUpdate(sql);
				stmt.executeUpdate(sql2);
			}

		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("Driver load에 에러가 있습니다.\n" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("conn에 에러가 있습니다.\n" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void member_submitDao(String[] checkbox) {
		String sql = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);
			stmt = con.createStatement();

			for (int i = 0; i < checkbox.length; i++) {
				sql = "update tb_rent set r_cdate= now() where r_no= "
						+ checkbox[i];

				stmt.executeUpdate(sql);
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
