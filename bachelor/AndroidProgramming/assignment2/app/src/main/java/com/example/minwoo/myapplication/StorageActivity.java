package com.example.minwoo.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StorageActivity extends AppCompatActivity {


    String FILENAME = "test.txt";
    EditText edit, edit2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        edit = (EditText) findViewById(R.id.EditText01);
        Button readButton = (Button) findViewById(R.id.read);
        Button readButton2 = (Button) findViewById(R.id.read2);
        Button writeButton = (Button) findViewById(R.id.write);
        Button writeButton2 = (Button) findViewById(R.id.write);
        readButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(FILENAME);


                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    edit.setText(new String(buffer));
                    fis.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(FILENAME,
                            Context.MODE_PRIVATE);
                    fos.write(edit.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED) == false)
            Toast.makeText(this, "외부 스토리지 쓰기 실패", Toast.LENGTH_SHORT).show();

        else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY) == false)
            Toast.makeText(this, "외부 스토리지 쓰기 / 읽기 실패", Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(this, "외부 스토리지 실패", Toast.LENGTH_SHORT).show();
        edit2 = (EditText) findViewById(R.id.EditText02);
        readButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    InputStream is = new FileInputStream(file);
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    edit2.setText(new String(buffer));
                    is.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        writeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(edit.getText().toString().getBytes());
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

