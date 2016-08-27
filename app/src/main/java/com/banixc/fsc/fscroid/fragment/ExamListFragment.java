package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.AwardAdapter;
import com.banixc.fsc.fscroid.adapter.ExamAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Exam;

import java.util.List;

/**
 * Created by Banixc on 2016/8/18.
 */

public class ExamListFragment extends BaseListFragment<Exam> {


    public static ExamListFragment newInstance() {
        Bundle args = new Bundle();
        ExamListFragment fragment = new ExamListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new ExamAdapter(items));
        refresh();
        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.get_exam_list(new ActionCallbackListener<List<Exam>>() {
            @Override
            public void onSuccess(List<Exam> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {
                updateError(error);
            }
        });
    }


}
