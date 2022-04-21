package com.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.system.dao.UserDao;
import com.system.entity.User;

public class MainActivityN extends AppCompatActivity {

    EditText username = null;
    EditText account = null;
    EditText password1 = null;
    EditText password2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_n);

        username = findViewById(R.id.editTextUserNameN);
        account = findViewById(R.id.editTextAccountN);
        password1 = findViewById(R.id.editTextPasswordN1);
        password2 = findViewById(R.id.editTextPasswordN2);

    }
        public void register(View view){

            String cusername = username.getText().toString();
            String caccount = account.getText().toString();
            String cpassword1 = password1.getText().toString();
            String cpassword2 = password2.getText().toString();

//            if(caccount.length() < 2 || cusername.length() < 2 || cpassword1.length() < 2 || cpassword2.length() < 2 ){
//                Toast.makeText(getApplicationContext(),"輸入資訊不符合要求請重新輸入",Toast.LENGTH_LONG).show();
//                return;
//            }

            if(cpassword1 != cpassword2){
                Toast.makeText(getApplicationContext(),"輸入資訊不符合要求請重新輸入",Toast.LENGTH_LONG).show();
                return;
            }

            User user = new User();

            user.setUsername(cusername);
            user.setPassword(cpassword1);

            new Thread(){
                @Override
                public void run() {

                    int msg = 0;

                    UserDao userDao = new UserDao();

                    User uu = userDao.findUser(user.getUsername());

                    if(uu != null){
                        msg = 1;
                    }

                    boolean flag = userDao.register(user);
                    if(flag){
                        msg = 2;
                    }
                    hand.sendEmptyMessage(msg);

                }
            }.start();


        }
        final Handler hand = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 0)
                {
                    Toast.makeText(getApplicationContext(),"註冊失敗",Toast.LENGTH_LONG).show();

                }
                if(msg.what == 1)
                {
                    Toast.makeText(getApplicationContext(),"該賬號已經存在，請換一個賬號",Toast.LENGTH_LONG).show();

                }
                if(msg.what == 2)
                {
                    //startActivity(new Intent(getApplication(),MainActivity.class));

                    Intent intent = new Intent();
                    //將想要傳遞的資料用putExtra封裝在intent中
                    intent.putExtra("a","註冊");
                    setResult(RESULT_CANCELED,intent);
                    finish();
                }

            }
        };
    }
}