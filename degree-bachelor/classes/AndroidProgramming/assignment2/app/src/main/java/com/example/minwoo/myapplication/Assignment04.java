package com.example.minwoo.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Assignment04 extends AppCompatActivity {

    private CustomListAdapter adapter;
    private ListView listView;
    final int DATA_ADD = 1;     // 추가 Activty
    final int DATA_MODIFY = 2;  // 수정 Activty
    Assignment04_DBAdapter db;  // DB adapter
    boolean dbOpen;
    Cursor currentCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonebook_list_adapter);

        Button button_add = (Button)findViewById(R.id.add);
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(itemClickListener);
        adapter = new CustomListAdapter(getBaseContext(), R.layout.phonebook_list_row);



        db = new Assignment04_DBAdapter(this);
        db.open();
        dbOpen = true;

        currentCursor = db.fetchAllData();  // 이전에 db 에 추가 했던 데이터들 불러옴
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Assignment04.this, Assignment04_add.class);
                startActivityForResult(intent,DATA_ADD); // 추가 Activty 실행
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!dbOpen) {
            db.open();
            dbOpen = true;
        }
        if(requestCode==DATA_ADD){
            if(resultCode==RESULT_OK){// 추가 Activity 로부터 응답
                String input[]={data.getStringExtra("INPUT_NAME"),data.getStringExtra("INPUT_PHONE")};
                db.addData(input[0], input[1]);     // DB에 정보 추가

            }
        }
        else{
            if(resultCode==RESULT_OK){// 편집 Activity 로부터 응답
                String input[]={data.getStringExtra("INPUT_NUM"),data.getStringExtra("INPUT_NAME"),data.getStringExtra("INPUT_PHONE")};
                db.modifyData(Integer.parseInt(input[0]),input[1],input[2]); // 수정된 정보 DB에 반영

            }
        }
        currentCursor = db.fetchAllData(); // DB 정보 불러오기
        adapter.notifyDataSetChanged(); // 변경된 정보 반영

    }
    @Override
    protected void onResume() {
        super.onResume();
        /*if (!dbOpen) {
            db.open();
            dbOpen = true;
        }*/

        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onPause() {
        super.onPause();
        /*if (dbOpen) {
            db.close();
            dbOpen = false;
        }*/
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(Assignment04.this);
            builder.setTitle("데이터 수정/삭제");
            currentCursor.moveToPosition(position); // 선택된 DB 데이터로 커서 이동
            builder.setMessage(currentCursor.getString(1)+" "+currentCursor.getString(2));
            builder.setCancelable(false);
            builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.delData(currentCursor.getString(0)); // 선택된 정보 DB에서 삭제
                    currentCursor = db.fetchAllData();
                    adapter.notifyDataSetChanged(); // 변경된 정보 반영
                }
            });
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setNeutralButton("수정", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Assignment04.this, Assignment04_add.class);
                    intent.putExtra("INPUT_NAME",currentCursor.getString(1)); // 편집 이름 전달
                    intent.putExtra("INPUT_PHONE", currentCursor.getString(2));// 편집 번호 전달
                    intent.putExtra("INPUT_NUM",currentCursor.getString(0)); // DB Column number 전달
                    setResult(RESULT_OK, intent);
                    startActivityForResult(intent,DATA_MODIFY); // 수정 Activity 실행
                }
            });
            builder.create();
            builder.show();
        }
    };
    public class CustomListAdapter extends BaseAdapter {
        Context my_context;
        private int mRowLayout;

        CustomListAdapter(Context context, int layout) {
            my_context = context;
            mRowLayout = layout;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public int getCount() {
            return currentCursor.getCount();
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            ListViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater inflater=(LayoutInflater)my_context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

                convertView = inflater.inflate(mRowLayout, parent, false);

                viewHolder = new ListViewHolder();
                viewHolder.numView = (TextView)convertView.findViewById(R.id.row_num);
                viewHolder.nameView = (TextView)convertView.findViewById(R.id.row_name);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ListViewHolder)convertView.getTag();
            }
            currentCursor.moveToPosition(position);                     // 불러온 쿼리중 position 행으로 이동
            viewHolder.numView.setText(currentCursor.getString(1));     // position 행 중 1번째 열 값 뷰에 출력
            viewHolder.nameView.setText(currentCursor.getString(2));    // position 행 중 2번째 열 값 뷰에 출력

            return convertView;
        }
        private class ListViewHolder {
            TextView numView;
            TextView nameView;
        }
    }
}
