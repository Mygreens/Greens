package com.guangdong.greens.Frames;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guangdong.greens.Other.Sqlit_;
import com.guangdong.greens.R;
import com.guangdong.greens.home_;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cat6572325 on 17-4-2.
 */

public class History_ extends Fragment {
    RecyclerView ry;
    ArrayList<HashMap<String, Object>> lists = new ArrayList<>();
    View v;
    Sqlit_ sqlit_;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.a_history_layout, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        sqlit_ = new Sqlit_(getContext(), "", null, 1);
        CheckHistory();
    }

    private void CheckHistory() {
        HashMap<String, Object> map;
        map = sqlit_.cursorData();
        if (map != null) {
            //TODO　从数据库获取数据后渲染列表
        }
    }

    /****** *****/
    //工具
    static class History_Adapter extends RecyclerView.Adapter<History_Adapter.HistoryViewHoder> {
        Context context;
        ArrayList<HashMap<String, Object>> lists = new ArrayList<>();
        int[] layouts = {R.layout.list_item};

        public History_Adapter(Context context, ArrayList<HashMap<String, Object>> lists) {
            this.lists = lists;
            this.context = context;
        }

        @Override
        public History_Adapter.HistoryViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
            HistoryViewHoder holder = new HistoryViewHoder(LayoutInflater.from(
                    context).inflate(layouts[viewType], parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(HistoryViewHoder holder, int position) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
            holder.itemView.setLayoutParams(params);//把params设置给item布局
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        public class HistoryViewHoder extends RecyclerView.ViewHolder {
            View itemView;

            public HistoryViewHoder(View itemView) {
                super(itemView);
                this.itemView = itemView;
            }
        }
    }

}
