package com.example.minwoo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Assignment03_add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment03_add);
        Button button_save = (Button)findViewById(R.id.button_add);
        Button button_canel = (Button)findViewById(R.id.button_cancel);
        Intent intent = getIntent();
        final EditText name = (EditText)findViewById(R.id.input_name);
        final EditText phone = (EditText)findViewById(R.id.input_phone);
        final int number = intent.getIntExtra("DATA_NUM",0);    // 전달 받은 Arraylist position 변수에 저장
        name.setText(intent.getStringExtra("INPUT_NAME"));      // 전달 받은 name view에 표시
        phone.setText(intent.getStringExtra("INPUT_PHONE"));    // 전달 받은 phone_number view에 표시
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("INPUT_NAME", name.getText().toString());   // 입력된 name 전달
                intent.putExtra("INPUT_PHONE", phone.getText().toString()); // 입력된 번호 전달
                intent.putExtra("DATA_NUM",number);                         // 받은 Arraylist position 다시 전달
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        button_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
