package com.android.asm.bean.manager;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author lizhifeng
 * @date 2020/8/11 09:52
 */
public class CheckNullBeanManager implements Thread.UncaughtExceptionHandler {
    private static volatile CheckNullBeanManager instance;
    private static volatile Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private static final String TAG = "CheckNullBeanManager";

    private CheckNullBeanManager() {
    }

    public static CheckNullBeanManager getInstance() {
        if (instance == null) {
            synchronized (CheckNullBeanManager.class) {
                if (instance == null) {
                    instance=new CheckNullBeanManager();
                }
            }
        }
        return instance;
    }

    public void setDefaultUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public NullPointerException throwNull(String className,
                                          String fieldName,
                                          String errorMessage) {
        String message = "null发生在" + className + "   " + fieldName + "   错误信息：" + errorMessage;
//        checkExceptionHandler();
        return new NullPointerException(message);
    }

    private void checkExceptionHandler() {
//        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (CheckNullBeanManager.defaultUncaughtExceptionHandler == null) {
            Thread.setDefaultUncaughtExceptionHandler(this);
        } else {
            Log.e(TAG, "checkExceptionHandler has handler");
        }
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        if (e.getMessage() != null) {
            Log.e(TAG, e.getMessage());
        }
    }
}
