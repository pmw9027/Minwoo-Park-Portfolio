package com.lpk.energy.weather;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-04-07.
 */
@RestController
public class weatherController {
    @RequestMapping(value="/test")
    public double test()
    {
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
                if(e.getNodeType() !=Node.ELEMENT_NODE)
                    continue;

                String hour = e.getElementsByTagName("hour").item(0).getTextContent(); //트리구조이기떄문에 첫번째데이터 item(0)을 해줘야함. 의 데이터를 꺼내준다.
                String temp = e.getElementsByTagName("temp").item(0).getTextContent();
                String wfEn = e.getElementsByTagName("wfEn").item(0).getTextContent();
                System.out.println("index"+i);
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


        return weatherList.get(1).getTemp();
    }
}

