package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.HomeworkAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Homework;

import java.util.Date;
import java.util.List;


public class HomeworkListFragment extends BaseListFragment<Homework> {




    public static HomeworkListFragment newInstance() {
        Bundle args = new Bundle();
        HomeworkListFragment fragment = new HomeworkListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new HomeworkAdapter(items));
        refresh();
        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;



        action.student_homework( new ActionCallbackListener<List<Homework>>() {
           @Override
           public void onSuccess(List<Homework> data) {
               updateSuccess(data, OperateType);
           }

           @Override
           public void onFailure(Error error) {
               updateError(error);
           }
       });
    }

}
