package cn.dreamchase.android.first.dialogframent;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_MyAlertDialogFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAlertDialogFragment myAlertDialogFragment = MyAlertDialogFragment.newInstance();
        myAlertDialogFragment.setOnClickListener(onClickListener);
        myAlertDialogFragment.show(getSupportFragmentManager(),"MyAlertDialogFragment");
    }

    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    Log.i("ansen","ok");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Log.i("ansen","cancel");
                    break;
            }
        }
    };
}