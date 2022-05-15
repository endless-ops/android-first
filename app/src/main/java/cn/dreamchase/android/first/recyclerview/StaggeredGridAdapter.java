package cn.dreamchase.android.first.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.dreamchase.android.first.R;

public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.MyViewHolder> {

    private List<ItemData> itemDatas;

    private LayoutInflater inflater;


    public StaggeredGridAdapter(Context context,List<ItemData> itemDatas) {
        inflater = LayoutInflater.from(context);
        this.itemDatas = itemDatas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_staggered_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemData itemData = itemDatas.get(position);

        holder.textView.setText(itemData.getContent());

        holder.textView.setHeight(itemData.getHeight());
    }

    @Override
    public int getItemCount() {
        return itemDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
