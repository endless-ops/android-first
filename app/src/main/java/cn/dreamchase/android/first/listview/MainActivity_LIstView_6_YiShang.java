package cn.dreamchase.android.first.listview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.dreamchase.android.first.R;

public class MainActivity_LIstView_6_YiShang extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter_6_YiShang adapter;
    private List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 1.
        initData();

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter = new ListViewAdapter_6_YiShang(this, items));

        // 2.每一行点击监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity_LIstView_6_YiShang.this, "点击item位置：" + position, Toast.LENGTH_LONG).show();
            }
        });

        // 4.添加header和footer
        View header = LayoutInflater.from(this).inflate(R.layout.activity_listview_header,null);
        header.setOnClickListener(onClickListener);
        listView.addHeaderView(header);

        View footer = LayoutInflater.from(this).inflate(R.layout.activity_listview_footer,null);
        footer.setOnClickListener(onClickListener);
        listView.addFooterView(footer);

        // 6.设置显示位置
        listView.setSelection(items.size() - 1);  // 显示列表最后一条


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_footer:
                    items.add("点击底部添加的item");
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.ll_header:
                    Toast.makeText(MainActivity_LIstView_6_YiShang.this,"点击ListView头布局",Toast.LENGTH_LONG).show();
                    break;

            }

       }
    };



    private void initData() {
        items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add("item:" + (i + 1));
        }
    }
}