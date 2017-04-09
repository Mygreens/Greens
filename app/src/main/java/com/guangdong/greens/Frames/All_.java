package com.guangdong.greens.Frames;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.guangdong.greens.Adapter.RecyclerViewAdapter;
import com.guangdong.greens.Api.api_;
import com.guangdong.greens.Detail_;
import com.guangdong.greens.Net.TheAsynkTask_;
import com.guangdong.greens.Other.Item_line;
import com.guangdong.greens.Publish_;
import com.guangdong.greens.R;

import java.util.ArrayList;
import java.util.HashMap;

public class All_ extends Fragment {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    RecyclerViewAdapter ryAdapter, ryAdapter1;
    ArrayList<HashMap<String, Object>> lists = new ArrayList<>();
    RecyclerView ry, HardwareRy;
    LinearLayoutManager lm, HardwareRylm;
    View v;
    RelativeLayout publish_rl;

    public All_() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_sub, container, false);
        return v;
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sub);
//        Toolbar toolbar = (Toolbar) this.findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_input_delete);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//
//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
////        collapsingToolbar.setTitle("详情界面");
//        initView();
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        HardwareRy = (RecyclerView) v.findViewById(R.id.home_HardwareRy_ry);
        publish_rl = (RelativeLayout) v.findViewById(R.id.publish);
        HardwareRylm = new LinearLayoutManager(HardwareRy.getContext());
//        HardwareRylm.setOrientation(LinearLayoutManager.HORIZONTAL);
        HardwareRy.setLayoutManager(HardwareRylm);
        HardwareRy.addItemDecoration(new Item_line());
        ryAdapter = new RecyclerViewAdapter(getActivity(), lists);
        HardwareRy.setAdapter(ryAdapter);
        ryAdapter.setOnClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Log.e("item", position + "");
                startActivity(new Intent(getActivity(), Detail_.class));
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });
        publish_click();
        loadAll();

    }

    //发布按钮点击事件
    private void publish_click() {
        publish_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Publish_.class));
            }
        });
    }

    private void setScroll() {
        HardwareRy.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager lm = (LinearLayoutManager) HardwareRy.getLayoutManager();


            }
        });
    }

    private void loadAll() {
//        api_ api = new api_();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("?", "post");
//        map.put("handle", mHandler);
//        map.put("what", 0);
//        map.put("url", api.getAll);
//        TheAsynkTask_ asynkTask_ = new TheAsynkTask_(map);
//        asynkTask_.execute("");
    }

    private void jsonitem(JsonArray jsonArray) {
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JsonObject) jsonArray.get(i);
            jsonObject.get("imageUrl");
        }
    }

}
