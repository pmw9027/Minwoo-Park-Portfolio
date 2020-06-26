package com.example.minwoo.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

public class PhonebookActivity extends AppCompatActivity {

    private CustomListAdapter adapter;
    private ListView listView;


    ArrayList<String[]> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonebook_list_adapter);
        dataList = new ArrayList<String[]>();
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(itemClickListener);
        adapter = new CustomListAdapter(getBaseContext(), R.layout.phonebook_list_row);
        listView.setAdapter(adapter);
        ((Button)findViewById(R.id.add)).setOnClickListener(addListener);
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

    View.OnClickListener addListener = new View.OnClickListener() {
        public void onClick(View view) {
            final Dialog dialog = new Dialog(PhonebookActivity.this);
            dialog.setContentView(R.layout.phonebook_dialog);
            Button save = (Button)dialog.findViewById(R.id.button_add);
            Button cancel = (Button)dialog.findViewById(R.id.button_cancel);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] input = new String[2];

                    EditText input_name = (EditText)dialog.findViewById(R.id.input_name);
                    EditText input_phone = (EditText)dialog.findViewById(R.id.input_phone);
                    input[0]  = input_name.getText().toString();
                    input[1] = input_phone.getText().toString();
                    dataList.add(input);
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
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(PhonebookActivity.this);
            builder.setTitle("데이터 삭제");
            builder.setMessage("삭제 하시겠습니까?");
            builder.setCancelable(false);
            builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dataList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
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
            //
            if (convertView == null) {
                LayoutInflater inflater=(LayoutInflater)my_context.getSystemService(
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
