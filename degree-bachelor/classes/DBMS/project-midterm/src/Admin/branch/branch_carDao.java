package Admin.branch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Admin.allow;
import Home.home;

public class branch_carDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<branch_car> array = new ArrayList<branch_car>();

	public void branch_carinfo(String branch) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "select * from tb_carinfo where c_branch=(select e_branch from tb_employee where l_id='" + branch
					+ "')";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				branch_car ab = new branch_car();
				ab.setNum(rs.getInt(1));
				ab.setKind(rs.getString(3));
				ab.setColor(rs.getString(2));
				if(rs.getInt(5)==1)
					ab.setCheck("O");
				else
					ab.setCheck("X");
				
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

	public void Arrayadd(branch_car ab) {
		array.add(ab);
	}

	public ArrayList<branch_car> getList() {
		return array;
	}
}
