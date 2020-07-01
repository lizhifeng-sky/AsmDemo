package com.android.asm.exec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.asm.R;
import com.android.asm.annotation.Catch;
import com.android.asm.simulate.ExecFieldTest;

/**
 * @author lizhifeng
 */
public class ExecFieldActivity extends AppCompatActivity {
    private ExecFieldTest execFieldTest;
    private String text;

    @Catch(value = "异常来了")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_field);
        execFieldTest=new ExecFieldTest();
    }

    @Catch(value = "异常来了")
    @Override
    protected void onResume() {
        super.onResume();
    }
}
