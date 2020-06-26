package Main;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pmw90 on 2015-11-27.
 */
public class DB {

    public final String server = "203.252.22.161:1521";
    public final String database = "orcl";
    public final String url = "jdbc:oracle:thin:@" + server + ":" + database;
    public final String user = "park_dbms";
    public final String password = "jy2411";
    Connection con = null;
    PreparedStatement ps = null;
    Statement stat;

    ResultSet rs = null;

    public DB() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, password);
            stat = con.createStatement();
            System.out.println("succeess");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("Driver load eroor.\n" + e.getMessage());
            e.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("conn error\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String DB_Login(String id, String pw) {
        String sql = "SELECT * FROM TB_USER where user_id='" + id + "'and CryptIT.decrypt(user_pw,'storm') = '"+pw+"'";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next() == false) {
                return null;
            } else {
                return rs.getString(3);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean DB_JOIN(UserVo UV){
        try {
            con.setAutoCommit(false);
            String sql = "insert into tb_user values(?,CryptIT.encrypt(?, 'storm'),?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,UV.getID());
            ps.setString(2,UV.getPW());
            ps.setString(3, UV.getName());
            ps.setString(4, UV.getAddress());
            ps.setInt(5,10000);
            ps.executeUpdate();
            con.commit(); // 커밋하기
            con.setAutoCommit(true);
            //return al;
        } catch (SQLException se) {
            System.out.println(se.getMessage());

            try {
                con.rollback(); // 롤백(작업취소하기)
                System.out.println("작업이 취소되었습니다.");
            } catch (SQLException ss) {
                System.out.println(ss.getMessage());
            }
            return false;
        }
        return true;
    }

    public ArrayList DB_Weather(String today) {
        String date = null;
        String query = null;
        String weather = null;

        SimpleDateFormat trans2 = new SimpleDateFormat("MM/dd");
        int max_temp = 0, min_temp = 0;
        int i = 0;
        ArrayList<WeatherVo> W_ArrayList = new ArrayList<WeatherVo>();
        try {
                DocumentBuilderFactory factory = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder
                        .parse("http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=109");
                // 109�� ���û���� �����ϴ� RSS OPEN API�� ����.���� ������ ����
                NodeList list = doc.getElementsByTagName("location");
                for (i = 2; i < 3; i++) { // ���� ������ ������ ����
                    // for(int i=0; i<list.getLength(); i++)��ŭ �ָ� ����.���� ����
                    // �ٳ���
                    for (Node node = list.item(i).getFirstChild(); node != null; node = node
                            .getNextSibling()) {
                        if (node.getNodeName().equals("city")) // ����
                            System.out.println("   <" + node.getTextContent()
                                    + ">");
                        for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2
                                .getNextSibling()) {
                            if (node2.getNodeName().equals("tmEf")) { // ��¥
                                date = node2.getTextContent();
                                System.out.print(date + " ");
                            } else if (node2.getNodeName().equals("wf")) { // ����
                                weather = node2.getTextContent();
                            } else if (node2.getNodeName().equals("tmn")) { // �������
                                min_temp = Integer.parseInt(node2
                                        .getTextContent());
                            } else if (node2.getNodeName().equals("tmx")) { // �ִ���
                                max_temp = Integer.parseInt(node2
                                        .getTextContent());


                                query = "select * from tb_weather where weather_date= '"
                                        + date + "'";

                                ResultSet rs = stat.executeQuery(query);
                                if (rs.next())
                                    query = "update tb_weather set weather_max_temp='"
                                            + max_temp + "',weather_min_temp='"
                                            + min_temp + "'" + "where weather_date='"
                                            + date + "'";
                                else
                                    query = "insert into tb_weather(weather_date,weather_max_temp,weather_min_temp,weather_weather) values('"
                                            + date
                                            + "','"
                                            + max_temp
                                            + "','"
                                            + min_temp + "','" + weather + "')";
                                stat.executeUpdate(query);

                            }
                        }
                    }

            }
            String sql = "SELECT to_char(weather_date,'YYYY-MM-DD'),weather_max_temp,weather_min_temp,weather_weather FROM TB_WEATHER where weather_date >='"+today+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                W_ArrayList.add(new WeatherVo(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
                i++;
            }
            return W_ArrayList;
        } catch (SQLException ex) {
            //System.err.println("conn�� ������ �ֽ��ϴ�.\n" + ex.getMessage());
            ex.printStackTrace();
            return null;
        } catch (SAXException E) {
        } catch (IOException E) {
        } catch (ParserConfigurationException E) {
        }
        return null;
    }

    public ArrayList DB_Terminal() {
        String sql = "SELECT * FROM TB_terminal ";
        ArrayList<TerminalVo> al = new ArrayList<TerminalVo>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                al.add(new TerminalVo(rs.getInt(1), rs.getString(2)));
            }
            return al;
        } catch (SQLException ex) {
            //System.err.println("conn�� ������ �ֽ��ϴ�.\n" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList DB_Terminal_Time(String start, String arrive,String date) {
        String sql = "SELECT * FROM TB_bus_Interval where bus_interval_start ='" + start + "' and bus_interval_arrive ='" + arrive + "' and bus_interval_date ='"+date+"'" ;
        ArrayList<TerminalTimeVo> al = new ArrayList<TerminalTimeVo>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                al.add(new TerminalTimeVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
            }
            return al;
        } catch (SQLException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    public boolean DB_Bus_cash(TerminalTimeVo inteval_num) {

            try {

                if (Main.session == null) {
                    new Login();
                    return false;
                } else {
                    con.setAutoCommit(false);
                    String sql = "insert INTO tb_bus_book VALUES (bus_book_seq.nextval," + inteval_num.getInterval_num() + ",'" + Main.session.getID() + "')";
                    String sql2 = "update tb_user set user_cash=user_cash-" + inteval_num.getPrice() + " where user_id='" + Main.session.getID() + "'";
                    String sql3 = "update tb_bus_interval set bus_interval_book_seat=bus_interval_book_seat+1  where bus_interval_num="+inteval_num.getInterval_num();
                    ps = con.prepareStatement(sql);
                    ps.executeUpdate();
                    ps = con.prepareStatement(sql2);
                    ps.executeUpdate();
                    ps = con.prepareStatement(sql3);
                    ps.executeUpdate();
                    con.commit(); // 커밋하기
                    con.setAutoCommit(true);
                }
        } catch (SQLException se) {
            System.out.println(se.getMessage());

            try {
                con.rollback(); // 롤백(작업취소하기)
                System.out.println("작업이 취소되었습니다.");
            } catch (SQLException ss) {
                System.out.println(ss.getMessage());
            }
            return false;
        }
        return true;
    }

    public ArrayList<BookVo> DB_Mybook () {

            //String sql2 = "SELECT * FROM TB_bus_Interval where bus_interval_start ='"+start+"' and bus_interval_arrive ='"+arrive+"'";
            try {


                BookVo Bv;
                ArrayList<BookVo> BvA = new ArrayList<BookVo>();
                if (Main.session == null) {
                    new Login();
                    return null;
                } else {
                    String sql = "select TB_bus_book.bus_book_number,TB_bus_book.bus_book_user,TB_BUS_INTERVAL.BUS_INTERVAL_DATE,TB_BUS_INTERVAL.BUS_INTERVAL_START_TIME,tb_bus_interval.bus_interval_start,tb_bus_interval.bus_interval_arrive,tb_bus_interval.bus_interval_price from tb_bus_book,tb_bus_interval where TB_BUS_BOOK.BUS_INTERVAL_NUMBER = TB_BUS_INTERVAL.BUS_INTERVAL_NUM and TB_BUS_BOOK.BUS_BOOK_USER = '" + Main.session.getID() + "'";
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Bv = new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                        BvA.add(Bv);
                    }
                    return BvA;
                }
                //return al;
            } catch (SQLException ex) {
                //System.err.println("conn�� ������ �ֽ��ϴ�.\n" + ex.getMessage());
                ex.printStackTrace();
                return null;
            }

        }

    public void DB_Book_cancel(BookVo Bv) {

        //String sql2 = "SELECT * FROM TB_bus_Interval where bus_interval_start ='"+start+"' and bus_interval_arrive ='"+arrive+"'";
        try {

            if (Main.session == null) {
                new Login();
            } else {
                con.setAutoCommit(false);
                String sql = "DELETE FROM tb_bus_book where bus_book_number = " + Bv.getBook_num();
                ps = con.prepareStatement(sql);
                ps.execute();
                sql = "UPDATE tb_user SET user_cash=user_cash+'" + Bv.getPrice() + "' where user_id='" + Bv.getBook_user() + "'";
                ps = con.prepareStatement(sql);
                ps.execute();
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace(); }
        }

    }

    public ArrayList<TourVo> DB_bascket_tour(){

        ArrayList<TourVo> ALTV = new ArrayList<TourVo>();
        try {
            if (Main.session == null) {
                new Login();
            } else {
                con.setAutoCommit(false);
                String sql = "select * from tb_bascket_tour where bascket_user_id = '"+ Main.session.getID()+"'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) {
                    ALTV.add((new Json(rs.getString(3))).getTour());
                }
                con.commit(); // 커밋하기
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());

            try {
                con.rollback(); // 롤백(작업취소하기)
                System.out.println("작업이 취소되었습니다.");
            } catch (SQLException ss) {
                System.out.println(ss.getMessage());
            }
        }
        return ALTV;
    }

    public boolean DB_Mytour_insert(String Cid){
        try {

            if (Main.session == null) {
                new Login();
                return false;
            } else {
                con.setAutoCommit(false);
                System.out.println(Main.session.getID());
                String sql = "insert INTO tb_bascket_tour VALUES (bascket_tour_seq.nextval,'"+Main.session.getID()+"','"+Cid+"')";
                ps = con.prepareStatement(sql);
                ps.executeUpdate();
                con.commit(); // 커밋하기
            }
            //return al;
        } catch (SQLException se) {
            System.out.println(se.getMessage());

            try {
                con.rollback(); // 롤백(작업취소하기)
                System.out.println("작업이 취소되었습니다.");
            } catch (SQLException ss) {
                System.out.println(ss.getMessage());
            }
            return false;
        }
        return true;
    }
    public boolean DB_tour_insert(String Cid){
        try {
                con.setAutoCommit(false);


                String sql = "MERGE INTO tb_tour USING DUAL ON (Tour_contents_id ='"+Cid +"') WHEN NOT MATCHED THEN INSERT(Tour_Contents_id) VALUES('"+Cid+"')";
                ps = con.prepareStatement(sql);
                ps.executeUpdate();
                con.commit(); // 커밋하기

            //return al;
        } catch (SQLException se) {
            System.out.println(se.getMessage());

            try {
                con.rollback(); // 롤백(작업취소하기)
                System.out.println("작업이 취소되었습니다.");
            } catch (SQLException ss) {
                System.out.println(ss.getMessage());
            }
            return false;
        }
        return true;

    }

    public UserVo DB_user_select(){
        try {
            con.setAutoCommit(false);


            String sql = "select user_id,user_name,user_ADDRESS,USER_CASH from tb_user where user_id = '"+Main.session.getID()+"'";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            //System.out.println(rs.getString(1)+rs.getString(3)+rs.getString(4));//+rs.getInt(4));
            return new UserVo(rs.getString(1),rs.getString(3),rs.getString(4),rs.getInt(5));
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return new UserVo();
    }
}

