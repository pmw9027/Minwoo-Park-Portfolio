package com.rival.hs.stadium.access;

import com.rival.hs.stadium.domain.StadiumDo;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Minwoo on 2017. 4. 1..
 */
public class StadiumXMLParser {

    public ArrayList<StadiumDo> parser(String input) throws Exception{

        ArrayList output = new ArrayList<StadiumDo>();

        InputSource is = new InputSource(new StringReader(input));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//*/com.google.gson.internal.LinkedTreeMap";
        NodeList cols = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);

        for( int idx=0; idx<cols.getLength(); idx++ ) {
            StadiumDo stadiumDo = new StadiumDo();

            for (int j=1; j < cols.item(idx).getChildNodes().getLength() ;j+=2) {


                String key = cols.item(idx).getChildNodes().item(j).getChildNodes().item(1).getTextContent();
                String value = cols.item(idx).getChildNodes().item(j).getChildNodes().item(3).getTextContent();

                if(value.equals(""))
                    continue;
                switch(key) {
                    case "개방시설유형구분":
                        stadiumDo.setType(value);
                        break;
                    case "사용료":
                        stadiumDo.setFare(value);
                        break;
                    case "평일운영시작시각":
                        stadiumDo.setWeekday_time_start(value);
                        break;
                    case "주말운영종료시각":
                        stadiumDo.setHoliday_time_end(value);
                        break;
                    case "사용안내전화번호":
                        stadiumDo.setPhone_num(value);
                        break;
                    case "사용기준시간":
                        stadiumDo.setStandard_time(value);
                        break;
                    case "경도":
                        stadiumDo.setLongitude(value);
                        break;
                    case "휴관일":
                        stadiumDo.setHoliday(value);
                        break;
                    case "개방장소명":
                        stadiumDo.setLocation_name(value);
                        break;
                    case "개방시설명":
                        stadiumDo.setStadium_name(value);
                        break;
                    case "신청방법구분":
                        stadiumDo.setBook_way(value);
                        break;
                    case "홈페이지주소":
                        stadiumDo.setHomepage(value);
                        break;
                    case "담당부서명":
                        stadiumDo.setDepartment(value);
                        break;
                    case "주말운영시작시각":
                        stadiumDo.setHoliday_time_start(value);
                        break;
                    case "부대시설정보":
                        stadiumDo.setInformation(value);
                        break;
                    case "데이터기준일자":
                        stadiumDo.setData_time(value);
                        break;
                    case "위도":
                        stadiumDo.setLatitude(value);
                        break;
                    case "초과사용단위시간":
                        //stadiumDo.setExcess_fare(value);
                        break;
                    case "관리기관명":
                        stadiumDo.setManagement_agency(value);
                        break;
                    case "유료사용여부":
                        stadiumDo.setB_fee(value);
                        break;
                    case "면적":
                        stadiumDo.setArea(value);
                        break;
                    case "평일운영종료시각":
                        stadiumDo.setWeekday_time_end(value);
                        break;
                    case "초과사용료":
                        stadiumDo.setExcess_fare(value);
                        break;
                    case "수용가능인원수":
                        stadiumDo.setCapacity(value);
                        break;
                    case "소재지도로명주소":
                        stadiumDo.setRoad_address(value);
                        break;
                    case "_id":
                        stadiumDo.setId(value);
                        break;
                    case "시설사진정보":
                        stadiumDo.setPicture(value);
                        break;
                    default:
                        System.out.println(key);
                        break;
                }
            }
            output.add(stadiumDo);
        }
        return output;
    }
}
