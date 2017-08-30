package com.mh.mhdemo.MHSkin.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.mh.mhdemo.MHSkin.MHSkinManager;
import com.mh.mhdemo.R;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MHSkinFrameLayout extends FrameLayout {

    //是否设置drawable资源
    private boolean mDrawableFlag = false;
    //是否设置color资源
    private boolean mColorFlag = false;


    public MHSkinFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.mh_skin);
        mDrawableFlag = ta.getBoolean(R.styleable.mh_skin_mh_drawable, false);
        mColorFlag = ta.getBoolean(R.styleable.mh_skin_mh_color, false);
    }

    @Override
    public void addView(View child) {
        if (mDrawableFlag && !mColorFlag&&MHSkinManager.newInstance().hasResource()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                child.setBackground(MHSkinManager.newInstance().getDrawable());
            }else{
                child.setBackgroundDrawable(MHSkinManager.newInstance().getDrawable());
            }

        } else if (mColorFlag && !mDrawableFlag&&MHSkinManager.newInstance().hasResource()) {
            child.setBackgroundColor(MHSkinManager.newInstance().getColorId());
        }
            super.addView(child);
    }
}
