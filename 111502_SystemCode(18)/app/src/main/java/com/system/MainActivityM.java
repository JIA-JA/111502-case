package com.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.system.dao.UserDao;

public class MainActivityM extends AppCompatActivity {

    public static final String TAG = MainActivityM.class.getSimpleName()+"My";
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1082870549671-hnq6kob2g02368r1vf1oal25hf0vpmu7.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton btSighIn = findViewById(R.id.button_SignIn);
        btSighIn.setOnClickListener(v->{
            startActivityForResult(mGoogleSignInClient.getSignInIntent(),200);
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String result = "登入成功\nEmail："+account.getEmail()+"\nGoogle名稱："
                        +account.getDisplayName();
                Log.d(TAG, "Token: "+account.getIdToken());
                TextView tvResult = findViewById(R.id.textView_Result);
                tvResult.setText(result);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    public void MNonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivityM.this, MainActivityN.class);
        startActivity(intent);
    }
    public void backonClick(View v) {
        MainActivityM.this.finish();
    }
//    public void reg(View view){
//
//        startActivity(new Intent(getApplicationContext(),MainActivityN.class));
//
//    }
//
//
//    public void login(View view){
//
//        EditText EditTextaccount = (EditText)findViewById(R.id.editTextAccountM);
//        EditText EditTextpassword = (EditText)findViewById(R.id.editTextPasswordM);
//
//        new Thread(){
//            @Override
//            public void run() {
//
//                UserDao userDao = new UserDao();
//
//                boolean aa = userDao.login(EditTextaccount.getText().toString(),EditTextpassword.getText().toString());
//                int msg = 0;
//                if(aa){
//                    msg = 1;
//                }
//
//                hand1.sendEmptyMessage(msg);
//
//
//            }
//        }.start();
//
//
//    }
//    final Handler hand1 = new Handler()
//    {
//        @Override
//        public void handleMessage(Message msg) {
//
//            if(msg.what == 1)
//            {
//                Toast.makeText(getApplicationContext(),"登入成功",Toast.LENGTH_LONG).show();
//
//            }
//            else
//            {
//                Toast.makeText(getApplicationContext(),"登入失敗",Toast.LENGTH_LONG).show();
//            }
//        }
//    };
}