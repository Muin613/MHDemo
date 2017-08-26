package com.mh.mhmvp;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/8/26.
 * BaseView 用来控制view的层显示
 * Data 是真实反馈数据
 */

public class BasePresenter<BaseView extends IBaseView, Data> implements IBasePresenter, IBaseRequestCallback<Data> {
    BaseView view;


    /**
     * 请求最开开始的操作
     * 做准备工作
     */
    @Override
    public void onBefore() {
        if (null != view)
            view.onPrepare();
    }

    /**
     * 请求成功后的操作
     *
     * @param tag  标志
     * @param data 请求后的数据
     */
    @Override
    public void onSuccess(String tag, Data data) {

    }

    /**
     * 请求出错
     *
     * @param isException 标志（判断要不要抛异常）
     * @param error       错误数据，错误码，错误内容
     */
    @Override
    public void onError(boolean isException, Object error) {

    }

    /**
     * 绑定BaseView
     *
     * @param Interface 接口传递，从而可以控制View的显示
     */
    @Override
    public void attachIBaseView(@NonNull IBaseView Interface) {
        view = (BaseView) Interface;
    }

    /**
     * 初始化
     */
    @Override
    public void initPresenter() {
        this.onBefore();
    }

    /**
     * onResume
     * 还原操作（建议开线程去操作）
     */

    @Override
    public void onResume() {

    }

    /**
     * onPause
     * 保存操作
     */
    @Override
    public void onPause() {

    }

    /**
     * onDestroy
     * 做销毁操作
     */
    @Override
    public void onDestroy() {

    }
}
