package com.guangdong.greens;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.guangdong.greens.Api.api_;
import com.guangdong.greens.Net.post_;

import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * Created by cat6572325 on 17-4-3.
 */

public class Login_ extends AppCompatActivity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(Login_.this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login_.this, Home_.class));
                    break;
            }

        }
    };
    api_ api = new api_();
    EditText email_e, password_e;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
    }

    public void login_click(View v) {
       // MyAsynctask asynctask = new MyAsynctask();
       // asynctask.execute(email_e.getText().toString(), password_e.getText().toString());
        startActivity(new Intent(Login_.this,Home_.class));
    }

    private void initView() {
        email_e = (EditText) findViewById(R.id.email_e);
        password_e = (EditText) findViewById(R.id.password_e);
    }

    private class MyAsynctask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            post_ post = new post_(mHandler);
            HashMap<String, Object> map = new HashMap<>();
            map.put("email", params[0]);
            map.put("password", params[1]);
            map.put("url", "http://192.168.8.135:3000/login");
            post.post_str(map);
            return null;
        }
    }
}
