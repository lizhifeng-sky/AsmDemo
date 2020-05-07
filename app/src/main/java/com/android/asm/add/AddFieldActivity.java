package com.android.asm.add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.asm.R;
import com.android.asm.simulate.ExecFieldTest;

public class AddFieldActivity extends AppCompatActivity {
    private String text;
    private ExecFieldTest execFieldTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_field);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
