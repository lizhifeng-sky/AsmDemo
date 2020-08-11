package com.android.asm;

import android.app.Application;
import android.content.Context;

import com.android.asm.bean.manager.CheckNullBeanManager;

/**
 * @author lizhifeng
 * @date 2020/8/11 11:42
 */
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        CheckNullBeanManager
                .getInstance()
                .setContext(this);
    }
}
