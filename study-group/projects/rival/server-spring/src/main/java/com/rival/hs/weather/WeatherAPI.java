package com.rival.hs.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Minwoo on 2017. 3. 31..
 */
public class WeatherAPI {

    StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/");
    String serviceKey = "iJf7jsFaeeLEI38DL8mbp%2BwekmXjhWJPOKMzGCSj53kMltkJEX6H%2F%2F%2BG7wRTChMa6bFzuPwHTuYTFQ5WIqQjCQ%3D%3D";



    /**
     * 실황정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 조회 조건으로 자료구분코드, 실황값, 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능
     */
    public void getForecastGrib(String base_date, String base_time, float nx, float ny) {
        try {
            urlBuilder.append("ForecastGrib");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + base_date); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + base_time); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("Nx","UTF-8") + "=" + String.valueOf(nx)); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("Ny","UTF-8") + "=" + String.valueOf(ny)); /*Service Key*/
            send(urlBuilder.toString());

        }
        catch (Exception e){
        }
    }

    /**
     * 초단기예보정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 조회 조건으로 자료구분코드, 예보값, 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능
     */
    public void getForecastTimeData(String base_date, String base_time, float nx, float ny) {
        try {
            urlBuilder.append("ForecastTimeData");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + base_date); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + base_time); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("Nx","UTF-8") + "=" + String.valueOf(nx)); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("Ny","UTF-8") + "=" + String.valueOf(ny)); /*Service Key*/
            send(urlBuilder.toString());

        }
        catch (Exception e){
        }
    }

    /**
     * 동네예보 정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X좌표, 예보지점 Y 좌표의 조회 조건으로 발표일자, 발표시각, 자료구분문자, 예보 값, 예보일자, 예보시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능
     */
    public void getForecastSpaceData(String base_date, String base_time, float nx, float ny) {
        try {
            urlBuilder.append("ForecastSpaceData");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + base_date); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + base_time); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("Nx","UTF-8") + "=" + String.valueOf(nx)); /*Service Key*/
            send(urlBuilder.toString());

        }
        catch (Exception e){
        }
    }

    /**
     * 동네예보정보조회서비스 각각의 오퍼레이션(초단기실황, 초단기예보, 동네예보)들의 수정된 예보 버전을 파악하기 위해 예보버전을 조회하는 기능
     */
    public void getForecastVersionCheck(String ftype, String basedatetime) {
        try {
            urlBuilder.append("ForecastVersionCheck");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("ftype","UTF-8") + "=" + ftype); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("basedatetime","UTF-8") + "=" + basedatetime); /*Service Key*/
            send(urlBuilder.toString());
        }
        catch (Exception e){
        }
    }


    public void send(String url1) {

        try {
            URL url = new URL(url1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());

        }
        catch (Exception e) {

        }
    }


    public static void main(String[] args) {

        WeatherAPI test = new WeatherAPI();
        //test.send();


    }
}
