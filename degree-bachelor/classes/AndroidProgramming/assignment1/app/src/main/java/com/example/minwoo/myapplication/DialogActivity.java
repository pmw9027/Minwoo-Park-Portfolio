package com.example.minwoo.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class DialogActivity extends AppCompatActivity {

    final int DIALOG_PAUSED_ID = 1;
    final int DIALOG_YES_NO_MESSAGE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_PAUSED_ID);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_MESSAGE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Test AlertBuilder");
                builder.setMessage("Hello");
                builder.setCancelable(false);
                builder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogActivity.this.finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog loginDialog = new Dialog(DialogActivity.this);
                loginDialog.setContentView(R.layout.custom_dialog);
                loginDialog.setTitle("Custom_dialog");
                final EditText edit_id = (EditText)findViewById(R.id.username);
                EditText edit_pw = (EditText)findViewById(R.id.password);
                Button button1 = (Button)findViewById(R.id.button_login);
                Button button2 = (Button)findViewById(R.id.button_cancel);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),edit_id.getText(), Toast.LENGTH_LONG).show();

                    }
                });
                loginDialog.show();

            }
        });

    }
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder;
        AlertDialog alert;
        builder = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_PAUSED_ID:
                builder.setTitle("Test AlertBuilder");
                builder.setMessage("Hello");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                        DialogActivity.this.finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert = builder.create();
                return alert;
            case DIALOG_YES_NO_MESSAGE:
                final CharSequence[] items ={ "Red", "Green", "Blue" };
                builder.setTitle("색상을 선택하시오");
                builder.setSingleChoiceItems(items, -1, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                Toast.makeText(getApplicationContext(), items[item],
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                alert = builder.create();
                return alert;
        }
        return null;

    }
}
