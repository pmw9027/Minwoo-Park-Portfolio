package com.lpk.energy.controller;

import com.lpk.energy.ClassDo;
import com.lpk.energy.TimeTableLoad;
import com.lpk.energy.TimeTableMongoRepository;
import com.lpk.energy.enertalk.RealTimeUsageDo;
import com.lpk.energy.enertalk.RealTimeUsageMongoRepository;
import com.lpk.energy.room.RoomDo;
import com.lpk.energy.room.RoomMongoRepository;
import com.lpk.energy.weather.weatherDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by HeeJoongKim on 2017-04-07.
 */
@Controller
public class WebController
{

    @Autowired
    TimeTableMongoRepository timeTableMongoRepository;
    @Autowired
    RealTimeUsageMongoRepository realTimeUsageMongoRepository;

    @Autowired
    RoomMongoRepository roomMongoRepository;
    @RequestMapping(value="/")
    public String main(){
        return "redirect:/main";
    }
    @RequestMapping(value="/main")
    public String mainframe(Model model){
        List<RealTimeUsageDo> boards = realTimeUsageMongoRepository.findAll();
        for(int i=0;i<boards.size();i++) {
            Date date = new Date(boards.get(i).getTimestamp());
            Format format = new SimpleDateFormat("yyyy-MM");
            String timeStamp = format.format(date);
            model.addAttribute("boards",timeStamp);
        }

        URL url =null;
        URLConnection conn=null;
        List<weatherDo> weatherList = new ArrayList();
        try {
            url =new URL("http://web.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162069500");
            conn=url.openConnection();

            DocumentBuilderFactory f= DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(conn.getInputStream());

            doc.getDocumentElement().normalize();

            NodeList data = doc.getElementsByTagName("data");
            for(int i=0;i<data.getLength();i++) {
                weatherDo weatherdo = new weatherDo();
                Element e = (Element) data.item(i);
                if(e.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                String hour = e.getElementsByTagName("hour").item(0).getTextContent(); //트리구조이기떄문에 첫번째데이터 item(0)을 해줘야함. 의 데이터를 꺼내준다.
                String temp = e.getElementsByTagName("temp").item(0).getTextContent();
                String wfEn = e.getElementsByTagName("wfEn").item(0).getTextContent();
                weatherdo.setHour(Integer.parseInt(hour));
                weatherdo.setTemp(Double.parseDouble(temp));
                weatherdo.setWfen(wfEn);

                weatherList.add(weatherdo);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        }

        model.addAttribute("weather",weatherList);
        return "main";
    }

    @RequestMapping(value="/flot")
    public String flot(){
        return "flot";
    }

    @RequestMapping(value="/test1234")
    public String test(){
        TimeTableLoad timeTableLoad = new TimeTableLoad();
        timeTableMongoRepository.save(timeTableLoad.send());

        return "main";
    }

    @RequestMapping(value="/morris")
    public String morris(){
        return "morris";
    }
    @RequestMapping(value="/tables", method = RequestMethod.GET)
    public String tables(Model model, @RequestParam(required = false) String room){
        List<ClassDo> boards = timeTableMongoRepository.findAll();
        List<RoomDo> rooms = roomMongoRepository.findByBuilding("18");



        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        Calendar cal = Calendar.getInstance();
        int num = cal.get(Calendar.DAY_OF_WEEK)-3;
        String today = week[num];
        System.out.println("test"+boards.get(0).getName());
        System.out.println(today);


        for (int i=0;i<boards.size();) { //i<boards.size()
            int j = boards.get(i).getRoom().indexOf(today);
            if(j != -1) {
                boards.get(i).setTest(boards.get(i).getRoom().charAt(j+1));
                boards.get(i).setTest(boards.get(i).getRoom().charAt(j+2));
                i++;

                for(RoomDo roomDo: rooms) {
                    if(roomDo.getRoom().equals(boards.get(i-1).getProfessor())) {
                        roomDo.setTime(boards.get(i-1));
                    }
                }
            }
            else {

                boards.remove(i);

            }




        }


//        for (RoomDo roomDo:rooms) {
//
//            System.out.println(roomDo.get);
//
//        }

        model.addAttribute("boards", boards);
        model.addAttribute("rooms", rooms);

        return "tables";
    }

    @RequestMapping(value="/calendar")
    public String calendar(){
        return "calendar";
    }
}
