package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.ParentAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.TeacherParent;

import java.util.List;

/**
 * Created by Banixc on 2016/8/18
 */

public class ParentListFragment extends BaseListFragment<TeacherParent> {

    public static final String TAG = "TEACHERPARENT";



    public static ParentListFragment newInstance() {
        Bundle args = new Bundle();
        ParentListFragment fragment = new ParentListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new ParentAdapter(items));
        refresh();

        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.teacher_parent( new ActionCallbackListener<List<TeacherParent>>() {
            @Override
            public void onSuccess(List<TeacherParent> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {
                updateError(error);
            }
        });
    }


}
