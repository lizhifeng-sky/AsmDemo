package com.android.asm.exec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.asm.R;
import com.android.asm.simulate.ExecFieldTest;

/**
 * @author lizhifeng
 */
public class ExecFieldActivity extends AppCompatActivity {
    private ExecFieldTest execFieldTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_field);
        execFieldTest=new ExecFieldTest();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
