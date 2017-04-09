package com.guangdong.greens.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guangdong.greens.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private int[] colors = {R.color.color_0, R.color.color_1, R.color.color_2, R.color.color_3,
            R.color.color_4, R.color.color_5, R.color.color_6, R.color.color_7,
            R.color.color_8, R.color.color_9,};

    private Context mContext;
    ArrayList<HashMap<String,Object>> lists;

    public RecyclerViewAdapter(Context mContext, ArrayList<HashMap<String,Object>> lists) {
        this.mContext = mContext;
        this.lists=lists;
    }

    public OnItemClickListener mListener;
//    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);

        void onItemLongClickListener(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        holder.itemView.setLayoutParams(params);//把params设置给item布局
     //   holder.all_item_img.setTag(lists.get(position).get("image"));
        if (mListener != null) {//如果设置了监听那么它就不为空，然后回调相应的方法
            holder.linea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();//得到当前点击item的位置pos
                    mListener.onItemClickListener(holder.itemView, pos);//把事件交给我们实现的接口那里处理
                    Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();

                }
            });
            holder.linea.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();//得到当前点击item的位置pos
                    mListener.onItemLongClickListener(holder.itemView, pos);//把事件交给我们实现的接口那里处理
                    return true;
                }
            });
        }

     //   holder.itemText.setText(lists.get(position).get("text").toString());
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View itemView;
        TextView itemText;
        LinearLayout linea;
        ImageView all_item_img;

        public ViewHolder(View view) {
            super(view);
            itemView = view;
            itemText = (TextView) itemView.findViewById(R.id.window_item_t);
            linea = (LinearLayout) itemView.findViewById(R.id.item_linear);
            all_item_img= (ImageView) itemView.findViewById(R.id.all_item_i);
        }
    }
}
