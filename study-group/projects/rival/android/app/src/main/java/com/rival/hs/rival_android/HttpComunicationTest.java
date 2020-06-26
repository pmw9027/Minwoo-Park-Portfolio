package com.rival.hs.rival_android;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by user on 2017-04-11.
 */

public class HttpComunicationTest extends AsyncTask<String, String, String> {
    @Override
    protected void onPostExecute(String s) {

        ListActivity.name = s;

        //ListActivity.adapter.addItem(ContextCompat.getDrawable(,R.drawable.kakaotalk_20170318_154059975),"Box",s);





        //ListActivity.adapter.addItem(ContextCompat.getDrawable(, R.drawable.kakaotalk_20170318_154059975),
        //        "Box", "Account Box Black 36dp");

        /**
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kakaotalk_20170318_154059975),
                "Box", "Account Box Black 36dp");
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kakaotalk_20170318_154059975),
                "Circle", "Account Circle Black 36dp");
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kakaotalk_20170318_154059975),
                "Ind", "Assignment Ind Black 36dp");
        */

        super.onPostExecute(s);
    }

    protected String doInBackground(String... urls) {
        StringBuilder stringBuilder = new StringBuilder();
        DataOutputStream out = null;

        try {
            URL url = new URL("pmw.iptime.org/ma");
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
