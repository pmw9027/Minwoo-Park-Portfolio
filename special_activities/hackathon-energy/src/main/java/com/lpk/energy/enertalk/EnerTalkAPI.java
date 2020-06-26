
package com.lpk.energy.enertalk;



import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Minwoo on 2017. 4. 7..
 */
public class EnerTalkAPI {

    String token2 = "fd7e971d33e1c80fc1d5b6291d0918597211f85aecd86dbe47ebd578138452a2f3830a6da3b0b9370bd171492e4d9127982b20d38e330b91e65c8e4df72b89df";
    String API_URL_BASE = "https://api2.enertalk.com";
    String URL_BASE = "https://enertalk-auth.encoredtech.com";
    String Token = "https://enertalk-auth.encoredtech.com/token";
    String ID = "d29vcmFteUBlbmNvcmVkdGVjaC5jb21fd29vcmFteQ==";
    String PW = "a21a74et5dq5e27po75y3ah0q20su0ai9hw8kt2";
    static String site_3   = "a50873c0a43159d9ed9d3b7c1ecc6a8ade7480e6";
    static String site_5   = "ba0d418db6ee472484d4e0b5887cac0952e7244e";
    static String site_6   = "b71e16e8713b6a653e2284a96e940a904b3095ca";
    static String site_25  = "cffa1d2a230f66f234cf897642895a77fbe23f6";
    static String site_27  = "a6d504310ab9a4d33b8865cf73427647927352a7";
    static String site_129 = "5981cdc3094bf469e85ab4b7dcdd230a65f5c1c9";

    URL url;

    String API_URL;
    StringBuilder URL;
    StringBuilder stringBuilder;
    DataOutputStream out;

    public HttpURLConnection conn ;



    public EnerTalkAPI() {

        stringBuilder = new StringBuilder();
        API_URL = new String(API_URL_BASE);
        URL = new StringBuilder(URL_BASE);

    }

    public void connSetting(String input_url) throws Exception{

        url = new URL(input_url);
        conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");
        //conn.setDoOutput(true);
        conn.setUseCaches(false);

//        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("accept-version", "2.0.0");
        conn.setRequestProperty("Authorization", "Bearer "+token2);

    }

    public String postToken() throws Exception {

        URL.append("/token");

        connSetting(URL.toString());

        conn.setRequestMethod("POST");

        String param = "{client_id: \"d29vcmFteUBlbmNvcmVkdGVjaC5jb21fd29vcmFteQ==\", client_secret : \"a21a74et5dq5e27po75y3ah0q20su0ai9hw8kt2\", grant_type: \"authorization_code\", code : \"ee4a4008cc8e7efbaf34d95584b3e0c4960ddc54b50e666d16d31acb9ad0cb7aba47954287da391f0a36ed0f6939a9b3704f2d97c315e9b4e06e42a7eaf3a772\"}";


        return send(param).toString();

    }

    public String getSites() throws Exception {


        connSetting(API_URL+"/sites");

        return send(null).toString();

    }

    public void getDevices() throws Exception {




    }

    public String getTags(String input_site) throws Exception {


        connSetting(API_URL + "/sites/"+input_site+"/tags");


        return send(null).toString();

    }

    public RealTimeUsageDo getRealTimeUsage(String input_site) throws Exception {


        ObjectMapper mapper = new ObjectMapper();

        connSetting(API_URL + "/sites/"+input_site+"/usages/realtime");


//      RealTimeUsageDo realTimeUsageDo = mapper.readValue( "{\"timestamp\":1491578441604,\"voltage\":0,\"current\":200102,\"activePower\":34273299,\"billingActivePower\":34273299,\"apparentPower\":45247334,\"reactivePower\":11074184,\"powerFactor\":0.7574656000727026,\"positiveEnergy\":462618322605,\"negativeEnergy\":0,\"positiveEnergyReactive\":54935102011,\"negativeEnergyReactive\":18253505958}", RealTimeUsageDo.class);

        String str = send(null).toString();

        RealTimeUsageDo realTimeUsageDo = mapper.readValue(str , RealTimeUsageDo.class);

        return realTimeUsageDo;

    }

    public String send(String query) throws Exception{

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //System.out.println(response.toString());

        return response.toString();
    }


    public static void main(String[] args) {


        EnerTalkAPI enerTalkAPI = new EnerTalkAPI();

        try {

//            System.out.println(enerTalkAPI.postToken());

//            System.out.println(enerTalkAPI.getSites());

            System.out.println(enerTalkAPI.getTags(EnerTalkAPI.site_3));

        }
        catch (Exception e) {

            try {
                System.out.println("getResponseMessage():" + enerTalkAPI.conn.getResponseMessage());

            }
            catch (Exception ea) {

            }

            System.out.println(e);

        }
    }
}

