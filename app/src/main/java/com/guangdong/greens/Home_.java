package com.guangdong.greens;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.guangdong.greens.Frames.All_;

/**
 * Created by cat6572325 on 17-4-1.
 */

public class Home_ extends AppCompatActivity implements View.OnClickListener {
    ImageView home, history, talk, set;
    RelativeLayout home_r, history_r, talk_r, set_r;
    All_ subfragment;
    FrameLayout content;
    FragmentManager fManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        initView();
    }

    private void initView() {
        home_r = (RelativeLayout) findViewById(R.id.home_r);
        history_r = (RelativeLayout) findViewById(R.id.history_r);
        talk_r = (RelativeLayout) findViewById(R.id.talk_r);
        set_r = (RelativeLayout) findViewById(R.id.set_r);
        home = (ImageView) findViewById(R.id.home_select);
        history = (ImageView) findViewById(R.id.history_select);
        talk = (ImageView) findViewById(R.id.talk_select);
        set = (ImageView) findViewById(R.id.set_select);
        content = (FrameLayout) findViewById(R.id.frame);
        home_r.setOnClickListener(this);
        history_r.setOnClickListener(this);
        talk_r.setOnClickListener(this);
        set_r.setOnClickListener(this);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item1","http://image.png");
        jsonObject.addProperty("item2","http://image.png");
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);
        jsonObject.addProperty("item3","http://image.png");
        jsonObject.addProperty("item4","http://image.png");
        jsonArray.add(jsonObject);
        Log.e("jsa",jsonArray.get(0).toString());
        Log.e("jsa",jsonArray.get(1).toString());


    }

    private void resetSelect(FragmentTransaction transaction) {
        home.setSelected(false);
        history.setSelected(false);
        talk.setSelected(false);
        set.setSelected(false);
        if (subfragment != null)
            transaction.hide(subfragment);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.home_r:
                resetSelect(transaction);
                home.setSelected(true);
                if (subfragment == null) {
                    subfragment = new All_();
                    transaction.add(R.id.frame, subfragment);
                } else
                    transaction.show(subfragment);
                break;
            case R.id.history_r:
                resetSelect(transaction);
                history.setSelected(true);
                break;
            case R.id.talk_r:
                resetSelect(transaction);
                talk.setSelected(true);
                break;
            case R.id.set_r:
                resetSelect(transaction);
                set.setSelected(true);
                break;
        }
        transaction.commit();
    }
}
