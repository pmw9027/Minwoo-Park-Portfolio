package com.example.minwoo.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLiteActivity extends AppCompatActivity {
    DBAdapter db;
    boolean dbOpen;
    Cursor currentCursor;
    int COL_NAME;
    int COL_TEL;
    //
    EditText edit_name, edit_tel;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        // make and open database
        db = new DBAdapter(this);
        db.open();
        dbOpen = true;
        // get column index
        currentCursor = db.fetchAllData();
        COL_NAME = currentCursor.getColumnIndex(DBAdapter.DBTBL_NAME);
        COL_TEL = currentCursor.getColumnIndex(DBAdapter.DBTBL_TEL);
        //
        edit_name = (EditText)findViewById(R.id.name);
        edit_tel = (EditText)findViewById(R.id.tel);
        // set button handler
        ((Button)findViewById(R.id.add)).setOnClickListener(addListener);
        ((Button)findViewById(R.id.query)).setOnClickListener(queryListener);
    }
    protected void onResume() {
        super.onResume();
        if (!dbOpen) {
            db.open();
            dbOpen = true;
        }
    }
    //
    @Override
    protected void onPause() {
        super.onPause();
        if (dbOpen) {
            db.close();
            dbOpen = false;
        }
    }
    View.OnClickListener addListener = new View.OnClickListener() {
        public void onClick(View view) {
            String name = edit_name.getText().toString();
            String tel = edit_tel.getText().toString();
            db.addData(name, tel);
            edit_name.setText("");
            edit_tel.setText("");
        }
    };
    View.OnClickListener queryListener = new View.OnClickListener() {
        public void onClick(View view) {
            String name = edit_name.getText().toString();
            Cursor cursor = db.searchDataByName(name);
            if (cursor.moveToFirst()) {
                String tel = cursor.getString(COL_TEL);
                edit_tel.setText(tel);
            }
        }
    };

}

