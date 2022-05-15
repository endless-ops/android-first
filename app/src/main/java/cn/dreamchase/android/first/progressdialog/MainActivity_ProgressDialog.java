package cn.dreamchase.android.first.progressdialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_ProgressDialog extends AppCompatActivity {

    private ProgressDialog staticDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 创建对象，调用Dialog的show()方法显示
//         ProgressDialog dialog = new ProgressDialog(this);
//         dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // 水平
//        dialog.incrementProgressBy(20);
//         dialog.setCanceledOnTouchOutside(false);  // 设置点击dialog外是否取消Dialog进度条
//
//        dialog.show();  // 显示

        // 调用ProgressDialog后5秒关闭
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staticDialog = ProgressDialog.show(MainActivity_ProgressDialog.this, "这是标题", "这是内容");
                // 开启一个线程，在新线程里执行耗时的方法
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0); // 延迟5秒之后发送消息给handle
                    }
                }).start();
            }
        });
    }


    /**
     * Handler handler = new Handler()  {
     *         @Override
     *         public void handleMessage(@NonNull Message msg) {
     *             super.handleMessage(msg);
     *         }
     *     };
     * 上面的那个需要会导致内存溢出
     * -官方给的方法
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            staticDialog.dismiss(); // 关闭progressDialog
            return true;
        }
    });
}