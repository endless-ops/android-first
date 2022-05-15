package cn.dreamchase.android.first.listview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.dreamchase.android.first.R;

public class MainActivity_7_ShixianLiaoTianJieMian extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 1.
        initData();

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter = new ListViewAdapter(this, messages));


        listView.setSelection(messages.size() - 1);  // 显示列表最后一条


    }

    private void initData() {
        messages = new ArrayList<>();
        messages.add(new Message("为什么程序员到哪里都背着电脑包",false));
        messages.add(new Message("因为他们没有别的包包",true));
        messages.add(new Message("程序员最烦两件事，第一件是别人要他给自己的代码写文档，第二件呢？",false));
        messages.add(new Message("是别人的程序没有文档",true));
        messages.add(new Message("如何生成一个随机的字符串",false));
        messages.add(new Message("让新手退出VIM",true));
    }
}