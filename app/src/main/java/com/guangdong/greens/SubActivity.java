package com.guangdong.greens;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guangdong.greens.Adapter.RecyclerViewAdapter;
import com.guangdong.greens.Other.Item_line;

import java.util.ArrayList;
import java.util.HashMap;

public class SubActivity extends Fragment {
    RecyclerViewAdapter ryAdapter, ryAdapter1;
    ArrayList<HashMap<String, Object>> lists = new ArrayList<>();
    RecyclerView ry, HardwareRy;
    LinearLayoutManager lm, HardwareRylm;
    View v;

    public SubActivity(){

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
        HardwareRylm = new LinearLayoutManager(HardwareRy.getContext());
//        HardwareRylm.setOrientation(LinearLayoutManager.HORIZONTAL);
        HardwareRy.setLayoutManager(HardwareRylm);
        HardwareRy.addItemDecoration(new Item_line());
        ryAdapter = new RecyclerViewAdapter(getActivity());
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

    }

}
