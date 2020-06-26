package Admin.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Admin.allow;
import Home.home;

public class carDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<car> array = new ArrayList<car>();

	public carDao() {

	}

	public void branch_car(String session) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "SELECT * FROM tb_carinfo WHERE c_branch=(SELECT e_branch FROM tb_employee WHERE l_id='"
					+ session + "')";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				car ab = new car();
				ab.setNum(rs.getInt(1));
				ab.setColor(rs.getString(2));
				ab.setKind(rs.getString(3));
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

	public void branch_carAdd(String branch) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "INSERT tb_carinfo(c_num,c_color,c_kind) VALUES('2321','검정색','SM5')";
			stmt = con.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("Driver load에 에러가 있습니다.\n" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("conn에 에러가 있습니다.\n" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void Arrayadd(car ab) {
		array.add(ab);
	}

	public ArrayList<car> getList() {
		return array;
	}
}
