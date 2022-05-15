package cn.dreamchase.android.first.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.dreamchase.android.first.R;

public class MainActivity_LinearLayoutManager extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<String> datas;

    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 设置布局管理器
        recyclerView.addItemDecoration(new DividerItemDecoration(this)); // 2.设置分隔线

        // 3.GridLayoutManager
        recyclerView.setAdapter(recyclerViewAdapter = new RecyclerViewAdapter(this,datas));
    }

    protected void init() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("item :" + i);
        }
    }
}