package com.banixc.fsc.fscroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.AwardDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Award;

import java.util.List;


public class AwardAdapter extends BaseAdapter<Award> {

    public AwardAdapter(List<Award> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new AwardAdapter.AwardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_award, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == list.size()) return;
        AwardAdapter.AwardViewHolder viewHolder = (AwardAdapter.AwardViewHolder) holder;
        final Award award = list.get(position);
        viewHolder.name.setText(award.getName());
        viewHolder.date.setText(award.getDate());
        viewHolder.type.setText(award.getType_name());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AwardDetailActivity.start(context, award);
            }
        });
    }


    public class AwardViewHolder extends RecyclerView.ViewHolder {
        //奖励类型
        public TextView name;
        //奖励类型
        public TextView date;
        public TextView type;
        private final View rootView;

        public AwardViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.text_award_name);
            date = (TextView) view.findViewById(R.id.text_award_datetime);
            type = (TextView) view.findViewById(R.id.text_award_type);

            rootView = view.findViewById(R.id.rootView);
        }


    }

}
