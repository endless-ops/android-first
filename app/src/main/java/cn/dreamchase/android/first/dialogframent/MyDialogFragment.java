package cn.dreamchase.android.first.dialogframent;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import cn.dreamchase.android.first.R;


public class MyDialogFragment extends DialogFragment {

    public static MyDialogFragment newInstance() {

//        Bundle args = new Bundle();
//
//        MyDialogFragment fragment = new MyDialogFragment();
//        fragment.setArguments(args);
        return new MyDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.hello_world,container,false);

        TextView textView = v.findViewById(R.id.fr_textView);
        textView.setText("This is an instance of MyDialogFrament");

        return v;
    }
}
