package com.rival.hs.kakao;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Minwoo on 2017. 3. 22..
 */
public class JsonParser {

    JSONParser jsonParser;
    JSONObject jsonObject;
    JSONObject jsonObject2;

    public JsonParser() {

        this.jsonParser = new JSONParser();

    }

//    public Kakao_info parse(String jsondata) {
//        try {
//
//            jsonObject = (JSONObject) jsonParser.parse(jsondata);
//            jsonObject2 = (JSONObject)jsonObject.get("properties");
//
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return new Kakao_info(jsonObject.get("id").toString(),new Kakao_properties(jsonObject2.get("nickname").toString(), jsonObject2.get("profile_image").toString(), jsonObject2.get("thumbnail_image").toString()));
//    }


}
