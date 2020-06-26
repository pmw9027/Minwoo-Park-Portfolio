package Home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Admin.allow;

public class popularityDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<popularity> array = new ArrayList<popularity>();

	public void popularity_order() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "SELECT tb_carinfo.c_kind,COUNT(tb_carinfo.c_kind) FROM tb_rent,tb_carinfo WHERE tb_carinfo.c_num = tb_rent.r_car GROUP BY tb_carinfo.c_kind ORDER BY COUNT(tb_carinfo.c_kind) DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("1234");
			while (rs.next()) {
				System.out.println("1234");
				popularity ab = new popularity();
				ab.setName(rs.getString(1));
				ab.setCount(rs.getInt(2));
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

	public void Arrayadd(popularity ab) {
		array.add(ab);
	}

	public ArrayList<popularity> getList() {
		return array;
	}
}
