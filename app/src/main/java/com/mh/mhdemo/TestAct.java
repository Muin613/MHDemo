package com.mh.mhdemo;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AnimationSet;

import com.mh.mhdemo.MHBase.Act.BaseAct;
import com.mh.mhdemo.MHBase.Annotation.Interface.MHBaseInject;
import com.mh.mhdemo.MHBase.Annotation.Interface.MHViewInject;
import com.mh.mhdemo.MHBase.Annotation.Utils.MHInject;

/**
 * Created by Administrator on 2017/8/28.
 */
@MHBaseInject(bodyId = R.layout.body, headerId = R.layout.header)
public class TestAct extends BaseAct {


    @MHViewInject(R.id.body)
    View body;
    @MHViewInject(R.id.mh_base_act_body)
    View v;
    @MHViewInject(R.id.mh_base_act_bottom)
    View v1;
    @MHViewInject(R.id.mh_base_act_header)
    View v2;
    private MHInject inject;

    @Override
    public void main() {
        System.out.println("laile?"+System.currentTimeMillis());
        inject = new MHInject()
                .setHeaderId(R.id.mh_base_act_header)
                .setBodyId(R.id.mh_base_act_body)
                .setBottomId(R.id.mh_base_act_bottom);
               inject .inject(this);
        inject.bindView();
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("dian ji e ");
                body.setBackgroundResource(R.color.white);

            }
        });
        System.out.println("laile?"+System.currentTimeMillis());

    }
}
