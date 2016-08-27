package com.banixc.fsc.fscroid.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.ClassactDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Classact;

import java.util.List;


public class ClassactAdapter extends BaseAdapter<Classact> {

    public ClassactAdapter(List<Classact> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new NewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_news, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (position == list.size()) return;
        NewsViewHolder holder = (NewsViewHolder) viewHolder;
        final Classact classact = list.get(position);
        holder.title.setText(classact.getTitle());
        holder.time.setText(classact.getDate());
        holder.type.setText("活动");
        holder.sender.setText(classact.getSender_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassactDetailActivity.start(context,classact);
            }
        });
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView time;
        public TextView type;
        public TextView sender;
        private final CardView cardView;

        public NewsViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.text_news_title);
            time = (TextView) view.findViewById(R.id.text_news_date);
            sender = (TextView)view.findViewById(R.id.text_news_sender);
            type = (TextView)view.findViewById(R.id.text_news_course);
             cardView= (CardView)view.findViewById(R.id.cardView);

        }


    }
}
