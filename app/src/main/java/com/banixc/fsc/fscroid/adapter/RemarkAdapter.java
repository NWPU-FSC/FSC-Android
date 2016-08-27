package com.banixc.fsc.fscroid.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.RemarkDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Remark;

import java.util.List;


public class RemarkAdapter extends BaseAdapter<Remark> {


    public RemarkAdapter(List<Remark> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new NewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_remark, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (position == list.size()) return;
        NewsViewHolder holder = (NewsViewHolder) viewHolder;
        final Remark remark = list.get(position);

        holder.time.setText(remark.getTime());
        String title = "来自" + remark.getTeacher_name() + "的评价";
        holder.title.setText(title);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemarkDetailActivity.start(context, remark);
            }
        });
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView title;
        private final CardView cardView;

        public NewsViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.text_remark_date);
            title = (TextView) view.findViewById(R.id.text_remark_title);
            cardView = (CardView) view.findViewById(R.id.cardView);

        }


    }
}
