package com.soundberry.hackers.soundberry;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import static com.google.android.gms.internal.zzs.TAG;
import static java.net.Proxy.Type.HTTP;

/**
 * Created by Minwoo on 2016. 9. 23..
 */

public class MySocket {
    private static String HOST = "http://14.63.196.214:8004";
    private static int PORT = 8004;
    private static Socket socket;
    private BufferedReader networkReader;
    private BufferedWriter networkWriter;


    public MySocket() throws IOException{
        try{
            send(1);
        }
        catch (Exception e){
            Log.e(TAG, "MySocket: ",e );
        }


    }
    public void send(int i) throws IOException{

        try{

            sendThread.start();

        }
        catch (Exception e){

        }
    }
    private Thread sendThread = new Thread() {
        HttpClient httpClient = new DefaultHttpClient();

        public void run() {

            try {
                socket = new Socket(HOST,PORT);
                URI url = new URI(HOST);

                HttpPost httpPost = new HttpPost();
                httpPost.setURI(url);

                List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("userId", MainActivity.session.getID()));
                nameValuePairs.add(new BasicNameValuePair("iotNum", MainActivity.session.getIotNum()));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                while(true) {
                    //HttpResponse response =
                    httpClient.execute(httpPost);
                    //String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                    //Log.d(TAG, responseString);
                }

            } catch (URISyntaxException e) {
                Log.e(TAG, e.getLocalizedMessage());
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                Log.e(TAG, e.getLocalizedMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, e.getLocalizedMessage());
                e.printStackTrace();
            }



        }
    };

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        socket.close();
    }
}
