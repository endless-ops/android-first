package cn.dreamchase.android.first.dialogframent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import cn.dreamchase.android.first.R;

public class MyAlertDialogFragment extends DialogFragment {

    private DialogInterface.OnClickListener onClickListener;

    public static MyAlertDialogFragment newInstance() {

//        Bundle args = new Bundle();
//
//        MyAlertDialogFragment fragment = new MyAlertDialogFragment();
//        fragment.setArguments(args);
//        return fragment;

        return new MyAlertDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name);


        if (onClickListener != null) {
            builder.setPositiveButton("Ok",onClickListener);
            builder.setNegativeButton("cancel",onClickListener);
        }
        return builder.create();
    }

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v = null;

        return v;
    }
}
