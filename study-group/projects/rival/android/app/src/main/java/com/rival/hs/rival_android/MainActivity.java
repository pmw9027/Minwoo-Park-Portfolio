package com.rival.hs.rival_android;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    SessionCallback callback; //kakao
    private LoginButton loginButton;
    Button button2;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext()); // SDK 초기화 (setContentView 보다 먼저 실행되어야합니다. 그렇지 않으면 Error.)
        setContentView(R.layout.activity_main);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        hideActionBar();

        callbackManager = CallbackManager.Factory.create();  //로그인 응답을 처리할 콜백 관리자

        //kakao
        //if (Session.getCurrentSession().isClosed()) {
            callback = new SessionCallback();
            Session.getCurrentSession().addCallback(callback);
            Session.getCurrentSession().checkAndImplicitOpen();
        //} else {
        //    redirectSignupActivity();
        //}


        loginButton = (LoginButton) findViewById(R.id.facebook_login); //페이스북 로그인 버튼
        loginButton.setReadPermissions("public_profile", "user_friends", "email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { //로그인 성공시 호출되는 메소드
                /*Log.e("토큰",loginResult.getAccessToken().getToken());
                Log.e("유저아이디",loginResult.getAccessToken().getUserId());
                Log.e("퍼미션 리스트",loginResult.getAccessToken().getPermissions()+"");*/

                //loginResult.getAccessToken() 정보를 가지고 유저 정보를 가져올수 있습니다.

                /*GraphRequest request =GraphRequest.newMeRequest(loginResult.getAccessToken() ,
                        new GraphRequest.GraphJSONObjectCallback() {

                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    Log.e("user profile",object.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                request.executeAsync();*/
                Log.e("UserProfile", loginResult.toString());
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(FacebookException error) {
            }

            @Override
            public void onCancel() {
            }
        });

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        //get hash key


    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }

    //kakao
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //간편로그인시 호출 ,없으면 간편로그인시 로그인 성공화면으로 넘어가지 않음
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {

            return;
        }

        //callbackManager.onActivityResult(requestCode, resultCode, data); // facebook
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {


            HttpComunication test = new HttpComunication();

            test.send();

            redirectSignupActivity();


            Log.e("park", "onSessionOpened: success");
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("park", "onSessionOpened: fail");

            if (exception != null) {
                Log.e("park", "onSessionOpened: " + exception);

            }
        }
    }

    protected void redirectSignupActivity() {
        //final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        final Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}