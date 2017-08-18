package com.example.administrator.personhealthrecord.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.ChatMessageBean;

import java.util.List;

/**
 * Created by andy on 2017/7/31.
 */

public class ChatRecycleviewAdapter extends RecyclerView.Adapter<ChatRecycleviewAdapter.ViewHolder>{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<ChatMessageBean> chatlist;
    public ChatRecycleviewAdapter(Context context, List<ChatMessageBean> chatList)
    {
        this.mContext=context;
        this.chatlist=chatList;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.chat_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ChatMessageBean bean=chatlist.get(position);
        if(bean.getType() == ChatMessageBean.TYPE_RECEIVED) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.head1.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.head2.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(bean.getContent());
        } else if(bean.getType() == ChatMessageBean.TYPE_SEND) {
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.head2.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.head1.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(bean.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return chatlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
       public LinearLayout leftLayout;
       public LinearLayout rightLayout;
       public TextView leftMsg;
       public TextView rightMsg;
       public ImageView head1;
       public ImageView head2;

        public ViewHolder(View view) {
            super(view);
            leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
            leftMsg = (TextView)view.findViewById(R.id.left_msg);
            rightMsg = (TextView)view.findViewById(R.id.right_msg);
            head1 = (ImageView)view.findViewById(R.id.head_left);
            head2 = (ImageView)view.findViewById(R.id.head_right);
        }
    }
}
