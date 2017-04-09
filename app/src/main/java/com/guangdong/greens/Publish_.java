package com.guangdong.greens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.guangdong.greens.Adapter.RecyclerViewAdapter;
import com.guangdong.greens.Net.LruCache_Img;
import com.guangdong.greens.Other.Item_line;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.guangdong.greens.Other.ResizeBitmap.resizeImage;

/**
 * Created by cat6572325 on 17-4-4.
 * 点击浮动按钮弹出对话框，可选择图片，获取图片路径后发送请求上传，服务器返回的地址记录到ｉｍａｇｅＶｉｅｗ上的ｔａｇ并显示，
 * ｔｘｔ只保存到临时全局的Ｓｔｒｉｎｇ．
 * 对话框只能通过点击取消或者确定来消去
 * 点击对话框确定后增加一个ｉｔｅｍ，用的是上面步骤的图片和文字．
 * ｉｔｅｍ包括一个ｉｍａｇｅｖｉｅｗ和一个ｔｅｘｔｖｉｅｗ．一个ｂｕｔｔｏｎ用于修改的
 */

public class Publish_ extends AppCompatActivity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    break;
            }
        }
    };
    PopupWindow mPopWindow;
    ArrayList<HashMap<String, Object>> images = new ArrayList<>();
    Publish_Recycler_Adapter ry_Adapter;
    public RecyclerView images_ry;
    ImageView img;

    LruCache_Img lruCacheImg = new LruCache_Img();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_publish_layout);
        initView();
    }

    private void initView() {
        images_ry = (RecyclerView) findViewById(R.id.Publish_ry);
        LinearLayoutManager HardwareRylm = new LinearLayoutManager(images_ry.getContext());
//        HardwareRylm.setOrientation(LinearLayoutManager.HORIZONTAL);
        images_ry.setLayoutManager(HardwareRylm);
//        images_ry.addItemDecoration(new Item_line());
        ry_Adapter = new Publish_Recycler_Adapter(Publish_.this, images);
        images_ry.setAdapter(ry_Adapter);


    }

    private void loadImages() {
//        Cursor cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//        while (cursor.moveToNext()) {
////            images.add(cursor.getString(cursor.getColumnIndex("_data")));
//            HashMap<String, Object> map = new HashMap<>();
//            String path = cursor.getString(cursor.getColumnIndex("_data"));
//            map.put("image", path);
//            images.add(map);
//            HashMap<String, Object> map1 = new HashMap<String, Object>();
//            map1.put("?", "grid");
//            map1.put("path", path);
//            lruCacheImg.checkLucache(map1);
//        }
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                Uri uri = data.getData();
                if (uri == null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                    }
                } else {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    } catch (IOException e) {
                        Toast.makeText(Publish_.this, "暂不支持获取sd卡图片", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void itemClick() {
        ry_Adapter.setOnClickListener(new Publish_Recycler_Adapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                popWindow(view);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });
    }

    public void Add_publish_item(View v) {
        popWindow(v);
    }

    private void popWindow(View v) {
        EditText editStep;
        //设置contentView
        View contentView = LayoutInflater.from(Publish_.this)
                .inflate(R.layout.d_popwindow, null);
        mPopWindow = new PopupWindow(contentView//设置上下文布局
                , ViewGroup.LayoutParams.MATCH_PARENT//设置为布局宽度，也可以直接指定
                , ActionMenuView.LayoutParams.WRAP_CONTENT//设置高度，同上
                , true);//不知道

        mPopWindow.setContentView(contentView);
        editStep = (EditText) contentView.findViewById(R.id.popWindow_e);
        //显示PopupWindow
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框。不知道是什么原因
//        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
// 设置点击窗口外边窗口消失
        mPopWindow.setOutsideTouchable(false);
// 设置此参数获得焦点，否则无法点击
        mPopWindow.setFocusable(true);
// 设置弹出框的显示位置
        mPopWindow.showAsDropDown(v, 200, 200);


    }

    /***** *****/
//    工具
    static class myAsynkTask extends AsyncTask<String, Void, Bitmap> {
        ImageView img;
        String path;

        public myAsynkTask(ImageView img, String path) {
            this.img = img;
            this.path = path;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                if (img.getTag().toString() == path) {
                    //TODO 缩小bitmap
                    Bitmap resizeBitmap = resizeImage(bitmap, 400, 400);
                    Log.e("bitmap", String.valueOf(bitmap.getHeight()));
                    Log.e("resizebitmap", String.valueOf(resizeBitmap.getHeight()));
                    img.setImageBitmap(resizeBitmap);
                } else {
                    Log.e("tagError", "");
                }
            }
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //选择图片
            FileInputStream fis = null;
            Bitmap bitmap = null;
            try {
                fis = new FileInputStream(path);
                bitmap = BitmapFactory.decodeStream(fis);
                fis.close();
            } catch (FileNotFoundException e) {
                Log.e("设置图片的adapter", e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }


    static class Publish_Recycler_Adapter extends RecyclerView.Adapter<Publish_Recycler_Adapter.Publish_Hoder> {

        ArrayList<HashMap<String, Object>> lists;
        Context context;
        public OnItemClickListener mListener;
        public OnItemClickListener mOnItemClickListener;

        public Publish_Recycler_Adapter(Context context, ArrayList<HashMap<String, Object>> lists) {
            this.context = context;
            this.lists = lists;
        }

        public interface OnItemClickListener {
            void onItemClickListener(View view, int position);

            void onItemLongClickListener(View view, int position);
        }

        public void setOnClickListener(OnItemClickListener listener) {
            this.mListener = listener;
        }

        @Override
        public Publish_Recycler_Adapter.Publish_Hoder onCreateViewHolder(ViewGroup parent, int viewType) {
            Publish_Hoder holder = new Publish_Hoder(LayoutInflater.from(
                    context).inflate(R.layout.publish_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final Publish_Recycler_Adapter.Publish_Hoder holder, int position) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
            holder.itemView.setLayoutParams(params);//把params设置给item布局
            holder.img.setTag(lists.get(position).get("image").toString());
            holder.txt.setText(lists.get(position).get("txt").toString());
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
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }
//
//        Cursor cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//        while(cursor.moveToNext())
//
//        {
////            images.add(cursor.getString(cursor.getColumnIndex("_data")));
//            HashMap<String, Object> map = new HashMap<>();
//            String path = cursor.getString(cursor.getColumnIndex("_data"));
//            map.put("image", path);
//            images.add(map);
//            HashMap<String, Object> map1 = new HashMap<String, Object>();
//            map1.put("?", "grid");
//            map1.put("path", path);
//            lruCacheImg.checkLucache(map1);
//        }

        class Publish_Hoder extends RecyclerView.ViewHolder {
            View itemView;
            ImageView img;
            TextView txt;

            public Publish_Hoder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                img = (ImageView) itemView.findViewById(R.id.publish_item_i);
                txt = (TextView) itemView.findViewById(R.id.publish_item_t);
            }
        }
    }
}
