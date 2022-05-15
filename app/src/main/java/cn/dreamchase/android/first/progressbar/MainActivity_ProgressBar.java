package cn.dreamchase.android.first.progressbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_ProgressBar extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pb_horizontal);

        findViewById(R.id.btn_add).setOnClickListener(onClickListener);
        findViewById(R.id.btn_reduce).setOnClickListener(onClickListener);

    }

   private View.OnClickListener onClickListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           switch (v.getId()) {
               case R.id.btn_add:
                   progressBar.setProgress(progressBar.getProgress() + 10);
                   break;

               case R.id.btn_reduce:
                   progressBar.setProgress(progressBar.getProgress() - 10);
                   break;
           }
       }
   };

}