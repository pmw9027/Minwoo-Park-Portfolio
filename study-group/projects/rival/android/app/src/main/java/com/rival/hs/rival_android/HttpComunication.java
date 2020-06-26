package com.rival.hs.rival_android;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static java.lang.System.out;


/**
 * Created by Minwoo on 2017. 3. 21..
 */

public class HttpComunication {// extends AsyncTask<String, Void, Boolean> {


    public String send(){


        StringBuilder stringBuilder = new StringBuilder();
        DataOutputStream out = null;

        try {
            URL url = new URL("http://192.168.219.135/kakao");
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

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
        catch(MalformedURLException error) {
            Log.e("ERROR", "send: "+error );
            //Handles an incorrectly entered URL
        }
        catch(SocketTimeoutException error) {
            Log.e("ERROR", "send: "+error );
            //Handles URL access timeout.
        }
        catch (IOException error) {
            Log.e("ERROR", "send: "+error );
            //Handles input and output errors
        }
        catch (Exception error) {
            Log.e("ERROR", "send: "+error );

        }
        return "test";
    }
    public String send2(){


        StringBuilder stringBuilder = new StringBuilder();
        DataOutputStream out = null;

        try {
            URL url = new URL("http://192.168.0.19/teamN?name=FC인천");
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

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
        catch(MalformedURLException error) {
            Log.e("ERROR", "send: "+error );
            //Handles an incorrectly entered URL
        }
        catch(SocketTimeoutException error) {
            Log.e("ERROR", "send: "+error );
            //Handles URL access timeout.
        }
        catch (IOException error) {
            Log.e("ERROR", "send: "+error );
            //Handles input and output errors
        }
        catch (Exception error) {
            Log.e("ERROR", "send: "+error );

        }
        return stringBuilder.toString();
    }

}
