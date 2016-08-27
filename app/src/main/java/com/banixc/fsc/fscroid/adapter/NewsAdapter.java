package com.banixc.fsc.fscroid.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.NewsDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.News;

import java.util.List;


public class NewsAdapter extends BaseAdapter<News> {

    public NewsAdapter(List<News> list) {
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
        final News news = list.get(position);
        if(news.is_stick()){
            holder.type.setTextColor(Color.RED);
        }
        holder.title.setText(news.getTitle());
        holder.time.setText(news.getTime());
        holder.type.setText(news.getType_name());
        holder.content.setText(news.getSender_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDetailActivity.start(context,news);
            }
        });
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView time;
        public TextView type;
        public TextView content;
        private final CardView cardView;

        public NewsViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.text_news_title);
            time = (TextView) view.findViewById(R.id.text_news_date);
            content = (TextView)view.findViewById(R.id.text_news_sender);
            type = (TextView)view.findViewById(R.id.text_news_course);
             cardView= (CardView)view.findViewById(R.id.cardView);

        }


    }
}
