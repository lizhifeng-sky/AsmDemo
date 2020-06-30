package com.android.asm.exec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.asm.R;
import com.android.asm.annotation.Catch;

/**
 * @author lizhifeng
 */
public class ExecStaticActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_static);
    }

    @Catch(value = "异常来啦")
    public void onTestCatch() {
        Log.e("lzf_onTestCatch", "onTestCatch");
    }
}
