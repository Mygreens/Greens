package com.guangdong.greens.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guangdong.greens.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cat6572325 on 17-4-1.
 */

public class Graphic_steps_ry_Adapter extends RecyclerView.Adapter<Graphic_steps_ry_Adapter.Graphic_steps_Holder> {

    Context context;
    ArrayList<HashMap<String, Object>> lists;
    int[] layouts = {R.layout.a_detail_item, R.layout.detail_comment_item, R.layout.comment_classsify_item};

    public Graphic_steps_ry_Adapter(Context context, ArrayList<HashMap<String, Object>> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public Graphic_steps_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(layouts[viewType], parent, false);
        return new Graphic_steps_Holder(view);
    }

    @Override
    public void onBindViewHolder(Graphic_steps_Holder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        holder.itemView.setLayoutParams(params);//把params设置给item布局
//        将图片的url写进ImageView的target里面
        switch ((int) lists.get(position).get("layout")) {
            case 0:
                holder.img.setTag(lists.get(position).get("image").toString());
                holder.txt.setText(lists.get(position).get("text").toString());
                break;
            case 1:

                break;
            case 2:

                break;
        }

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (int) lists.get(position).get("layout");
    }

    public static class Graphic_steps_Holder extends RecyclerView.ViewHolder {
        public final View itemView;
        ImageView img;
        TextView txt;

        public Graphic_steps_Holder(View view) {
            super(view);
            itemView = view;
            img = (ImageView) itemView.findViewById(R.id.Graphic_steps_img);
            txt = (TextView) itemView.findViewById(R.id.Graphic_steps_txt);
        }
    }
}
