package com.android.asm.bean.manager;

import android.content.Context;

/**
 * @author lizhifeng
 * @date 2020/8/11 09:52
 */
public class CheckNullBeanManager {
    private static volatile CheckNullBeanManager instance;
    private static final String TAG = "CheckNullBeanManager";
    private Context context;

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

    public void setContext(Context context){
        this.context=context;
    }

    public CheckNullException throwNull(String className,
                                          String fieldName,
                                          String errorMessage) {
        String message = "null发生在" + className + "   " + fieldName + "   错误信息：" + errorMessage;
        return new CheckNullException(message,context);
    }

}
