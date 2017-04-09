package com.guangdong.greens.Net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Message;
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

import static com.guangdong.greens.Other.ResizeBitmap.resizeImage;


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

    public Bitmap checkLucache(HashMap<String,Object> map) {
        String path = map.get("path").toString();
        Bitmap bitmap = myLruCache.get(path),resizeBitmap=null;
        if (bitmap == null) {
            myAsyncTask async = new myAsyncTask(map);
            async.execute(path);
        }else
        {

            resizeBitmap = resizeImage(bitmap, 340, 340);
            Log.e("bitmap", String.valueOf(bitmap.getHeight()));
            Log.e("resizebitmap", String.valueOf(resizeBitmap.getHeight()));
        }
        Log.e("lrucache", "加载图片");
        return resizeBitmap;
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


    /***** *****/
    //加载GridView
    public void loadGridViewImg(String path) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("path",path);
        map.put("?", "grid");
        Bitmap bitmap = (Bitmap) myLruCache.get(path);
        if (bitmap == null) {
            myAsyncTask async = new myAsyncTask(map);
            async.execute(path);
            Log.e("gridImage",path);
        }
    }

    private Bitmap getGridView_picture(HashMap<String, Object> map) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(map.get("path").toString());
        } catch (FileNotFoundException e) {
            Log.e("获取grid",e.toString());
        }
        Log.e("getGridView",map.get("path").toString());
        return BitmapFactory.decodeStream(fis);
    }

    private class myAsyncTask extends AsyncTask<String, Void, Bitmap> {
        String path;
        HashMap<String, Object> map;
        public myAsyncTask(HashMap<String, Object> map) {
            this.map = map;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            if (map.get("?").toString() == "grid") {
                bitmap = getGridView_picture(map);
            } else
                bitmap = addimg(params[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.e("postExecute",bitmap == null ? "null" : "nonull");
            if (bitmap != null) {
                myLruCache.put(map.get("path").toString(), bitmap);
            }
        }
    }
}
