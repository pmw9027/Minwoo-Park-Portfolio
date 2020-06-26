package com.example.minwoo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.LinearLayout;

public class CodeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout manager = new LinearLayout(this);
        manager.setOrientation(LinearLayout.VERTICAL);

        Button button1 = new Button(this);
        Button button2 = new Button(this);

        button1.setText("Vertical");
        button2.setText("Horizontalw");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        manager.addView(button1);
        manager.addView(button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.setOrientation(LinearLayout.VERTICAL);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.setOrientation(LinearLayout.HORIZONTAL);
            }
        });
        setContentView(manager);
    }
}
