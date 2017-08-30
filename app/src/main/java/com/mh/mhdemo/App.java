package com.mh.mhdemo;

import android.app.Application;
import android.util.Log;

import com.mh.mhdemo.MHSkin.MHSkinManager;

/**
 * Created by Administrator on 2017/8/30.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        MHSkinManager.newInstance().bindContext(this);
        if(MHSkinManager.newInstance().hasResource())
            try {
                MHSkinManager.newInstance().loadAPK();
                Log.d("1111","111");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
