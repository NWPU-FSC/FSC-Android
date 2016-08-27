package com.banixc.fsc.fscroid.adapter;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.banixc.fsc.core.Action;
import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.core.ActionImplement;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.AwardDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.ParentStudent;

import java.util.List;


public class StudentAdapter extends BaseAdapter<ParentStudent> {

    public StudentAdapter(List<ParentStudent> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new StudentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_student, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == list.size()) return;
        StudentViewHolder viewHolder = (StudentViewHolder) holder;
        final ParentStudent parentStudent = list.get(position);
        if(Integer.parseInt(parentStudent.getStatus())==1){
            viewHolder.rootView.setClickable(true);
            viewHolder.ischoosable.setText("可选");
            viewHolder.ischoosable.setTextColor(Color.GREEN);
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Action action=new ActionImplement(context) ;
                    action.update_current_student(parentStudent.getStudent_id(), new ActionCallbackListener<Void>() {
                        @Override
                        public void onSuccess(Void data) {
                            Toast.makeText(context, "修改关联学生为"+parentStudent.getStudent_name(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Error error) {
                            Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }else{
            viewHolder.rootView.setClickable(false);
            viewHolder.ischoosable.setText("不可选");
            viewHolder.ischoosable.setTextColor(Color.RED);
        }
        viewHolder.stuid.setText(parentStudent.getStudent_id()+"");
        viewHolder.name.setText(parentStudent.getStudent_name());

    }


    public class StudentViewHolder extends RecyclerView.ViewHolder {
        //审核通过
        public TextView ischoosable;

        public TextView stuid;
        public TextView name;
        private final View rootView;

        public StudentViewHolder(View view) {
            super(view);
            ischoosable = (TextView) view.findViewById(R.id.text_student_choosable);
            stuid = (TextView) view.findViewById(R.id.text_student_id);
            name = (TextView) view.findViewById(R.id.text_student_name);

            rootView = view.findViewById(R.id.rootView);
        }


    }

}
