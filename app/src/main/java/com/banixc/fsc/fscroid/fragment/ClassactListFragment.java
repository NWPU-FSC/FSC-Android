package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.ClassactAdapter;
import com.banixc.fsc.fscroid.adapter.NewsAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Classact;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.News;

import java.util.List;


public class ClassactListFragment extends BaseListFragment<Classact> {


    public static ClassactListFragment newInstance() {
        Bundle args = new Bundle();
        ClassactListFragment fragment = new ClassactListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new ClassactAdapter(items));
        refresh();
        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.get_activity( new ActionCallbackListener<List<Classact>>() {
            @Override
            public void onSuccess(List<Classact> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {
                updateError(error);
            }
        });
    }
}
