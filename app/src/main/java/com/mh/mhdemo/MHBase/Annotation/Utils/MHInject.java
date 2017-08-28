package com.mh.mhdemo.MHBase.Annotation.Utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mh.mhdemo.MHBase.Annotation.Error.MHNullException;
import com.mh.mhdemo.MHBase.Annotation.Interface.MHBaseInject;
import com.mh.mhdemo.MHBase.Annotation.Interface.MHViewInject;
import com.mh.mhdemo.TestAct;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/8/28.
 */

public class MHInject {
    private int headerId;//头view id
    private int bodyId;//身体view id
    private int bottomId;//底部view Id
    private View decorView;//顶层view
    private Activity activity;

    public int getHeaderId() {
        return headerId;
    }

    public MHInject setHeaderId(int headerId) {
        this.headerId = headerId;
        return this;
    }

    public int getBodyId() {
        return bodyId;
    }

    public MHInject setBodyId(int bodyId) {
        this.bodyId = bodyId;
        return this;
    }

    public int getBottomId() {
        return bottomId;
    }

    public MHInject setBottomId(int bottomId) {
        this.bottomId = bottomId;
        return this;
    }

    public View getDecorView() {
        return decorView;
    }

    public Activity getActivity() {
        return activity;
    }


    public MHInject inject(Activity activity) {
        this.activity = activity;
        this.decorView = this.activity.getWindow().getDecorView();
        Class<?> cls = this.activity.getClass();
        LayoutInflater inflater = LayoutInflater.from(this.activity);
        bind(inflater, cls);
        return this;
    }


    private void checkException(){
        if(decorView==null)
            throw new MHNullException("DecorView is null,u should initialize the decor view!!!");
    }


    private void bind(LayoutInflater inflater, Class cls) {
        MHBaseInject inject = (MHBaseInject) cls.getAnnotation(MHBaseInject.class);
        System.out.println(inject);
        if (inject != null) {
            if (headerId != 0 && inject.headerId() != 0) {
                System.out.println("shezhi header"+ headerId);
                bindView(inflater, new int[]{headerId, inject.headerId()});
            }
            if (bodyId != 0 && inject.bodyId() != 0) {
                System.out.println("shezhi body"+bodyId);
                bindBody(inflater, new int[]{bodyId, inject.bodyId()});
            }
            if (bottomId != 0 && inject.bottomId() != 0) {
                bindView(inflater, new int[]{bottomId, inject.bottomId()});
            }

        }
    }


   private void bindView(LayoutInflater inflater, int... ids) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
//        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout bottom = (RelativeLayout) decorView.findViewById(ids[0]);
        View b = inflater.inflate(ids[1], null);
        bottom.addView(b, lp);
    }

    private void bindBody(LayoutInflater inflater, int... ids) {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        FrameLayout bottom = (FrameLayout) decorView.findViewById(ids[0]);
        View b = inflater.inflate(ids[1], null);
        bottom.addView(b, lp);
    }
    public  void bindView() {
//        checkException();
        Class cls=activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            MHViewInject inject = f.getAnnotation(MHViewInject.class);
            if (inject != null) {
                f.setAccessible(true);
                    try {
                            f.set(activity, decorView.findViewById(inject.value()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
            }
        }

    }
}
