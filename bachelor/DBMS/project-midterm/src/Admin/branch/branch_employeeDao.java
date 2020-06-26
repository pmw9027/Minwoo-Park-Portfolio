package Admin.branch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Home.home;

public class branch_employeeDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<branch_employee> array = new ArrayList<branch_employee>();

	public void branch_employeeinfo(String branch) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "select l_id,e_name,e_position,e_pnumber,e_cnumber from tb_employee where e_branch=(select e_branch from tb_employee where l_id='"
					+ branch + "') order by e_num";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				branch_employee ab = new branch_employee();
				ab.setId(rs.getString(1));
				ab.setName(rs.getString(2));
				ab.setPosition(rs.getString(3));
				ab.setPhone(rs.getString(4));
				ab.setCall(rs.getString(5));
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

	public void Arrayadd(branch_employee ab) {
		array.add(ab);
	}

	public ArrayList<branch_employee> getList() {
		return array;
	}
}