package com.mh.mhdemo.MHSkin.Bean;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/8/29.
 */

public class APKBean {
    private String name;
    private String size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRealFileName() {
        if (TextUtils.isEmpty(name))
            return name;
        StringBuilder builder = new StringBuilder(Environment.getExternalStorageDirectory().getAbsolutePath())
                .append(File.separator)
                .append("mh_theme")
                .append(File.separator)
                .append(name);
        return builder.toString();
    }
}
