package com.soundberry.hackers.soundberry;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by Minwoo on 2016. 9. 29..
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public MyFirebaseInstanceIdService() {
    }


    @Override
    public void onTokenRefresh() {


        URL url;
        HttpURLConnection conn;
        InputStream is;
        BufferedReader reader;
        String line;
        StringBuilder builder;
        try{

            url = new URL("http://pmw.chickenkiller.com:800/FCM/AAA/"+FirebaseInstanceId.getInstance().getToken());
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            is = conn.getInputStream();
            builder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            Log.e(TAG, "onTokenRefresh: test" );
            while((line = reader.readLine()) != null) {
                builder.append(line+"\n");
                Log.e(TAG, "doInBackground: "+ line);
            }
        } catch(Exception e){
            Log.e(TAG, "doInBackground: "+e.toString() );
        }



        super.onTokenRefresh();
    }
}
