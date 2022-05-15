package cn.dreamchase.android.first.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import cn.dreamchase.android.first.R;

public class ListViewAdapter extends BaseAdapter {

    private List<Message> messages;

    private LayoutInflater inflater;

    public ListViewAdapter(Context context,List<Message> messages) {
        inflater = LayoutInflater.from(context);
        this.messages = messages;
    }

    /**
     * -数据源的长度
     * @return
     */
    @Override
    public int getCount() {
        return messages.size();
    }

    /**
     * -每一行的绑定数据源
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        Message message = messages.get(position);

        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();

            if (type == TYPE_SEND) {
                // 发送消息
                convertView  = inflater.inflate(R.layout.item_message_chat_send,null);
            }else if (type == TYPE_ACCEPT) {
                convertView  = inflater.inflate(R.layout.item_message_chat_accept,null);
            }

            viewHolder.tvContent = convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvContent.setText(message.getContent());
        return convertView;
    }

    private class ViewHolder {
        private TextView tvContent;
    }

    /**
     * -以下两个方法是用于练习实现聊天界面
     * @return
     */

    private final int TYPE_ACCEPT = 1; // 消息接收
    private final int TYPE_SEND = 0; // 消息发送

    @Override
    public int getViewTypeCount() {
        return TYPE_ACCEPT + 1;
    }

    // 每个类型对应的int类型的值必须从0开始
    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).isSended()) {
            return TYPE_SEND; // 发送类型
        }
        return TYPE_ACCEPT; // 接收类型
    }
}
