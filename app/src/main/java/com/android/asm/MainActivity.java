package com.android.asm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.asm.bean.UserBean;
import com.android.asm.bean.manager.CheckNullBeanManager;
import com.android.asm.exec.ExecFieldActivity;
import com.android.asm.exec.ExecStaticActivity;
import com.android.asm.exec.ExecThisActivity;

public class MainActivity extends AppCompatActivity {
    UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewById1 = findViewById(R.id.exec_this_method);
        viewById1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(MainActivity.this,
//                        ExecThisActivity.class));
                userBean=new UserBean();
                userBean.setUserName("userName");
            }
        });
        final TextView viewById = findViewById(R.id.exec_static_method);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    throw  CheckNullBeanManager.getInstance().throwNull("className",
//                            "fieldName",
//                            "errorMessage");
                    viewById.setText(userBean.getUserAge());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                startActivity(new Intent(MainActivity.this,
//                        ExecStaticActivity.class));

            }
        });
        final TextView viewById2 = findViewById(R.id.exec_field_method);
        viewById2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewById2.setText(userBean.getUserSex());
//                startActivity(new Intent(MainActivity.this,
//                        ExecFieldActivity.class));
            }
        });
    }
}
