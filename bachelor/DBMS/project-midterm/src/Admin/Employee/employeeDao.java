package Admin.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Admin.Car.car;
import Home.home;

public class employeeDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<employee> emp = new ArrayList<employee>();

	public employeeDao() {

	}

	public void branch_employee(String session) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "SELECT * FROM tb_employee WHERE e_branch=(SELECT e_branch FROM tb_employee WHERE l_id='"
					+ session + "')";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				employee ab = new employee();
				ab.setNum(rs.getInt(1));
				ab.setPosition(rs.getString(4));
				ab.setId(rs.getString(2));
				ab.setName(rs.getString(3));
				ab.setCnum(rs.getString(8));
				ab.setPhone(rs.getString(7));
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

	public void Arrayadd(employee ab) {
		emp.add(ab);
	}

	public ArrayList<employee> getList() {
		return emp;
	}
}
