package com.android.asm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.asm.exec.ExecFieldActivity;
import com.android.asm.exec.ExecStaticActivity;
import com.android.asm.exec.ExecThisActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.exec_this_method).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        ExecThisActivity.class));
            }
        });
        findViewById(R.id.exec_static_method).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        ExecStaticActivity.class));
            }
        });
        findViewById(R.id.exec_field_method).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        ExecFieldActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
