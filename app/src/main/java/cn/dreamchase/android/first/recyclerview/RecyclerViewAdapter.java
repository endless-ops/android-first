package cn.dreamchase.android.first.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.dreamchase.android.first.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHoldr> {
    private LayoutInflater inflater;
    private List<String> datas;
    public RecyclerViewAdapter(Context context, List<String> datas) {
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * -创建每一行的view用recyclerview.viewholder包装
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHoldr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_item,null);
        return new MyViewHoldr(itemView);
    }

    /**
     * -给每一行view填充数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHoldr holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHoldr extends RecyclerView.ViewHolder {
        private TextView textView;


       public MyViewHoldr(@NonNull View itemView) {
           super(itemView);
           textView = itemView.findViewById(R.id.textview);
       }
   }
}
