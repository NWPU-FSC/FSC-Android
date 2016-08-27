package com.banixc.fsc.fscroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.AwardDetailActivity;
import com.banixc.fsc.fscroid.activity.ExamDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Exam;

import java.util.List;


public class ExamAdapter extends BaseAdapter<Exam> {

    public ExamAdapter(List<Exam> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new ExamAdapter.ExamViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_exam, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == list.size()) return;
        ExamAdapter.ExamViewHolder viewHolder = (ExamAdapter.ExamViewHolder) holder;
        final Exam exam = list.get(position);
        viewHolder.name.setText(exam.getExam_name());
        viewHolder.date.setText(exam.getTime());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExamDetailActivity.start(context, exam);
            }
        });
    }


    public class ExamViewHolder extends RecyclerView.ViewHolder {
        //奖励类型
        public TextView name;
        //奖励类型
        public TextView date;
        private final View rootView;

        public ExamViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.text_exam_name);
            date = (TextView) view.findViewById(R.id.text_exam_datetime);

            rootView = view.findViewById(R.id.rootView);
        }


    }

}
