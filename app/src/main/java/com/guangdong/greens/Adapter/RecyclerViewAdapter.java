package com.guangdong.greens.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guangdong.greens.R;
import com.guangdong.greens.SubActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private int[] colors = {R.color.color_0, R.color.color_1, R.color.color_2, R.color.color_3,
            R.color.color_4, R.color.color_5, R.color.color_6, R.color.color_7,
            R.color.color_8, R.color.color_9,};
    private String[] items = {
            "硬件", "女人", "家居", "遛宠", "厨房", "清洁"
    };
    private Context mContext;

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        holder.itemView.setLayoutParams(params);//把params设置给item布局

        // holder.mTextView.setBackgroundColor(mContext.getResources().getColor(colors[position % (colors.length)]));
        //holder.mTextView.setText(position + "");

//        holder.mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContext.startActivity(new Intent(mContext, SubActivity.class));
//            }
//        });

        holder.itemText.setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View itemView;
        TextView itemText;

        public ViewHolder(View view) {
            super(view);
            itemView = view;
            itemText = (TextView) itemView.findViewById(R.id.window_item_t);
        }
    }
}
