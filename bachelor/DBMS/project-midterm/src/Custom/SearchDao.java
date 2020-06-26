package Custom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Home.home;

public class SearchDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<Search> array = new ArrayList<Search>();

	public SearchDao() {

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("Driver load에 에러가 있습니다.\n" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("conn에 에러가 있습니다.\n" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public int DeleteMember(String ID) throws SQLException {
		stmt = con.createStatement();
		stmt.executeUpdate("delete from tb_custom where l_id ='" + ID + "';");
		return 1;
	}

	public int adjustInfo(String name, String password, String phone,
			String address, String license, String ID) throws SQLException {
		stmt = con.createStatement();
		stmt.executeUpdate("update tb_custom set c_name = '" + name
				+ "', l_pw = '" + password + "', c_pnum = '" + phone
				+ "', c_addres = '" + address + "', c_license = '" + license
				+ "' where l_id = '" + ID + "';");
		return 1;
	}

	public void myInfo(String custom) throws SQLException {
		String sql = "select l_id, l_pw, c_name, c_pnum, c_addres, c_license from tb_custom where l_id=?";
		ps = con.prepareStatement(sql);
		ps.setString(1, custom);
		rs = ps.executeQuery();
		while (rs.next()) {
			Search ab = new Search();
			ab.setName(rs.getString("c_name"));
			ab.setpnum(rs.getString("c_pnum"));
			ab.setaddress(rs.getString("c_addres"));
			ab.setlicense(rs.getString("c_license"));
			Arrayadd(ab);
		}
	}

	public void MyRentInfo(String custom) throws SQLException {
		String sql = "Select * " + "from  tb_rent,tb_carinfo "
				+ "where  tb_rent.r_car = tb_carinfo.c_num "
				+ "AND tb_rent.r_custom = ?;";
		ps = con.prepareStatement(sql);
		ps.setString(1, custom);
		rs = ps.executeQuery();
		while (rs.next()) {

			Search ab = new Search();
			ab.setday(rs.getInt("r_day"));
			ab.setDate(rs.getString("r_rdate"));
			ab.setArea(rs.getString("r_branch"));
			ab.setCheck(rs.getInt("c_check"));
			ab.setCar_kind(rs.getString("c_kind"));
			Arrayadd(ab);
		}
	}

	public void SetColorName(int Carnum) throws SQLException {
		String sql = " Select c_kind,c_color " + " from tb_carinfo "
				+ " where c_num = ?;";
		ps = con.prepareStatement(sql);
		ps.setInt(1, Carnum);
		rs = ps.executeQuery();
		while (rs.next()) {
			Search ab = new Search();
			ab.setCar_kind(rs.getString("c_kind"));
			ab.setcolor(rs.getString("c_color"));
			Arrayadd(ab);
		}

	}

	public void InsertRent(String custom, int Carnum, String Date, int day,
			String area) throws SQLException {
		String sql = "INSERT tb_rent (r_custom,r_car,r_rdate,r_day,r_branch) VALUES (?,?,?,?,?);";
		ps = con.prepareStatement(sql);
		ps.setString(1, custom);
		ps.setInt(2, Carnum);
		ps.setString(3, Date);
		ps.setInt(4, day);
		ps.setString(5, area);
		ps.executeUpdate();
	}

	public void Query(String branch, String kind) throws SQLException {

		String sql = "Select c_num,c_name,c_color,c_fuel,c_pride "
				+ "from tb_carinfo,tb_branch,tb_carkind "
				+ "where  tb_carinfo.c_branch =tb_branch.a_branch "
				+ "AND tb_carkind.c_name = tb_carinfo.c_kind "
				+ "AND tb_carinfo.c_branch =? " + "AND tb_carkind.c_kind = ? "
				+ "AND tb_carinfo.c_check=0 " + "Group by c_name;";

		ps = con.prepareStatement(sql);
		ps.setString(1, branch);
		ps.setString(2, kind);
		rs = ps.executeQuery();
		while (rs.next()) {
			Search ab = new Search();
			ab.setNumber(rs.getString("c_num"));
			ab.setName(rs.getString("c_name"));
			ab.setcolor(rs.getString("c_color"));
			ab.setfuel(rs.getInt("c_fuel"));
			ab.setPrice(rs.getInt("c_pride"));
			Arrayadd(ab);
		}
	}

	public void Arrayadd(Search ab) {
		array.add(ab);
	}

	public ArrayList<Search> getList() {
		return array;
	}
}
