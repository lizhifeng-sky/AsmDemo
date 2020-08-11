package com.android.asm.bean.manager;

import android.content.Context;
import android.widget.Toast;

/**
 * @author lizhifeng
 * @date 2020/8/11 15:22
 */
public class CheckNullException extends NullPointerException{
    private Context context;

    public CheckNullException(String s, Context context) {
        super(s);
        this.context = context;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        Toast.makeText(context,getMessage(),Toast.LENGTH_SHORT).show();
    }
}
