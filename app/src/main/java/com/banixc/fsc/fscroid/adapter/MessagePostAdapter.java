package com.banixc.fsc.fscroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Post;

import java.util.List;

/**
 * Created by Banixc on 2016/8/23.
 */

public class MessagePostAdapter extends BaseAdapter<Post> {


    public MessagePostAdapter(List<Post> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new MessagePostAdapter.MessagePostViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_post, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == list.size()) return;
        MessagePostAdapter.MessagePostViewHolder viewHolder = (MessagePostAdapter.MessagePostViewHolder) holder;
        final Post post = list.get(position);
        viewHolder.content.setText(post.getContent());
        viewHolder.sender_name.setText(post.getName_time());

    }

    public class MessagePostViewHolder extends RecyclerView.ViewHolder{

        public TextView content;
        public TextView sender_name;
        public View rootView;


        public MessagePostViewHolder(View itemView) {
            super(itemView);

            sender_name = (TextView) itemView.findViewById(R.id.text_post_sender_name_time);
            content = (TextView) itemView.findViewById(R.id.text_post_content);
            rootView = itemView;
        }


    }


}
