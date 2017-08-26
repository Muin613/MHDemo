package com.mh.mhmvp;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/8/26.
 *
 */

public interface IBasePresenter {

    void attachIBaseView(@NonNull IBaseView Interface);

    void initPresenter();

    void onResume();

    void onPause();

    void onDestroy();

}
