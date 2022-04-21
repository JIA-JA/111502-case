package com.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

import com.system.dao.UserDao;

public class MainActivityM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);
    }
    public void MNonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivityM.this, MainActivityN.class);
        startActivity(intent);
    }

    public void reg(View view){

        startActivity(new Intent(getApplicationContext(),MainActivityN.class));

    }


    public void login(View view){

        EditText EditTextaccount = (EditText)findViewById(R.id.editTextAccountM);
        EditText EditTextpassword = (EditText)findViewById(R.id.editTextPasswordM);

        new Thread(){
            @Override
            public void run() {

                UserDao userDao = new UserDao();

                boolean aa = userDao.login(EditTextaccount.getText().toString(),EditTextpassword.getText().toString());
                int msg = 0;
                if(aa){
                    msg = 1;
                }

                hand1.sendEmptyMessage(msg);


            }
        }.start();


    }
    final Handler hand1 = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"登入成功",Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(),"登入失敗",Toast.LENGTH_LONG).show();
            }
        }
    };
}