package com.soundberry.hackers.soundberry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Minwoo on 2016. 9. 23..
 */

public class Section2Fragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private EditText messageET;
    private ListView messagesContainer;
    private Button sendBtn;
    public static ChatAdapter adapter;
    public static ArrayList<ChatMessage> chatHistory;
    public Section2Fragment() {

    }
    public static Section2Fragment newInstance(int sectionNumber) {
        Section2Fragment fragment = new Section2Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onResume() {


        adapter.notifyDataSetChanged();
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_section2, container, false);


        messagesContainer = (ListView)rootView.findViewById(R.id.messagesContainer);

        messageET = (EditText)rootView.findViewById(R.id.messageEdit);
        sendBtn = (Button)rootView.findViewById(R.id.chatSendButton);

        //TextView meLabel = (TextView)rootView.findViewById(R.id.meLbl);
        //TextView companionLabel = (TextView)rootView.findViewById(R.id.friendLabel);
        RelativeLayout container2 = (RelativeLayout)rootView.findViewById(R.id.container);
        //companionLabel.setText("My Buddy");// Hard Coded

        chatHistory = new ArrayList<ChatMessage>();



        adapter = new ChatAdapter(getActivity(), new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);
        for(int i=0; i<chatHistory.size(); i++) {
            ChatMessage message = chatHistory.get(i);
            adapter.add(message);
            adapter.notifyDataSetChanged();
            messagesContainer.setSelection(messagesContainer.getCount() - 1);
        }


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(122);//dummy
                chatMessage.setMessage(messageText);
                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                chatMessage.setMe(true);

                messageET.setText("");

                adapter.add(chatMessage);
                adapter.notifyDataSetChanged();
                messagesContainer.setSelection(messagesContainer.getCount() - 1);
            }
        });


        return rootView;

    }

}

