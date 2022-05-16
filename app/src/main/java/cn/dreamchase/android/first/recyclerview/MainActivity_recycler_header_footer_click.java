package cn.dreamchase.android.first.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.dreamchase.android.first.R;

public class MainActivity_recycler_header_footer_click extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<String> datas;

    private RecyclerHeadFootViewAdapter recyclerHeadFootViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        // 如果要显示多列或者纵向显示，只需新建不同的构造方法即可，以下代码纵向显示4列，如果还需要反方向显示，把false改成true
        // recyclerView.setLayoutManager(new GridLayoutManager(this,4,GridLayoutManager.HORIZONTAL,false));

        // 设置布局管理器
        // recyclerView.addItemDecoration(new DividerGridItemDecoration(this)); // 2.设置分隔线

        // 3.GridLayoutManager
        recyclerView.setAdapter(recyclerHeadFootViewAdapter = new RecyclerHeadFootViewAdapter(this,datas));

        // 添加header和footer
        View header = LayoutInflater.from(this).inflate(R.layout.recycler_header,recyclerView,false);
        recyclerHeadFootViewAdapter.setHeader(header);

        View footer = LayoutInflater.from(this).inflate(R.layout.recycler_footer,recyclerView,false);
        recyclerHeadFootViewAdapter.setFooter(footer);

        // 6.item 点击事件&& 增加或删除带动画效果
        recyclerHeadFootViewAdapter.setRecyclerViewItemClick(recyclerViewItemClick);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    protected void init() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("item :" + i);
        }
    }

    // 6.item 点击事件&& 增加或删除带动画效果
    private RecyclerHeadFootViewAdapter.RecyclerViewItemClick recyclerViewItemClick
            = new RecyclerHeadFootViewAdapter.RecyclerViewItemClick() {
        @Override
        public void onItemClick(int realPosition, int position) {
            datas.remove(realPosition);

            recyclerHeadFootViewAdapter.notifyItemRemoved(position);

            recyclerHeadFootViewAdapter.notifyItemRangeChanged(position,recyclerHeadFootViewAdapter.getItemCount() - position -1);
        }
    };
}