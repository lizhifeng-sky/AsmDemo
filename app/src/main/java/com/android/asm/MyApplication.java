package com.android.asm;

import android.app.Application;

import com.android.asm.bean.manager.CheckNullBeanManager;

/**
 * @author lizhifeng
 * @date 2020/8/11 11:42
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CheckNullBeanManager
                .getInstance()
                .setContext(this);
    }
}
