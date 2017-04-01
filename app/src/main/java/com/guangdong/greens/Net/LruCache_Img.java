package com.guangdong.greens.Net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LruCache_Img {
    LruCache<String, Bitmap> myLruCache;

    public LruCache_Img() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;//指定使用可用内存4分之1
        myLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存时调用，返回一个bitmap正确大小
                return value.getByteCount();
            }
        };//初始化缓存区
    }

    public Bitmap checkLucache(String path) {
        Bitmap bitmap = myLruCache.get(path);
        if (bitmap == null) {
            myAsyncTask async = new myAsyncTask(path);
            async.execute(path);
        }
        Log.e("lrucache","加载图片");
        return bitmap;
    }

    private Bitmap addimg(String path) {
        final String myurl = path;
        Bitmap bitmap = null;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            conn.disconnect();

        } catch (IOException e) {
            Log.e("联网或图片加载失败", e.toString());
        }
        return bitmap;
    }

    private class myAsyncTask extends AsyncTask<String, Void, Bitmap> {
        String path;

        public myAsyncTask(String path) {
            this.path = path;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = addimg(params[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                myLruCache.put(path, bitmap);
            }
        }
    }
}
