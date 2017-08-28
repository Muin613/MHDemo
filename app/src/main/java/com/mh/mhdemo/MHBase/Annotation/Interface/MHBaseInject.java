package com.mh.mhdemo.MHBase.Annotation.Interface;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/8/28.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MHBaseInject {
    int bodyId() default 0;
    int bottomId() default 0;
    int headerId() default 0;
}
