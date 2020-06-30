package com.android.asm.add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.asm.R;
import com.android.asm.annotation.Catch;

public class AddClassActivity extends AppCompatActivity {

    @Catch(value = "异常来啦")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
    }
}
