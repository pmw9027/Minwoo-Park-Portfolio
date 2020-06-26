package com.soundberry.hackers.soundberry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import static com.google.android.gms.internal.zzs.TAG;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseInstanceId mFirebaseInstanceId;
    public static Session session;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private PagerAdapter Padapter;
    public Section1Fragment tab1;
    static Section2Fragment tab2;
    public Section3Fragment tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        registerReceiver(myReceiver, new IntentFilter(MyFirebaseMessagingService.INTENT_FILTER));
        // mFirebaseInstanceId = FirebaseInstanceId.getInstance();
        // Log.e("park", "onCreate: "+ mFirebaseInstanceId.getToken());
        // FirebaseMessaging.getInstance().subscribeToTopic("message");

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Status"));
        tabLayout.addTab(tabLayout.newTab().setText("Dialog"));
        tabLayout.addTab(tabLayout.newTab().setText("Setting"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_sound_log);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_sound_list);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_setting);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Set up the ViewPager with the sections adapter.
        Padapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(Padapter);
        // mViewPager.setAdapter(mSectionsPagerAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Section2Fragment.adapter.notifyDataSetChanged();
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    tab1 = new Section1Fragment();
                    //tabLayout.getTabAt(0).select();
                    return tab1;
                case 1:
                    tab2 = new Section2Fragment();
                    //tabLayout.getTabAt(1).select();
                    return tab2;
                case 2:
                    tab3 = new Section3Fragment();
                    //tabLayout.getTabAt(2).select();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

}
