package com.guangdong.greens;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guangdong.greens.Adapter.RecyclerViewAdapter;
import com.guangdong.greens.Net.post_;
import com.guangdong.greens.Other.Item_line;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cat6572325 on 17-3-20.
 */

public class home_ extends Fragment {
    View v;
    RecyclerView ry;
    HomeAdapter homeAdapter;
    ArrayList<HashMap<String, Object>> lists = new ArrayList<>();
    HashMap<String, Object> map;
    RelativeLayout left, mid, right;
    //    分类对话框
    PopupWindow mPopWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.f_home_layout, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        ry = (RecyclerView) v.findViewById(R.id.home_ry);
        left = (RelativeLayout) v.findViewById(R.id.left_cl);
        clicks();
        homeAdapter = new HomeAdapter(getContext(), lists);
        ry.setLayoutManager(new LinearLayoutManager(ry.getContext()));
        ry.addItemDecoration(new Item_line());
        HashMap<String, Object> map1 = new HashMap();
        map1.put("layout", 1);
        map1.put("text", "第三方应用");
        lists.add(map1);
        for (int i = 0; i < 5; i++) {
            map = new HashMap<>();
            map.put("layout", 0);
            map.put("text", i + '?');
            lists.add(map);
        }
        map = new HashMap();
        map.put("layout", 1);
        map.put("text", "第三方应用");
        lists.add(map);
        for (int i = 0; i < 5; i++) {
            map = new HashMap<>();
            map.put("layout", 0);
            map.put("text", i + '?');
            lists.add(map);
        }
        ry.setAdapter(homeAdapter);
        homeAdapter.setOnClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Myasynctask myasynctask = (Myasynctask) new Myasynctask().execute("null");
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });
    }

    /*********** *********/
//    点击事件
    private void clicks() {
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upwindow();
            }
        });
    }

    /***************** *****************/
    //分类and排序对话框
    private void upwindow() {
        //TODO 下拉选项表格
        RecyclerView window_ry;
        RecyclerViewAdapter window_adapter;
        ArrayList<HashMap<String, String>> window_lists = new ArrayList<>();
        //设置contentView
        View contentView = LayoutInflater.from(getContext())
                .inflate(R.layout.d_popwindow, null);
        mPopWindow = new PopupWindow(contentView//设置上下文布局
                , ViewGroup.LayoutParams.MATCH_PARENT//设置为布局宽度，也可以直接指定
                , ActionMenuView.LayoutParams.WRAP_CONTENT//设置高度，同上
                , true);//不知道
        mPopWindow.setContentView(contentView);
        window_ry = (RecyclerView) contentView.findViewById(R.id.window_ry);
        HashMap<String, String> map = new HashMap<>();
        map.put("text", "硬件");
        window_lists.add(map);


        //显示PopupWindow
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.f_home_layout, null);
        //  mPopWindow.showAtLocation(rootview, Gravity.VERTICAL_GRAVITY_MASK, 0, 0);//设置相对于布局的哪里显示
        // mPopWindow.setAnimationStyle(R.style.AnimationPreview);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框。不知道是什么原因
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
// 设置点击窗口外边窗口消失
        mPopWindow.setOutsideTouchable(true);
// 设置此参数获得焦点，否则无法点击
        mPopWindow.setFocusable(true);
// 设置弹出框的显示位置
        mPopWindow.showAsDropDown(v, 0, 0);
//

    }


    /************** *************/
    //工具
    private ArrayList<HashMap<String, String>> addRraylist(ArrayList<HashMap<String, String>> list) {

        return null;
    }

    //net
    private class Myasynctask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            post_ post = new post_();

            post.post_str();

            return null;
        }
    }

    static class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        Context context;
        ArrayList<HashMap<String, Object>> lists;
        private OnItemClickListener mListener;
        private OnItemClickListener mOnItemClickListener;
        private int[] layouts = {R.layout.f_home_item, R.layout.item_label};

        public interface OnItemClickListener {
            void onItemClickListener(View view, int position);

            void onItemLongClickListener(View view, int position);
        }

        public void setOnClickListener(OnItemClickListener listener) {
            this.mListener = listener;
        }

        public HomeAdapter(Context context, ArrayList<HashMap<String, Object>> lists) {
            this.context = context;
            this.lists = lists;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    context).inflate(layouts[viewType], parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
            holder.itemView.setLayoutParams(params);//把params设置给item布局
            //通过返回的viewtype获得硬性布局
            switch ((int) lists.get(position).get("layout")) {
                case 0:
                    if (mListener != null) {//如果设置了监听那么它就不为空，然后回调相应的方法
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = holder.getLayoutPosition();//得到当前点击item的位置pos
                                mListener.onItemClickListener(holder.itemView, pos);//把事件交给我们实现的接口那里处理
                                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();

                            }
                        });
                        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                int pos = holder.getLayoutPosition();//得到当前点击item的位置pos
                                mListener.onItemLongClickListener(holder.itemView, pos);//把事件交给我们实现的接口那里处理
                                return true;
                            }
                        });
                    }

                    break;
            }
        }

        @Override
        public int getItemViewType(int position) {
            return (int) lists.get(position).get("layout");
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            View itemView;
            TextView text;

            public MyViewHolder(View view) {
                super(view);
                itemView = view;
                text = (TextView) itemView.findViewById(R.id.home_items);
            }
        }
    }
}
