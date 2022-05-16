package cn.dreamchase.android.first.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.dreamchase.android.first.R;

public class FragmentTest extends Fragment {
    private String content;

    private int background;

    public FragmentTest(String content,int background) {
        this.content = content;
        this.background = background;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test,null);
        TextView tvContent = rootView.findViewById(R.id.vp_textview);
        tvContent.setText(content);
        rootView.setBackgroundResource(background);
        return rootView;
    }
}
