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

    }
    public void testonClick(View v){
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
    String result1 = null;
    String result2 = null;
    String result3 = null;
    // 建立一個執行緒執行的事件取得網路資料
    // Android 有規定，連線網際網路的動作都不能再主線程做執行
    // 畢竟如果使用者連上網路結果等太久整個系統流程就卡死了
    private Runnable mutiThread = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL("http://127.0.0.1/GetData.php");
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                int responseCode = connection.getResponseCode();
                // 建立取得回應的物件
                if(responseCode == HttpURLConnection.HTTP_OK){ // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    InputStream inputStream = connection.getInputStream(); // 取得輸入串流
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // 讀取輸入串流的資料
                    String line = null; // 宣告讀取用的字串
                    while((line = bufReader.readLine()) != null) {
                        JSONArray dataJson = new JSONArray(line);
                        int i = dataJson.length()-1;
                        JSONObject info = dataJson.getJSONObject(i);
                        String Username = info.getString("user_name"); // 宣告存放用字串
                        String tag = info.getString("tag_");
                        String content = info.getString("content");
                        result1 = Username; // 把存放用字串放到全域變數
                        result2 = tag;
                        result3 = content;
                    }
                    inputStream.close(); // 關閉輸入串流

                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) {
                result1 = e.toString(); // 如果出事，回傳錯誤訊息
                result2 = e.toString();
                result3 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    textViewUserNameA1.setText(result1); // 更改顯示文字
                    textViewTagA1.setText(result2);
                    textViewTextA1.setText(result3);
                }
            });
        }
    };
}