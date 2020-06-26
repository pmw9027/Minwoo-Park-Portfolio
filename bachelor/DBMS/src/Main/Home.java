package Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by pmw90 on 2015-11-27.
 */
public class Home extends JFrame {
    private JPanel jPanel;
    private JPanel jPanel2;
    private JPanel jPanel_tour;
    private JPanel jPanel_weather;
    private JPanel jPanel5;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button_my;
    private JButton button6;
    private JButton Button_tour_search;
    private JButton button8;
    private JButton button_tour_festival;
    private JButton Button_start;
    private JButton Button_arrive;
    private JButton Button_search;
    private JButton Session;
    private JList list1;
    private JButton Button_Mybook;
    private JButton Button_Mytour;
    private JButton Button_insert;
    private JButton Button_Myinfo;
    private JLabel Login_Jlabel;
    private JLabel Jlabel_today;
    private JLabel Jlabel_date1;
    private JLabel Jlabel_date2;
    private JLabel Jlabel_date3;
    private JLabel Jlabel_date4;
    private JLabel Jlabel_date5;
    private JLabel Jlabel_date6;
    private JLabel Jlabel_weather1;
    private JLabel Jlabel_weather2;
    private JLabel Jlabel_weather3;
    private JLabel Jlabel_weather4;
    private JLabel Jlabel_weather5;
    private JLabel Jlabel_weather6;
    private JLabel Jlabel_max_temp2;
    private JLabel Jlabel_max_temp3;
    private JLabel Jlabel_max_temp4;
    private JLabel Jlabel_max_temp5;
    private JLabel Jlabel_max_temp6;
    private JLabel Jlabel_max_temp1;
    private JLabel Jlabel_min_temp1;
    private JLabel Jlabel_min_temp2;
    private JLabel Jlabel_min_temp3;
    private JLabel Jlabel_min_temp4;
    private JLabel Jlabel_min_temp5;
    private JLabel Jlabel_min_temp6;
    private JLabel Jlabel_start;
    private JLabel Jlabel_arrive;
    private JButton Button_date;
    private JButton Button_cash;
    private JLabel Jlabel_book_date;
    private JTextField Jlabel_tour_search;
    private JList Jlist_Tour;
    private Tour tour_frame = null;
    private JPanel Jpanel_tour_search;
    private JList Jlist_my_book;
    private JButton Button_book_cancel;
    private JButton Button_book_w;
    private JLabel Jlabel_money;
    private JLabel Jlabel_user_address;
    private JLabel Jlabel_user_id;
    private JLabel Jlabel_user_name;
    private JButton Button_user_pw;
    private JPanel Jpanel_my_book;
    private JPanel Jpanel_my_info;
    private JList Jlist_my_tour;
    private JPanel Jpanel_my_tour;
    private JButton Jbutton_my_tour_del;
    private JScrollPane Jscroll_tour;
    private JScrollPane JscrollPanel_book;
    static ArrayList<JLabel> Jlabel_date = new ArrayList<JLabel>();
    static ArrayList<JLabel> Jlabel_weather = new ArrayList<JLabel>();
    static ArrayList<JLabel> Jlabel_max_temp = new ArrayList<JLabel>();
    static ArrayList<JLabel> Jlabel_min_temp = new ArrayList<JLabel>();
    static ArrayList<TourVo> AL_Tour = new ArrayList<TourVo>();
    ArrayList<BookVo> BvA = Main.db.DB_Mybook();
    ArrayList<TerminalTimeVo> al;
    Tour tour;
    DefaultListModel listModel = new DefaultListModel();
    DefaultListModel listModel2 = new DefaultListModel();
    //Toolkit tk = Toolkit.getDefaultToolkit();
    // Dimension screenSize = tk.getScreenSize();
    static SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
    static java.util.Date today = new java.util.Date();
    static String today_trans = trans.format(today);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();

