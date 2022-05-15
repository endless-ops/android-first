package cn.dreamchase.android.first.gridview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.dreamchase.android.first.R;

public class MainActivity_GridView extends AppCompatActivity {

    private GridView gridView;

    private List<Integer> images;

    private GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gridview);

        init();

        gridView = findViewById(R.id.gridview);
        gridView.setAdapter(gridViewAdapter = new GridViewAdapter(this, images));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity_GridView.this, "当前选中了" + ":" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void init() {
        images = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 1) {
                // 对2取余，结果为1
                images.add(R.mipmap.ic_launcher);
            } else {
                images.add(R.mipmap.ic_launcher_round);
            }
        }
    }
}