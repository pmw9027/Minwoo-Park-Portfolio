package com.example.minwoo.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        Button setting = (Button)findViewById(R.id.settings);
        Button show = (Button)findViewById(R.id.show);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Preference_Setting_Activity.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String  settingText = "";
                settingText = "사용자이름 사용 : " + sharedPrefs.getBoolean("useUserName", false);
                if (sharedPrefs.getBoolean("useUserName", false)) {
                    settingText = settingText + "\n사용자 이름 : " + sharedPrefs.getString("userName", "");
                    settingText = settingText + "\n이름 공개범위 : " + sharedPrefs.getString("userNameOpen", "");
                }
                TextView textView = (TextView)findViewById(R.id.textView1);
                textView.setText(settingText);
            }
        });
    }
}

