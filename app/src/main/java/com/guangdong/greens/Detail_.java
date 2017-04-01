package com.guangdong.greens;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.guangdong.greens.Adapter.Graphic_steps_ry_Adapter;
import com.guangdong.greens.Adapter.comment_Adapter;
import com.guangdong.greens.Other.Item_line;

import java.util.ArrayList;
import java.util.HashMap;


public class Detail_ extends AppCompatActivity {
    RecyclerView comment_ry, Graphic_steps_ry;
    ArrayList<HashMap<String, Object>> GraphicLists = new ArrayList<>(), CommentLists = new ArrayList<>();
    comment_Adapter Adapter;
    Graphic_steps_ry_Adapter Adapter1;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.a_detail_layout);

    }

    private void initView() {
        /** 初始化评论列表 **/
        comment_ry = (RecyclerView) findViewById(R.id.detailOfcomment_ry);
        Adapter = new comment_Adapter(this, CommentLists);
        comment_ry.addItemDecoration(new Item_line());
        loadComment();
        comment_ry.setAdapter(Adapter);

        /** 初始化图文列表 **/
        Graphic_steps_ry = (RecyclerView) findViewById(R.id.Graphic_steps_ry);
        Adapter1 = new Graphic_steps_ry_Adapter(this, GraphicLists);
        Graphic_steps_ry.addItemDecoration(new Item_line());
        loadGraphic();
        Graphic_steps_ry.setAdapter(Adapter1);
    }

    private void loadGraphic() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("image", "http://pic10.nipic.com/20100929/4308872_150108084472_2.jpg");
        map.put("text", "硬件方面的信息，你可以学到如何用一个硬件来和这个世界任何其他物质拼接成对人有巨大帮助的器械＆＆工具");
        GraphicLists.add(map);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("image", "http://scimg.jb51.net/allimg/151228/14-15122Q60431W4.jpg");
        map1.put("text", "硬件方面的信息，你可以学到如何用一个硬件来和这个世界任何其他物质拼接成对人有巨大帮助的器械＆＆工具");
        GraphicLists.add(map1);
    }

    private void loadComment() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("text", "这非常有用，如果你不想默默无闻的过完一生，也不想整天思考明天要做什么的话，可以学学");
        GraphicLists.add(map);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("text", "这非常有用，如果你不想默默无闻的过完一生，也不想整天思考明天要做什么的话，可以学学");
        GraphicLists.add(map1);
    }

}
