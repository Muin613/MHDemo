package com.mh.mhdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/7/30.
 */

public  class MHProxy<T> implements InvocationHandler {
    T subject;
    private String methodName;

    public MHProxy(T subject) {
        this.subject = subject;
    }

    public void setNull() {
        subject = null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (subject == null)
            return null;
        if (method.getAnnotation(MHProxyTag.class)!=null) {
            System.out.println("ni shi you zhu jie de ");
            return method.invoke(subject, args);
        }
        return method.invoke(subject, args);
    }

    public T newProxyInstance(Class<?>[] cs) {
        return (T) Proxy.newProxyInstance(subject.getClass().getClassLoader(), cs, this);
    }

}
