package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.StudentAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.ParentStudent;

import java.util.List;

/**
 * Created by Banixc on 2016/8/18.
 */

public class StudentListFragment extends BaseListFragment<ParentStudent> {

    public static final String TAG = "AWARDLIST";



    public static StudentListFragment newInstance() {
        Bundle args = new Bundle();
        StudentListFragment fragment = new StudentListFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new StudentAdapter(items));
        refresh();

        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.parent_student(new ActionCallbackListener<List<ParentStudent>>() {
            @Override
            public void onSuccess(List<ParentStudent> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {

            }
        });
    }


}
