package Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Admin.allow;
import Home.home;

public class viewDao {
	PreparedStatement ps = null;
	Statement stmt;
	Connection con = null;
	ResultSet rs = null;
	private board ab = new board();

	public void viewDao_abc(int idx) {
		try {

			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + home.server
					+ "/" + home.database, home.user_name, home.dbpassword);

			String sql = "Select user,title,content,date,reply from tb_board where number='"
					+ idx + "'";
			ps = con.prepareStatement(sql);
			//ps.setInt(1, idx);
			rs = ps.executeQuery();
			if (rs.next()) {
				board ab = new board();
				ab.setUser(rs.getString(1));
				ab.setTitle(rs.getString(2));
				ab.setContent(rs.getString(3));
				ab.setDate(rs.getString(4));
				ab.setReply(rs.getString(5));
				board_save(ab);
			}

		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("Driver load에 에러가 있습니다.\n" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("conn에 에러가 있습니다.\n" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void board_save(board abc) {
		this.ab = abc;
	}

	public board getList() {
		return ab;
	}
}