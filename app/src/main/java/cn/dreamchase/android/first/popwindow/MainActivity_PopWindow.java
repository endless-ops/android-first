package cn.dreamchase.android.first.popwindow;

import android.app.ProgressDialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_PopWindow extends AppCompatActivity {

    private ProgressDialog staticDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowPropUpWindow = findViewById(R.id.btn_prop_up);
        btnShowPropUpWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAsDropUpWindow(btnShowPropUpWindow);
            }
        });

        Button btnPopDown = findViewById(R.id.btn_prop_down);
        btnPopDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomPopupWindow();
            }
        });

    }

    private void showAsDropUpWindow(Button btn) {
        View popView = LayoutInflater.from(this).inflate(R.layout.popup_drop_down,null);
        // 设置PopupWindow View ,宽度，高度
        PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        // 设置允许在外点击消失，必须要给popupwindow设置背景才会有效
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // 显示在 btnShowPopupwindow按钮下面x位置偏移100px就是偏移屏幕左边100px
        popupWindow.showAsDropDown(btn,100,0);
    }

    /**
     * -PopupWindow 显示指定位置，从下往上弹出
     */
    private void showBottomPopupWindow() {
        View popView = LayoutInflater.from(this).inflate(R.layout.popup_bottom,null);

        PopupWindow popupWindow = new PopupWindow(popView,LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        popupWindow.setOutsideTouchable(true); // 允许在外点击消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x30000000)); // 设置背景颜色

        popupWindow.setAnimationStyle(R.style.Animation_Bottom_Dialog); // 设置动画

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_camera_album) {
                    Toast.makeText(MainActivity_PopWindow.this,"点击拍照按钮",Toast.LENGTH_LONG).show();
                }else if (v.getId() == R.id.btn_camera_cancel) {
                    Toast.makeText(MainActivity_PopWindow.this,"点击取消了按钮",Toast.LENGTH_LONG).show();
                }
                popupWindow.dismiss();
            }
        };

        popView.findViewById(R.id.btn_camera_album).setOnClickListener(onClickListener);
        popView.findViewById(R.id.btn_camera_cancel).setOnClickListener(onClickListener);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM,0,0);
    }
}