package com.example.minwoo.myapplication;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLifecycle extends AppCompatActivity {


    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i("LifeCycle","onCreate() call");
        setContentView(R.layout.activity_activitylifecycle);
        Button button1, button2;
        final TextView text;
        text = (TextView) findViewById(R.id.text);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count++;
                text.setText("현재 개수=" + count);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count--;
                text.setText("현재 개수=" + count);
            }
        });
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            text.setText("현재 개수=" + count);
        }
    }
    @Override
    protected void onStart() {
        Log.i("LifeCycle","onStart() call");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.i("LifeCycle","onResume() call");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("LifeCycle","onPause() call");
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("count", count);
    }

    @Override
    protected void onStop() {
        Log.i("LifeCycle","onStop() call");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i("LifeCycle","onRestart() call");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i("LifeCycle","onDestroy() call");
        super.onDestroy();
    }
}
