package cn.dreamchase.android.first.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cn.dreamchase.android.first.R;


public class MainActivity_SwipeRefreshLayout extends AppCompatActivity {

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
        // recyclerView.addItemDecoration(new DividerItemDecoration(this)); // 2.设置分隔线
        recyclerView.setAdapter(recyclerViewAdapter = new RecyclerViewAdapter(this, datas));

        // SwipeRefreshLayout 下拉刷新

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        // 刷新事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(PULL_TO_REFRESH, 3000);
            }
        });

        recyclerView.addOnScrollListener(onScrollListener);

        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500, android.R.color.holo_green_light, R.color.purple_200);
    }

    protected void init() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("item :" + i);
        }
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    public static final int PULL_TO_REFRESH = 1; // 下拉刷新
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case PULL_TO_REFRESH: // 下拉刷新
                    if (datas.size() > 0) {
                        datas.remove(0);
                        recyclerViewAdapter.notifyDataSetChanged(); // 更新第一条
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    break;

                case UP_TO_REFRESH:
                    for (int i = 0; i < 3; i++) {
                        datas.add("load more item:" + i);
                    }
            }
            return true;
        }
    });


    /**
     * -用于- SwipeRefreshLayout 加载更多 -->
     */
    public static final int UP_TO_REFRESH = 2;
    private boolean isLoadMore = false;
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

            int total = layoutManager.getItemCount();


            if (dy > 0) {
                if (!isLoadMore) {
                    handler.sendEmptyMessageDelayed(UP_TO_REFRESH,1000);
                    isLoadMore = true;
                }
            }
        }
    };


}