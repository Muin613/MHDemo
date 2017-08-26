package com.mh.mhmvp;

/**
 * Created by Administrator on 2017/8/26.
 */

public interface IBaseRequestCallback<T> {

    void onBefore();

    void onSuccess(String tag, T data);

    void onError(boolean isException, Object error);
}
