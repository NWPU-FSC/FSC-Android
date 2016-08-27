package com.banixc.fsc.fscroid.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.MessageDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Message;
import com.banixc.fsc.model.Remark;

import java.util.List;

import static com.banixc.fsc.fscroid.R.id.rootView;

/**
 * Created by Banixc on 2016/8/22.
 */

public class MessageAdapter extends BaseAdapter<Message> {

    public MessageAdapter(List<Message> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new MessageAdapter.MessageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_message, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == list.size()) return;
        MessageViewHolder viewHolder = (MessageViewHolder)holder;
        final Message message = list.get(position);

        viewHolder.sender.setText(message.getSend_time());
        viewHolder.title.setText(message.getTitle());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageDetailActivity.start(view.getContext(),message);
            }
        });
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        public TextView sender;
        public TextView title;
        public View rootView;


        public MessageViewHolder(View itemView) {
            super(itemView);
            sender = (TextView) itemView.findViewById(R.id.text_message_sender_time);
            title = (TextView) itemView.findViewById(R.id.text_message_title);
            rootView = itemView;
        }
    }
}
