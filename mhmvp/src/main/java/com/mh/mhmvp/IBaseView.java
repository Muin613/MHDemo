package com.mh.mhmvp;

/**
 * Created by Administrator on 2017/8/26.
 */

public interface IBaseView {
    void onPrepare();

    void onSuccess(Object o);

    void onError();
}
