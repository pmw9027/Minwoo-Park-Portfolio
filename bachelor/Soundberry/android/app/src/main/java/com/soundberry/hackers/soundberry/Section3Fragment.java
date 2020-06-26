package com.soundberry.hackers.soundberry;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Minwoo on 2016. 9. 23..
 */

public class Section3Fragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";



    public Section3Fragment() {

    }

    public static Section3Fragment newInstance(int sectionNumber) {
        Section3Fragment fragment = new Section3Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {


        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_section3, container, false);

        return rootView;

    }

}

