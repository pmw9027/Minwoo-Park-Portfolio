package Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Home.home;
public class boardDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	ArrayList<board> array = new ArrayList<board>();

	public boardDao() {

		try {

			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "Select * from tb_board";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				board ab = new board();
				ab.setUser(rs.getString(1));
				ab.setTitle(rs.getString(2));
				ab.setNumber(rs.getString(3));
				ab.setDate(rs.getString(4));
				ab.setPw(rs.getString(5));
				ab.setContent(rs.getString(6));
				ab.setReply(rs.getString(7));
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

	public void Arrayadd(board ab) {
		array.add(ab);
	}

	public ArrayList<board> getList() {
		return array;
	}
}
