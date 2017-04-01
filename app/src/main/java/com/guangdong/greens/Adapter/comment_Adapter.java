package com.guangdong.greens.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guangdong.greens.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cat6572325 on 17-4-1.
 */

public class comment_Adapter extends RecyclerView.Adapter<comment_Adapter.comment_Holder> {

    Context context;
    ArrayList<HashMap<String, Object>> lists;

    public comment_Adapter(Context context, ArrayList<HashMap<String, Object>> lists) {

    }

    @Override
    public comment_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new comment_Holder(view);
    }

    @Override
    public void onBindViewHolder(comment_Holder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        holder.itemView.setLayoutParams(params);//把params设置给item布局

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class comment_Holder extends RecyclerView.ViewHolder {
        public final View itemView;

        public comment_Holder(View view) {
            super(view);
            itemView = view;
        }
    }
}
