package com.example.minwoo.myapplication;
import android.app.Activity;
import android.os.Bundle;

public class Preference_Setting_Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new Preference_Fragment()).commit();
    }
}





