package com.mh.mhdemo.MHSkin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/8/25.
 */

public interface API {
    @Streaming
    @GET
    Call<ResponseBody> downloadFiel(@Header("Range") String range, @Url String fileUrl);
}
