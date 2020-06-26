package com.rival.hs.rival_android;

/**
 * Created by Minwoo on 2017. 3. 20..
 */

import android.content.Intent;
import android.util.Log;

import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class SessionCallback implements ISessionCallback {

    MainActivity mainActivity;

    public SessionCallback(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        Log.e("park", "SessionCallback: " );
    }

    @Override
    public void onSessionOpened() {
        Log.e("park", "SessionCallback: " );

        final Intent intent = new Intent(this.mainActivity, MenuActivity.class);
        mainActivity.startActivity(intent);
        mainActivity.finish();
    }
    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        if(exception != null) {
            Logger.e(exception);
        }
    }


}
