package cn.dreamchase.android.first.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.dreamchase.android.first.R;

public class MainActivity_GridLayoutManager extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<String> datas;

    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        // 如果要显示多列或者纵向显示，只需新建不同的构造方法即可，以下代码纵向显示4列，如果还需要反方向显示，把false改成true
        // recyclerView.setLayoutManager(new GridLayoutManager(this,4,GridLayoutManager.HORIZONTAL,false));

        // 设置布局管理器
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this)); // 2.设置分隔线

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