package com.android.asm.exec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.asm.R;
import com.android.asm.annotation.Catch;
import com.android.asm.annotation.Timer;
import com.android.asm.time.TimeUtils;

/**
 * @author lizhifeng
 */
public class ExecStaticActivity extends AppCompatActivity {


    @Timer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_static);
        onTestCatch();
    }

    @Catch(value = "异常来啦")
    public void onTestCatch() {
        test();
    }

    @Timer
    public void test(){
        try {
            Log.e("lzf_onTestCatch", "onTestCatch");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
