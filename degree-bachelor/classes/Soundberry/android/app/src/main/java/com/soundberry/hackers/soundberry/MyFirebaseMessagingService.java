package com.soundberry.hackers.soundberry;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by Minwoo on 2016. 9. 29..
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    ChatMessage msg;
    public static final String INTENT_FILTER = "INTENT_FILTER";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // super.onMessageReceived(remoteMessage);
        Log.e(TAG, "onMessageReceived: "+ remoteMessage.getData() );

        msg = new ChatMessage();
        msg.setId(2);
        msg.setMe(false);
        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        String name=null;
        String time=null;
        try {
            JSONObject jsonObject = new JSONObject(remoteMessage.getData());
            // JSONArray jarray = new JSONArray(values[0]);   // JSONArray 생성
            for(int i=0; i < jsonObject.length(); i++){
                // JSONObject jObject = jsonObject.getJSONObject(i);  // JSONObject 추출
                name = jsonObject.getString("name");
                time = jsonObject.getString("time");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        msg.setMessage(time+"에 "+name+"소리가 들렸어요");

        MainActivity.tab2.chatHistory.add(msg);
        MainActivity.tab2.adapter.add(msg);
        Intent intent = new Intent(INTENT_FILTER);
        sendBroadcast(intent);
        //MainActivity.tab2.adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }


}