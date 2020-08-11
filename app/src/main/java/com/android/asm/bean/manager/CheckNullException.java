package com.android.asm.bean.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.asm.MyApplication;

/**
 * @author lizhifeng
 * @date 2020/8/11 15:22
 */
public class CheckNullException extends NullPointerException{
    private String errorMessage;
    public CheckNullException(String message,String errorMessage) {
        super(message);
        this.errorMessage=errorMessage;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        if (getMessage()!=null) {
            Log.e("tag",getMessage());
        }
        if (MyApplication.context !=null) {
            Toast.makeText(MyApplication.context,errorMessage,Toast.LENGTH_SHORT).show();
        }
    }
}
