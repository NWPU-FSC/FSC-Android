package com.banixc.fsc.fscroid.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.ParentDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.TeacherParent;

import java.util.List;


public class ParentAdapter extends BaseAdapter<TeacherParent> {

    public ParentAdapter(List<TeacherParent> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new TeacherParentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_parent, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (position == list.size()) return;
        TeacherParentViewHolder holder = (TeacherParentViewHolder) viewHolder;
        final TeacherParent teacherParent = list.get(position);
        holder.type.setText(teacherParent.getRelation_status_name());
        holder.name.setText(teacherParent.getParent_name());
        String relation = teacherParent.getStudent_name() + teacherParent.getRelation_type_name();
        holder.relation.setText(relation);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParentDetailActivity.start(context, teacherParent);
            }
        });

    }


    public class TeacherParentViewHolder extends RecyclerView.ViewHolder {

        public TextView type;
        public TextView name;
        public TextView relation;
        public CardView cardView;

        public TeacherParentViewHolder(View view) {
            super(view);
            type = (TextView) view.findViewById(R.id.text_parent_state);
            name = (TextView) view.findViewById(R.id.text_parent_sender);
            relation = (TextView) view.findViewById(R.id.text_parent_date);
            cardView = (CardView) view.findViewById(R.id.cardView);

        }


    }
}
