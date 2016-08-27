package com.banixc.fsc.fscroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.HomeworkDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Homework;

import java.util.List;


public class HomeworkAdapter extends BaseAdapter<Homework> {

    public HomeworkAdapter(List<Homework> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new HomeworkAdapter.HomeworkViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_homework, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == list.size()) return;
        HomeworkAdapter.HomeworkViewHolder viewHolder = (HomeworkAdapter.HomeworkViewHolder) holder;
        final Homework homework = list.get(position);
        viewHolder.title.setText(homework.getTitle());
        viewHolder.date.setText(homework.getDate());
        viewHolder.course.setText(homework.getCourse_name());
        viewHolder.sendname.setText(homework.getSender_teacher());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeworkDetailActivity.start(context,homework);

            }
        });
    }


    public class HomeworkViewHolder extends RecyclerView.ViewHolder {
        //奖励类型
        public TextView title;
        //奖励类型
        public TextView date;
        public TextView course;
        public TextView sendname;
        private final View rootView;

        public HomeworkViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.text_hw_title);
            date = (TextView) view.findViewById(R.id.text_hw_date);
            course = (TextView) view.findViewById(R.id.text_hw_course);
            sendname=(TextView) view.findViewById(R.id.text_hw_sender);

            rootView = view.findViewById(R.id.cardView);
        }


    }

}
