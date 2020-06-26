package com.rival.hs.rival_android;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.HttpURLConnection;

/**
 * Created by HeeJoongKim on 2017-03-20.
 */

public class ListActivity extends AppCompatActivity {


    ListView listview ;
    static ListViewAdapter adapter;
    String url1,url2,url3;
    static String name = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sportlist);

        //HttpComunication comunication = new HttpComunication();


        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        //리스트뷰 전체 삭제
        //adapter.clearItem();

        System.out.println("name = "+name);
        new HttpComunicationTest().execute(url1,url2,url3);
        System.out.println("name = "+name);


        /*
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kakaotalk_20170318_154059975),
                "Box", "Account Box Black 36dp") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kakaotalk_20170318_154059975),
                "Circle", "Account Circle Black 36dp") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kakaotalk_20170318_154059975),
                "Ind", "Assignment Ind Black 36dp") ;

        */


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                // TODO : use item data.
            }
        }) ;

    }
}
