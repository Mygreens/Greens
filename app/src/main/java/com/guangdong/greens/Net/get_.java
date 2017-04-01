package com.guangdong.greens.Net;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class get_ extends Thread {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            //设置超时，不设置可能会报异常
            .connectTimeout(3000, TimeUnit.MINUTES)
            .readTimeout(3000, TimeUnit.MINUTES)
            .writeTimeout(3000, TimeUnit.MINUTES)
            .build();
    Handler handler;
    LruCache<String, Bitmap> lruCache;//
    Message msg = null;
    Bundle bundle = null;
    Context context;
    ImageView headimg;
    String imgurl;

    public get_() {//TODO 构造

    }

    public void initlrucache() {//TODO 初始化一个缓冲区
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;//指定使用可用内存4分之1
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存时调用，返回一个bitmap正确大小
                return value.getByteCount();
            }
        };//初始化缓存区
    }


    private void addimg() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = null;
                try {
                    URL url = new URL(imgurl);

                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = new BufferedInputStream(conn.getInputStream());
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    conn.disconnect();

                } catch (IOException e) {
                    Log.e("联网或图片加载失败", e.toString());
                }
                if (bitmap != null) {
                    Message msg = Message.obtain();
                    msg.obj = bitmap;
                    msg.what = 3;

                }
            }
        }.start();
    }

    public void GetGridViewImg(final HashMap<String, Object> map) {
        headimg = (ImageView) map.get("image");
        Bitmap bitmap = (Bitmap) lruCache.get(map.get("path").toString());
        if (bitmap != null) {
            headimg.setImageBitmap(bitmap);
        } else {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Bitmap bitmap1 = null;
                    if (bitmap1 != null) {
                        try {
                            FileInputStream fis = new FileInputStream(map.get("path").toString());
                            bitmap1 = BitmapFactory.decodeStream(fis);

                            Message msg = new Message();
                            msg.obj = bitmap1;
                            msg.what = 0;
                            handler.sendMessage(msg);

                        } catch (FileNotFoundException e) {
                            Log.e("获取gridview图片时", e.toString());
                        }
                    }
                }
            }.start();
        }
    }

    public void gethttp(String url, final HashMap<String, Object> map) {

    }
}