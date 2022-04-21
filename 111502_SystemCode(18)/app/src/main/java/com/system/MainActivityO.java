package com.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivityO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_o);
    }
    public void OAonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivityO.this, MainActivity.class);
        startActivity(intent);
    }
    public void OBonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivityO.this, MainActivityB.class);
        startActivity(intent);
    }
    public void OHonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivityO.this, MainActivityH.class);
        startActivity(intent);
    }
    public void OJonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivityO.this, MainActivityJ.class);
        startActivity(intent);
    }
    public void OMonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivityO.this, MainActivityM.class);
        startActivity(intent);
    }

}