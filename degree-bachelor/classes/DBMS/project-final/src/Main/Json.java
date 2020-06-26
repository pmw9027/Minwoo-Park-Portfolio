package Main;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by pmw90 on 2015-12-06.
 */
public class Json {
    TourVo tour ;


    Json(TourVo tour){
        this.tour = tour;
        InputStream is =getInputStreamFromUrl(tour.getContentID());
        String result = convertStreamToString(is);
        System.out.println(result);
        festivaljsonParserList(result);
    }
    Json(String Cid){
        this.tour = new TourVo();
        tour.setContentID(Cid);
        InputStream is =getInputStreamFromUrl(tour.getContentID());
        String result = convertStreamToString(is);
        System.out.println(result);
        festivaljsonParserList(result);
    }
    private static String convertStreamToString(InputStream is)
    {
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
    public InputStream getInputStreamFromUrl(String code) {
        InputStream content = null;
        String URL = null;
        String base = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?";
        String myKey = "ServiceKey=YUiU%2F%2BiwXOmqlEHW0YbPM0lBlY22JeKjxNMz%2FHsjw2OtMygbwCG9wKUqs%2Bz63QE5ZUzr2YTFH8Jr95FwQUza2g%3D%3D";
        String MobileOS = "&MobileOS=ETC";
        String MobileAPP = "&MobileApp=AppTesting";
        String contentid = "&addrinfoYN=Y&defaultYN=Y&firstImageYN=Y&overviewYN=Y&contentId="
                + code;
        String para = "&numOfRows=20&pageNo=1";
        String type = "&_type=json";
        URL = base + myKey + contentid + MobileOS + MobileAPP + para + type;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(URL));
            content = response.getEntity().getContent();
        } catch (Exception e) {
            //Log.e("[GET REQUEST]", "Network exception", e);
        }
        return content;
    }
    public void festivaljsonParserList(String pRecvServerPage) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(pRecvServerPage);
            JSONObject localJSONArray = (JSONObject)((JSONObject)((JSONObject) ((JSONObject) jsonObject
                    .get("response")).get("body"))
                    .get("items")).get("item");
            String[] jsonName = { "addr1", "firstimage", "overview",
                    "tel", "title" };
                // m_Adapter.removeall();
                if (localJSONArray != null) {
                    try {
                        tour.setAdrres(localJSONArray.get(jsonName[0]).toString());
                        tour.setImage(localJSONArray.get(jsonName[1]).toString());
                        tour.setOverview(localJSONArray.get(jsonName[2]).toString());
                        tour.setTitle(localJSONArray.get(jsonName[4]).toString());
                        tour.setHomepage(localJSONArray.get(jsonName[3]).toString());
                    } catch (Exception e) {

                    }
                
            }
        } catch (ParseException e){

        }
    }
    public TourVo getTour() {
        return tour;
    }
}
