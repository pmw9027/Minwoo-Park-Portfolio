package com.example.minwoo.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Assignment03 extends AppCompatActivity {

    private CustomListAdapter adapter;
    private ListView listView;
    final int DATA_ADD = 1; // 추가 Activty
    final int DATA_MODIFY = 2; // 수정 Activty
    ArrayList<String[]> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonebook_list_adapter);

        Button button_add = (Button)findViewById(R.id.add);
        dataList = new ArrayList<String[]>();
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(itemClickListener);
        adapter = new CustomListAdapter(getBaseContext(), R.layout.phonebook_list_row);
        listView.setAdapter(adapter);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Assignment03.this, Assignment03_add.class);
                startActivityForResult(intent,DATA_ADD); // 추가 Activty 실행
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DATA_ADD){
            if(resultCode==RESULT_OK){// 추가 Activity 로부터 응답
                String input[]={data.getStringExtra("INPUT_NAME"),data.getStringExtra("INPUT_PHONE")};
                dataList.add(input);// Arraylist에 추가
                adapter.notifyDataSetChanged(); // 변경된 정보 반영
            }
        }
        else{
            if(resultCode==RESULT_OK){// 편집 Activity 로부터 응답
                String input[]={data.getStringExtra("INPUT_NAME"),data.getStringExtra("INPUT_PHONE")};
                dataList.set(data.getIntExtra("DATA_NUM",0),input); //Arraylist position index 편집
                adapter.notifyDataSetChanged(); // 변경된 정보 반영
            }
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(Assignment03.this);
            builder.setTitle("데이터 삭제");
            builder.setMessage(dataList.get(position)[0].toString()+" "+dataList.get(position)[1].toString());
            builder.setCancelable(false);
            builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dataList.remove(position); // Arraylist position index 삭제
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
                    Intent intent = new Intent(Assignment03.this, Assignment03_add.class);
                    intent.putExtra("INPUT_NAME", dataList.get(position)[0]); // 편집 이름 전달
                    intent.putExtra("INPUT_PHONE", dataList.get(position)[1]);// 편집 번호 전달
                    intent.putExtra("DATA_NUM",position); // ArrayList position 전달
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
            return dataList.size();
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
            } else {
                viewHolder = (ListViewHolder)convertView.getTag();
            }
            viewHolder.numView.setText(dataList.get(position)[0]);
            viewHolder.nameView.setText(dataList.get(position)[1]);
            return convertView;
        }

        private class ListViewHolder {
            TextView numView;
            TextView nameView;
        }
    }

}
