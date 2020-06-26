package com.example.minwoo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Minwoo on 2016. 5. 31..
 */

public class Assignment04_DBAdapter {
    private final Context context;
    static final String DB = "assignment04";    //  DB 이름
    static final String TABLE = "data";         //  Table 이름
    public static final String DBTBL_NAME  = "name";	// column 이름
    public static final String DBTBL_TEL   = "tel";	    // column 이름
    static final int DB_VERSION = 1;
    static final String CREATE = "create table " + TABLE +
            " (_id integer primary key autoincrement, " +
            DBTBL_NAME + " text, " +
            DBTBL_TEL + " text);";
    static final String DROP = "drop table ";
    private SQLiteDatabase db;
    private OpenHelper dbHelper;
    public Assignment04_DBAdapter(Context ctx) {
        context = ctx;
    }
    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context c) {
            super(c, DB, null, DB_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE);
        }
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
            db.execSQL(DROP + TABLE);
            db.execSQL(CREATE);
        }
    }
    public Assignment04_DBAdapter open() throws SQLException {
        dbHelper = new OpenHelper(context);
        db = dbHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        dbHelper.close();
    }
    // db 모든 데이터 불러오는 메소드
    public Cursor fetchAllData() {

        return db.query(TABLE, null, null, null, null, null, null);
    }
    // db column 추가하기 위한 메소드
    public long addData(String name, String tel) {
        ContentValues values = new ContentValues();
        values.put(DBTBL_NAME, name);
        values.put(DBTBL_TEL, tel);
        long id = db.insert(TABLE, null, values);
        return id;
    }
    // db column 삭제하기 위한 메소드
    public void delData(String id) {
        db.delete(TABLE, "_id = ?", new String[] {id});
    }
    // db column 수정하기 위한 메소드
    public void modifyData(long id, String name, String tel) {
        ContentValues values = new ContentValues();
        values.put(DBTBL_NAME, name);
        values.put(DBTBL_TEL, tel);
        db.update(TABLE, values, "_id='" + id + "'", null);

    }
}

