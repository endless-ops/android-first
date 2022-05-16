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

public class RecyclerHeadFootViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> datas;
    private LayoutInflater inflater;

    public static final int TYPE_HEADER = 1; // header类型
    public static final int TYPE_FOOTER = 2; // footer类型
    private View header = null; // 头View
    private View footer = null; // 脚View

    public RecyclerHeadFootViewAdapter(Context context, List<String> datas) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    /**
     * -创建每一行的view ，用RecyclerView.ViewHolder包装
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            return new RecyclerView.ViewHolder(header) {
            };
        } else if (viewType == TYPE_FOOTER) {
            return new RecyclerView.ViewHolder(footer) {
            };
        }
        View itemView = inflater.inflate(R.layout.recycler_item, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER) {
            return;
        }

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.textView.setText(datas.get(getRealPosition(position)));
    }

    public int getRealPosition(int position) {
        return header == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        if (header == null && footer == null) {
            return datas.size();
        } else if (header == null && footer != null) {
            return datas.size() + 1;
        } else if (header != null && footer == null) {
            return datas.size() + 1;
        } else {
            return datas.size() + 2;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (header != null && position == 0) {
            return TYPE_HEADER;
        } else if (footer != null && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public void setHeader(View header) {
        this.header = header;
        notifyItemInserted(0);
    }

    public void setFooter(View footer) {
        this.footer = footer;
        notifyItemInserted(datas.size() - 1);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textview);
        }
    }

    /**
     * -当调用recyclerView的setOnItemClickListener方法时，发现居然没有该方法，
     * RecyclerView需要自己封装
     */
    public interface RecyclerViewItemClick {
        /**
         * -item点击
         * @param realPosition 数据源position
         * @param position view position
         */
        void onItemClick(int realPosition,int position);
    }

    private RecyclerViewItemClick recyclerViewItemClick;

    public void setRecyclerViewItemClick(RecyclerViewItemClick recyclerViewItemClick) {
        this.recyclerViewItemClick = recyclerViewItemClick;
    }
}