    public Home() {
        super("Hello world");
        Jlabel_date.add(Jlabel_date1);
        Jlabel_date.add(Jlabel_date2);
        Jlabel_date.add(Jlabel_date3);
        Jlabel_date.add(Jlabel_date4);
        Jlabel_date.add(Jlabel_date5);
        Jlabel_date.add(Jlabel_date6);
        Jlabel_weather.add(Jlabel_weather1);
        Jlabel_weather.add(Jlabel_weather2);
        Jlabel_weather.add(Jlabel_weather3);
        Jlabel_weather.add(Jlabel_weather4);
        Jlabel_weather.add(Jlabel_weather5);
        Jlabel_weather.add(Jlabel_weather6);
        Jlabel_max_temp.add(Jlabel_max_temp1);
        Jlabel_max_temp.add(Jlabel_max_temp2);
        Jlabel_max_temp.add(Jlabel_max_temp3);
        Jlabel_max_temp.add(Jlabel_max_temp4);
        Jlabel_max_temp.add(Jlabel_max_temp5);
        Jlabel_max_temp.add(Jlabel_max_temp6);
        Jlabel_min_temp.add(Jlabel_min_temp1);
        Jlabel_min_temp.add(Jlabel_min_temp2);
        Jlabel_min_temp.add(Jlabel_min_temp3);
        Jlabel_min_temp.add(Jlabel_min_temp4);
        Jlabel_min_temp.add(Jlabel_min_temp5);
        Jlabel_min_temp.add(Jlabel_min_temp6);
        Jlabel_today.setText(today_trans);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = e.getSource().hashCode();
                if (a == Session.hashCode()) {
                    new Login();
                } else if (a == button1.hashCode()) {
                    jPanel2.setVisible(true);
                    jPanel_tour.setVisible(false);
                    jPanel_weather.setVisible(false);
                    jPanel5.setVisible(false);
                } else if (a == button2.hashCode()) {
                    jPanel2.setVisible(false);
                    jPanel_tour.setVisible(true);
                    jPanel_weather.setVisible(false);
                    jPanel5.setVisible(false);
                } else if (a == button3.hashCode()) {
                    set_weather();
                    jPanel2.setVisible(false);
                    jPanel_tour.setVisible(false);
                    jPanel_weather.setVisible(true);
                    jPanel5.setVisible(false);
                } else if (a == button_my.hashCode()) {
                    if (Main.session != null) {
                        jPanel2.setVisible(false);
                        jPanel_tour.setVisible(false);
                        jPanel_weather.setVisible(false);
                        jPanel5.setVisible(true);
                    } else
                        new Login();
                } else if (a == Button_date.hashCode()) {
                    new Search_Date();
                } else if (a == Button_arrive.hashCode()) {
                    new Search_Terminal(2);
                } else if (a == Button_start.hashCode()) {
                    new Search_Terminal(1);
                } else if (a == Button_search.hashCode()) {
                    //al.clear();
                    al = Main.db.DB_Terminal_Time(Jlabel_start.getText(), Jlabel_arrive.getText(), Jlabel_book_date.getText());
                    int j = 0;
                    //listModel.addElement(al);
                    listModel.removeAllElements();
                    for (int i = 0; i < al.size(); i++)
                        listModel.add(j++, al.get(i).getDate() + "          " + al.get(i).getTime() + "                  " + al.get(i).getStart() + "                       " + al.get(i).getArrive() + "                   " + al.get(i).getCompany() + "              " + al.get(i).getBook_seat() + "               " + al.get(i).price);
                    list1.setModel(listModel);
                    JscrollPanel_book.repaint();
                } else if (a == Button_cash.hashCode()) {
                    if (Main.db.DB_Bus_cash(al.get(list1.getSelectedIndex()))) {
                        JOptionPane.showMessageDialog(null, "예매 결제 성공");
                    }
                } else if (a == Button_tour_search.hashCode()) {
                    listModel2.removeAllElements();
                    InputStream is = getInputStreamFromUrl(Jlabel_tour_search.getText());
                    String result = convertStreamToString(is);
                    keywordjsonParserList(result);
                } else if (a == button_tour_festival.hashCode()) {
                    Jpanel_tour_search.setVisible(false);
                    listModel2.removeAllElements();
                    InputStream is = getInputStreamFromUrl(today_trans, today_trans);
                    String result = convertStreamToString(is);
                    festivaljsonParserList(result);
                    Jscroll_tour.repaint();
                } else if (a == Button_Mybook.hashCode()) {
                    Jpanel_my_book.setVisible(true);
                    Jpanel_my_info.setVisible(false);
                    Jpanel_my_tour.setVisible(false);
                    BvA = Main.db.DB_Mybook();
                    listModel.clear();
                    int j = 0;
                    for (int i = 0; i < BvA.size(); i++)
                        listModel.add(j++, BvA.get(i).getDate() + "          " + BvA.get(i).getDate_time() + "                  " + BvA.get(i).getStart() + "                       " + BvA.get(i).getArrive() + "              충남고속               2                 "+BvA.get(i).getPrice()) ;//+ BvA.get(i).getArrive() + "              " + BvA.get(i).getBook_user() + "               " + BvA.get(i).getPrice());
                    Jlist_my_book.setModel(listModel);
                } else if (a == Button_Myinfo.hashCode()) {
                    Jpanel_my_book.setVisible(false);
                    Jpanel_my_info.setVisible(true);
                    Jpanel_my_tour.setVisible(false);
                    UserVo UV = Main.db.DB_user_select();
                    Jlabel_user_id.setText(UV.getID());
                    Jlabel_user_name.setText(UV.getName());
                    Jlabel_user_address.setText(UV.getAddress());
                    Jlabel_money.setText(Integer.toString(UV.getMoney()));
                    pack();
                } else if (a == Button_Mytour.hashCode()) {
                    listModel.clear();
                    ArrayList<TourVo> TvA = Main.db.DB_bascket_tour();
                    int j = 0;
                    for (int i = 0; i < TvA.size(); i++ ){
                        listModel.add(j++, TvA.get(i).getTitle());
                    }
                    Jlist_my_tour.setModel(listModel);
                    Jpanel_my_book.setVisible(false);
                    Jpanel_my_info.setVisible(false);
                    Jpanel_my_tour.setVisible(true);
                } else if (a == Button_book_cancel.hashCode()) {
                    Main.db.DB_Book_cancel(BvA.get(Jlist_my_book.getSelectedIndex()));
                } else if (a == Button_book_w.hashCode()) {

                } else if (a == Jbutton_my_tour_del.hashCode()) {

                } else if (a == Button_Mytour.hashCode()) {

                }

            }

        };
        Session.addActionListener(listener);
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button_my.addActionListener(listener);
        Button_date.addActionListener(listener);
        Button_start.addActionListener(listener);
        Button_arrive.addActionListener(listener);
        Button_search.addActionListener(listener);
        Button_cash.addActionListener(listener);
        Button_tour_search.addActionListener(listener);
        button_tour_festival.addActionListener(listener);
        Button_book_cancel.addActionListener(listener);
        Button_Mybook.addActionListener(listener);
        Button_Myinfo.addActionListener(listener);
        Button_Mytour.addActionListener(listener);


        Jlist_Tour.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tour_frame != null)
                    tour_frame.dispose();
                tour_frame = new Tour(AL_Tour.get(Jlist_Tour.getSelectedIndex()));
            }
        });
        setContentPane(jPanel);
        pack();
        setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
        setVisible(true);
    }

    public void set_Login(String name) {
        Login_Jlabel.setText(name + "환영합니다.");
        repaint();
    }

    public void set_weather() {
        ArrayList<WeatherVo> W_Array = Main.db.DB_Weather(today_trans);
        for (int i = 0; i < 6; i++) {
            Jlabel_date.get(i).setText(W_Array.get(0).getDate());
            Jlabel_weather.get(i).setText(W_Array.get(0).getWeather());
            Jlabel_max_temp.get(i).setText(Integer.toString(W_Array.get(0).getMax_temp()));
            Jlabel_min_temp.get(i).setText(Integer.toString(W_Array.remove(0).getMin_temp()));

        }
    }

    public void set_start_terminal(String str) {
        Jlabel_start.setText(str);
    }

    public void set_arrive_terminal(String str) {
        Jlabel_arrive.setText(str);
    }

    public void set_date(String str) {
        Jlabel_book_date.setText(str);
    }

    public void keywordjsonParserList(String pRecvServerPage) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(pRecvServerPage);
            JSONArray localJSONArray = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("response")).get("body"))
                    .get("items")).get("item");
            String[] arrayOfString = {"addr1", "firstimage", "title",
                    "contentid", "firstimage2"};
            //AL_Tour.removeAll();
            for (int i = 0; i < localJSONArray.size(); i++) {
                JSONObject localJSONObject = (JSONObject) localJSONArray.get(i);
                // m_Adapter.removeall();

                if (localJSONObject != null) {
                    try {
                        System.out.println(localJSONObject.toString());
                        TourVo localConList = new TourVo(localJSONObject.get(arrayOfString[2]).toString(), localJSONObject.get(arrayOfString[3]).toString());
                        Main.db.DB_tour_insert(localConList.getContentID());
                        AL_Tour.add(localConList);
                        listModel2.add(i, localJSONObject.get(arrayOfString[2]).toString());
                    } catch (Exception e) {


                    }
                }
            }
            Jlist_Tour.setModel(listModel2);

        } catch (org.json.simple.parser.ParseException e) {

        }
        try {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(pRecvServerPage);

        } catch (org.json.simple.parser.ParseException e) {

        }
    }

    public void festivaljsonParserList(String pRecvServerPage) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(pRecvServerPage);
            JSONArray localJSONArray = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("response")).get("body"))
                    .get("items")).get("item");
            String[] arrayOfString = {"addr1", "firstimage", "title",
                    "contentid"};
            AL_Tour.clear();
            for (int i = 0; i < localJSONArray.size(); i++) {
                JSONObject localJSONObject = (JSONObject) localJSONArray.get(i);
                // m_Adapter.removeall();
                if (localJSONObject != null) {
                    try {
                        System.out.println(localJSONObject.toString());
                        TourVo localConList = new TourVo(localJSONObject.get(arrayOfString[2]).toString(), localJSONObject.get(arrayOfString[3]).toString());

                        Main.db.DB_tour_insert(localConList.getContentID());
                        AL_Tour.add(localConList);
                        listModel2.add(i, localJSONObject.get(arrayOfString[2]).toString());
                    } catch (Exception e) {

                    }
                }
                Jlist_Tour.setModel(listModel2);
            }
        } catch (org.json.simple.parser.ParseException e) {

        }
    }

    public InputStream getInputStreamFromUrl(String keyword) {

        String key = null;
        try {
            key = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String str1 = "&keyword=" + key;
        String str2 = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword"
                + "?ServiceKey=YUiU%2F%2BiwXOmqlEHW0YbPM0lBlY22JeKjxNMz%2FHsjw2OtMygbwCG9wKUqs%2Bz63QE5ZUzr2YTFH8Jr95FwQUza2g%3D%3D"
                + str1
                + "&MobileOS=ETC"
                + "&MobileApp=AppTesting"
                + "&numOfRows=20&pageNo=1" + "&_type=json";
        String URL = str2;
        InputStream content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(URL));
            content = response.getEntity().getContent();
        } catch (Exception e) {

        }
        return content;
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public InputStream getInputStreamFromUrl(String start, String end) {

        String str1 = "&eventStartDate=" + start + "&eventEndDate=" + end;
        String str2 = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival"
                + "?ServiceKey=YUiU%2F%2BiwXOmqlEHW0YbPM0lBlY22JeKjxNMz%2FHsjw2OtMygbwCG9wKUqs%2Bz63QE5ZUzr2YTFH8Jr95FwQUza2g%3D%3D"
                + str1
                + "&MobileOS=ETC"
                + "&MobileApp=AppTesting&overviewYN=Y"
                + "&numOfRows=20&pageNo=1" + "&_type=json";
        String URL = str2;
        InputStream content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(URL));
            content = response.getEntity().getContent();
        } catch (Exception e) {

        }
        return content;
    }



}
