package com.soundberry.hackers.soundberry;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

import static android.content.ContentValues.TAG;

/**
 * Created by Minwoo on 2016. 9. 23..
 */

public class Section1Fragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView tv;
    public Section1Fragment() {



    }
    public static Section1Fragment newInstance(int sectionNumber) {
        Section1Fragment fragment = new Section1Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onResume() {


        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_section1, container, false);

        tv = (TextView) rootView.findViewById(R.id.tv);
        new myAsyncTask().execute();

        return rootView;

    }

    public class myAsyncTask extends AsyncTask<Void, String, String> {


        URL url;
        HttpURLConnection conn;
        BufferedReader reader;
        InputStream is;
        String line;
        StringBuilder builder;
        @Override
        protected void onPreExecute() {

            builder = new StringBuilder();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

            while (true){

                try {
                    url = new URL("http://14.63.196.214:8000/AAAA");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setDefaultUseCaches(false);
                    Thread.sleep(3000);

                    while (true) {
                        is = conn.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }

                        // Log.e(TAG, builder.toString());

                        publishProgress(builder.toString());

                        Thread.sleep(3000);
                    }

                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: " + e.toString());
                    publishProgress("Server is down");

                }

            }

        }

        @Override
        protected void onPostExecute(String aVoid) {


            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String lastconnect = null;
            String token = null;
            String user = null;
            // Log.e(TAG, "onProgressUpdate: "+values[0] );

            try {
                JSONObject jsonObject = new JSONObject(values[0]);
                // JSONArray jarray = new JSONArray(values[0]);   // JSONArray 생성
                for(int i=0; i < jsonObject.length(); i++){
                    // JSONObject jObject = jsonObject.getJSONObject(i);  // JSONObject 추출
                    lastconnect = jsonObject.getString("lastconnect");
                    token = jsonObject.getString("token");
                    user = jsonObject.getString("user");
                }
                tv.setText(values[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

    }



}

