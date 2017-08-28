package com.mh.mhdemo.MHBase.Act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mh.mhdemo.R;

/**
 * Created by Administrator on 2017/8/28.
 */

public abstract class BaseAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mh_base_activity);
        main();
    }
    public abstract void main();
}
