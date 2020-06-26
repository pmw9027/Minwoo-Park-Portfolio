package Admin.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Home.home;

public class carInfoDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<carInfo> array = new ArrayList<carInfo>();

	public void allow_add(String Session_ID) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "SELECT tb_rent.r_no,tb_rent.r_custom,tb_rent.r_car,tb_rent.r_rdate,tb_carinfo.c_kind FROM tb_rent,tb_carinfo WHERE tb_rent.r_branch=(select e_branch from tb_employee where l_id ='"
					+ Session_ID
					+ "')AND r_cdate IS NULL GROUP BY tb_rent.r_car";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println(Session_ID);
			while (rs.next()) {
				carInfo ab = new carInfo();
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

	public void Arrayadd(carInfo ab) {
		array.add(ab);
	}

	public ArrayList<carInfo> getList() {
		return array;
	}
}
