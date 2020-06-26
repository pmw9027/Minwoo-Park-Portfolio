package com.rival.hs.kakao;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Minwoo on 2017. 3. 22..
 */
public class KakaoAPI {

    URL url;
    URLConnection conn;
    StringBuilder stringBuilder;
    DataOutputStream out;
    public String send(String access_token){
        stringBuilder = new StringBuilder();
        out = null;

        try {
            url = new URL("https://kapi.kakao.com/v1/user/me");
            conn = url.openConnection();

            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Authorization", "Bearer {"+access_token+"}");


            try {
                out = new DataOutputStream(conn.getOutputStream());
                out.flush();
            } finally {
                if (out != null) out.close();
            }

            InputStream is = conn.getInputStream();
            Scanner scan = new Scanner(is);

            while (scan.hasNext()) {
                String str = scan.nextLine();
                stringBuilder.append(str);

            }
        }
        catch (Exception error) {

            System.out.println(error);

        }
        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }

}
