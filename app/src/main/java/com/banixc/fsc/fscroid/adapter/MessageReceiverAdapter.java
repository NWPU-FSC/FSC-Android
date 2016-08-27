package com.banixc.fsc.fscroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.api.Receive;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Message;
import com.banixc.fsc.model.Receiver;

import java.util.List;

public class MessageReceiverAdapter extends BaseAdapter<Receiver> {

    public MessageReceiverAdapter(List<Receiver> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new MessageReceiverAdapter.MessageReceiverViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_receiver, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == list.size()) return;
        MessageReceiverViewHolder viewHolder = (MessageReceiverViewHolder) holder;
        final Receiver receiver = list.get(position);
        viewHolder.name.setText(receiver.getName());

    }

    public class MessageReceiverViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public View rootView;

        public MessageReceiverViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_receiver_sender_name);
            rootView = itemView;
        }
    }
}
