package com.guangdong.greens.Net;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.json.*;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.RequestBody;
import okhttp3.Response;


public class post_ {
    Handler handler;

    public post_(Handler handler) {
        this.handler = handler;
    }

    //    public void post_str() {
//        OkGo.get("http://192.168.8.135:8081/list_user")     // 请求方式和请求url
//                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
//                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
//                .execute(new StringCallback() {
//                    @Override
//                    public void onAfter(String s, Exception e) {
//                        super.onAfter(s, e);
//                        if (e == null) {
//                            //e即是连接错误时才有值的，所以说如果为null则成功连接
//
//                            Log.e("连接成功", s);
//                        } else
//                            Log.e("连接失败", e.toString());
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Log.e("错误",e.toString());
//                    }
//
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("成功",s);
//                    }
//                });
//    }
    public void post_str(final HashMap<String, Object> map) {
        OkGo.post(map.get("url").toString() )
                .tag(this)
//                .upString(map.get("email").toString())
//                .params("email", map.get("email").toString())
//                .params("password", map.get("password").toString())
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.e("postError", e.toString());
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        Log.e("after", s);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("success", s);
                        Message msg = new Message();
                        msg.what = (int) map.get("what");
                        msg.obj = s;
                        handler.sendMessage(msg);
                    }
                });

    }
}