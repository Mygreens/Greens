package com.guangdong.greens.Net;

import android.os.AsyncTask;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by cat6572325 on 17-4-4.
 */

public class TheAsynkTask_ extends AsyncTask<String, Void, Void> {
    HashMap<String, Object> map;

    public TheAsynkTask_(HashMap<String, Object> map) {
        this.map = map;
    }

    @Override
    protected Void doInBackground(String... params) {
        if (map.get("?").toString() == "post") {
            post_ post = new post_((Handler) map.get("handle"));
            post.post_str(map);
        }
        return null;
    }
}
