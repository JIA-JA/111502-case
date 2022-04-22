package com.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textViewUserNameA1 = (TextView) findViewById(R.id.textViewUserNameA1);
    TextView textViewTagA1 = (TextView) findViewById(R.id.textViewTagA1);
    TextView textViewTextA1 = (TextView) findViewById(R.id.textViewTextA1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(mutiThread);
        thread.start();
    }

    public void ABonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivityB.class);
        startActivity(intent);
    }
    public void AHonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivityH.class);
        startActivity(intent);
    }
    public void AJonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivityJ.class);
        startActivity(intent);
    }
    public void AOonClick(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivityO.class);
        startActivity(intent);
    }
    private Runnable mutiThread = new Runnable() {
        @Override
        public void run() {
            String result1 = null;
            String result2 = null;
            String result3 = null;
            try {
                URL url = new URL("http://10.0.2.2/GetData.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        JSONArray dataJson = new JSONArray(line);
                        int i = dataJson.length() - 1;
                        JSONObject info = dataJson.getJSONObject(i);
                        String time = info.getString("time");
                        String dB = info.getString("dB");

                        result1 = time.toString();
                        result2 = dB.toString();

                    }
                    inputStream.close();
                }
            } catch (Exception e) {
                result1 = e.toString();
                result2 = e.toString();
                result3 = e.toString();
            }

            String finalResult1 = result1;
            String finalResult2 = result2;
            String finalResult3 = result3;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewUserNameA1.setText(finalResult1);
                    textViewTagA1.setText(finalResult2);
                    textViewTextA1.setText(finalResult3);

                }
            });
        }
    };
}