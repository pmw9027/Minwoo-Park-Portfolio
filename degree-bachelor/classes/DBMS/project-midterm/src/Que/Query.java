package Que;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
   //
   PreparedStatement ps = null;
   Statement stmt;
   Connection con = null;
   ResultSet rs = null;
   private String user;
   private String string;
   private String string2;
   private Object content;
   private Object date;
   private String title;
   private String reply;

   public Query() {
      String Login_sessin = null;
      String server = "121.168.72.142:3306";
      String database = "car";
      String user_name = "mw";
      String dbpassword = "goodmusic";

      try {
         Class.forName("org.gjt.mm.mysql.Driver");
         con = DriverManager.getConnection("jdbc:mysql://" + server + "/"
               + database, user_name, dbpassword);

      } catch (java.lang.ClassNotFoundException e) {
         System.err.println("Driver load�� ������ �ֽ��ϴ�.\n" + e.getMessage());
         e.printStackTrace();
      } // ����̹� �ε�
      catch (SQLException ex) {
         System.err.println("conn�� ������ �ֽ��ϴ�.\n" + ex.getMessage());
         ex.printStackTrace();
      }
   } // DB����

   public String[] SearchCar(String BRANCH, String KIND) throws SQLException {
      String[] Re = null;
      String sql = "Select c_name,c_color,c_fuel,c_price"
            + "from tb_carinfo,tb_branch,tb_carkind "
            + "where  tb_carinfo.c_branch =tb_branch.a_branch "
            + "AND tb_carkind.c_name = tb_carinfo.c_kind "
            + "AND tb_carinfo.c_branch =? " + "AND tb_carkind.c_kind = ? "
            + "Group by c_name;";
      ps = con.prepareStatement(sql);
      ps.setString(1, BRANCH);
      ps.setString(2, KIND);
      rs = ps.executeQuery();
      int i = 1;
      while (rs.next()) {
         Re[i] = " " + i + " " + rs.getString("c_name") + " "
               + rs.getString("c_color") + " " + "" + rs.getInt("c_fuel")
               + " " + rs.getInt("c_price") + " ";
         i++;
      }
      i = 1;
      return Re;
   }

   public int Login(String ID, String PW) throws SQLException {

      String table = "tb_custom";
      String sql = "Select l_id from " + table
            + " where l_id =? and AES_DECRYPT(UNHEX(l_pw), 'nhw3') = ?;";
      ps = con.prepareStatement(sql);
      ps.setString(1, ID);
      ps.setString(2, PW);
      rs = ps.executeQuery();
      System.out.println(ps.toString());
      if (rs.next()) {
         return 1;
      } else {
         table = "tb_employee";
         sql = "Select l_id from " + table + " where l_id =? and AES_DECRYPT(UNHEX(l_pw), 'nhw3') = ?;";

         ps = con.prepareStatement(sql);
         ps.setString(1, ID);
         ps.setString(2, PW);
         rs = ps.executeQuery();
         if (rs.next()) {
            return 2;
         } else
            return 0;

      }
   }

   public void login_get(String ID) throws SQLException {
      ps = con.prepareStatement("Select e_name,e_position from tb_employee where l_id=?");
      ps.setString(1, ID);
      rs = ps.executeQuery();
      String name, position;
      name = rs.getString(2);
      position = rs.getString(3);
   }

   public int JoinInfo(String ID, String PASSWORD, String NAME, String Phone,
         String Address, String License) {
      String sql = "insert into tb_custom values('" + NAME + "','" + ID
            + "',HEX(AES_ENCRYPT('" + PASSWORD + "','nhw3')),'" + Phone + "','" + Address + "','"
            + License + "');";
      System.out.println(sql);
      try {
         stmt = con.createStatement();
         stmt.executeUpdate(sql);
         return 1;
      } catch (SQLException e) {
         e.printStackTrace();
         return 0;
         // TODO Auto-generated catch block

      }

   }
   public int replyDao(int number,String reply) {
      String sql = "update tb_board set reply = '"+reply+"' where number = '"+number+"'";
      try {
         stmt = con.createStatement();
         stmt.executeUpdate(sql);
         return 1;
      } catch (SQLException e) {
         e.printStackTrace();
         return 0;
         // TODO Auto-generated catch block
      }
   }

   public int writeDao(String user, String title, String pw, String content) {
      String sql = "insert tb_board(title,user,pw,content) values('" + title
            + "','" + user + "','" + pw + "','" + content + "');";
      try {
         stmt = con.createStatement();
         stmt.executeUpdate(sql);
         return 1;
      } catch (SQLException e) {
         e.printStackTrace();
         return 0;
         // TODO Auto-generated catch block
      }

   }
   
   public int viewDao(int idx) {
      String sql = "Select user,title,date,content from board where number=";
      try {
         ps = con.prepareStatement(sql);
         ps.setInt(1, idx);
         rs = ps.executeQuery();
         if (rs.next()) {
            setUser(rs.getString(1));
            setTitle(rs.getString(2));
            setDate(rs.getString(4));
            setContent(rs.getString(6));
         }
         return 1;

      } catch (SQLException e) {
         e.printStackTrace();
         return 0;
      }
   }

   public String[] getSession(String ID, int i) {
      String[] session = new String[3];
      String sql;
      try {
         if (i == 1)
            sql = "Select e_name,e_position,e_branch from tb_employee where l_id =?";
         else
            sql = "Select c_name from tb_custom where l_id =?";
         ps = con.prepareStatement(sql);
         ps.setString(1, ID);
         rs = ps.executeQuery();

         if (rs.next() && i == 1) {
            session[0] = rs.getString(1);
            session[1] = rs.getString(2);
            session[2] = rs.getString(3);
         } else
            session[0] = rs.getString(1);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return session;
   }

   private void setDate(String date) {
      this.date = date;
      // TODO Auto-generated method stub

   }

   private void setContent(String content) {
      this.content = content;
   }

   private void setTitle(String title) {
      this.title = title;
   }

   private void setUser(String user) {
      this.user = user;
   }

   public int IdCheck(String id) {
      int num = 3;
      String sql = "select *from tb_custom where l_id=?";

      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, id);
         rs = ps.executeQuery();
         if (rs.next()) {
            num = 1;
         } else {

         }

      } catch (SQLException e) {

         e.printStackTrace();
      }
      return num;
   }

}