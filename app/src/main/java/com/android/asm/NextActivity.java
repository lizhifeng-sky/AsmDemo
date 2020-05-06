package com.android.asm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

/**
 * @author lizhifeng
 */
public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
    }

    @Override
    protected void onResume() {
        Toast.makeText(this,"我进来就finish",Toast.LENGTH_LONG).show();
        super.onResume();
    }
}
