package com.android.asm.exec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.asm.R;

/**
 * @author lizhifeng
 */
public class ExecThisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"我进来就finish",Toast.LENGTH_LONG).show();
    }
}
