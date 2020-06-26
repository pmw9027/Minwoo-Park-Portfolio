package com.example.minwoo.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ListAdapterActivity extends AppCompatActivity {

    private CustomListAdapter adapter;
    private ListView listView;
    ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_adapter);
        dataList = new ArrayList<String>();
        // get reference of list & adapter
        dataList.add("abc");
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(itemClickListener);
        adapter = new CustomListAdapter(getBaseContext(), R.layout.custom_list_row);
        listView.setAdapter(adapter);
        ((Button)findViewById(R.id.add)).setOnClickListener(addListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        adapter.notifyDataSetChanged();
    }
    //
    @Override
    protected void onPause() {
        super.onPause();
    }

    View.OnClickListener addListener = new View.OnClickListener() {
        public void onClick(View view) {
            final Dialog dialog = new Dialog(getApplicationContext());
            dialog.setTitle("데이터 추가");
            dialog.setContentView(R.layout.custom_list_dialog);
            // 여기에 데이터 추가 코드 작성
            Button save = (Button)findViewById(R.id.add);
            Button cancel = (Button)findViewById(R.id.button_cancel);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText input = (EditText)dialog.findViewById(R.id.input);
                    String str = input.getText().toString();
                    dataList.add(str);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    };
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), dataList.get(position), Toast.LENGTH_SHORT).show();

        }
    };

    //////////////////////////////////////////////////////////////////////////////////////
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
            //
            if (convertView == null) {
                LayoutInflater    inflater=(LayoutInflater)my_context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(mRowLayout, parent, false);
                //
                viewHolder = new ListViewHolder();
                viewHolder.numView = (TextView)convertView.findViewById(R.id.row_num);
                viewHolder.nameView = (TextView)convertView.findViewById(R.id.row_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ListViewHolder)convertView.getTag();
            }

            viewHolder.numView.setText("" + position);
            viewHolder.nameView.setText(dataList.get(position));
            return convertView;
        }

        private class ListViewHolder {
            TextView numView;
            TextView nameView;
        }
    }

}

