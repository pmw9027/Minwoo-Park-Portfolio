package com.rival.hs.rival_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.test.suitebuilder.TestMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kakao.auth.ErrorCode;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static android.R.attr.bitmap;

/**
 * Created by HeeJoongKim on 2017-03-18.
 */

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Button footballButton;
    Button baseballButton;
    Button basketballButton;
    Button volley_footballButton;
    Button billiardButton;
    Button bowingButton;

    TextView nick;
    ImageView profile;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        NavigationView navigationV = (NavigationView)findViewById(R.id.nav_view);
        navigationV.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationV.getHeaderView(0);

        nick = (TextView)nav_header_view.findViewById(R.id.nickname);
        profile = (ImageView)nav_header_view.findViewById(R.id.profileiamge);
        requestMe();

        // FootballButton
        footballButton = (Button)findViewById(R.id.footballButton);
        footballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        //BaseballButton
        baseballButton = (Button)findViewById(R.id.baseballButton);
        baseballButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        //BasketballButton
        basketballButton = (Button)findViewById(R.id.basketballButton);
        basketballButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        //VolleyfootballButton
        volley_footballButton = (Button)findViewById(R.id.volleyfootballButton);
        volley_footballButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        //BilliardsButton
        billiardButton = (Button)findViewById(R.id.billiardButton);
        billiardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        //BowingButton
        bowingButton = (Button)findViewById(R.id.bowingButton);
        bowingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if(id == R.id.nav_logout) {
            onClickLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickLogout() {
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {    //나머지 오류
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                //redirectLoginActivity();

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if(result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    //redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {  //세션이 닫힌 경우
                //redirectLoginActivity();
            }

            @Override
            public void onSuccess(final UserProfile userProfile) {    //성공
                Logger.d("UserProfile : " + userProfile);

                nick.setText(userProfile.getNickname());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bitmap = getBitmap(userProfile.getThumbnailImagePath());
                        } catch (Exception e) {

                        } finally {
                            if(bitmap != null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        profile.setImageBitmap(bitmap);
                                    }
                                });
                            }
                        }
                    }
                }).start();

            }

            @Override
            public void onNotSignedUp() {   //가입되지 않은 경우
                //showSignup();
            }
        });
    }

    private Bitmap getBitmap(String url) {
        URL imgUrl = null;
        HttpURLConnection connection = null;
        InputStream is = null;

        Bitmap retBitmap = null;

        try {
            imgUrl = new URL(url);
            connection = (HttpURLConnection)imgUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            is = connection.getInputStream();
            retBitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            return retBitmap;
        }
    }
}
