package com.guangdong.greens;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.guangdong.greens.Adapter.RecyclerViewAdapter;
import com.guangdong.greens.Other.Item_line;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author       : yanbo
 * Date         : 2015-06-02
 * Time         : 10:15
 * Description  :
 */
public class SubActivity extends AppCompatActivity {
    RecyclerViewAdapter ryAdapter,ryAdapter1;
    ArrayList<HashMap<String, Object>> lists = new ArrayList<>();
    RecyclerView ry,HardwareRy;
    LinearLayoutManager lm,HardwareRylm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_input_delete);
        actionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("详情界面");
        initView();
    }

    private void initView() {
        HardwareRy = (RecyclerView) findViewById(R.id.home_HardwareRy_ry);
        HardwareRylm = new LinearLayoutManager(HardwareRy.getContext());
//        HardwareRylm.setOrientation(LinearLayoutManager.HORIZONTAL);
        HardwareRy.setLayoutManager(HardwareRylm);
        HardwareRy.addItemDecoration(new Item_line());
        ryAdapter1 = new RecyclerViewAdapter(this);
        HardwareRy.setAdapter(ryAdapter1);
    }
}
