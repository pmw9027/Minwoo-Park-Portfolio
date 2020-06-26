package com.example.minwoo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnClickEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click);
        Button button1 =(Button)findViewById(R.id.button1);
        Button button2 =(Button)findViewById(R.id.button2);
        Button button3 =(Button)findViewById(R.id.button3);

        button1.setOnClickListener(myListener());


    }
    View.OnClickListener myListener(){

        View.OnClickListener myListener;
        myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button1:
                        break;
                    case R.id.button2:
                        break;
                    case R.id.button3:
                        break;
                }
            }
        };
        return myListener;
    }
}
