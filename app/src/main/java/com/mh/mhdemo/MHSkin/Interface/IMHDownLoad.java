package com.mh.mhdemo.MHSkin.Interface;

/**
 * Created by Administrator on 2017/8/29.
 */

public interface IMHDownLoad {

    void onPre();

    void onLoading();

    void onError();

    void onSuccess();

    void cancel();
}
