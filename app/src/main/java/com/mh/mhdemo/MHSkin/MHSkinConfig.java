package com.mh.mhdemo.MHSkin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MHSkinConfig {
    private boolean hasResource = false;

    private String childPath;//文件名字
    private File themeApkFile;//真实文件位置

    private String path = new StringBuilder(Environment.getExternalStorageDirectory().getAbsolutePath())
//            .append(File.separator)
//            .append("mh_theme")
            .toString();
    private static MHSkinConfig config;

    private MHSkinConfig() {

    }

    public synchronized static MHSkinConfig newInstance() {
        if (config == null)
            config = new MHSkinConfig();
        return config;
    }

    public String getRealFilePath() {
        return path + File.separator + childPath;
    }

    public MHSkinConfig bindContext(Context context) {
        SharedPreferences skinSP = context.getSharedPreferences("mh", Context.MODE_PRIVATE);
        childPath = skinSP.getString("theme", "");
        childPath = "mhskin.apk";
        check();
        return this;
    }


    public MHSkinConfig commit(Context context, String apkName) {
        SharedPreferences skinSP = context.getSharedPreferences("mh", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = skinSP.edit();
        editor.putString("theme", apkName);
        editor.commit();
        return this;
    }


    public void check() {
        if (TextUtils.isEmpty(childPath)) {//无文件名字
            hasResource = false;
            return;
        }
        themeApkFile = new File(path, childPath);
        if (!themeApkFile.exists()) {//无文件
            hasResource = false;
            return;
        }
        hasResource = true;
    }

    public void setHasResource(boolean hasResource) {
        this.hasResource = hasResource;
    }

    public boolean hasResource() {
        return hasResource;
    }
}
