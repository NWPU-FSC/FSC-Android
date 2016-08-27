package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.RemarkAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;

import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Remark;

import java.util.List;

/**
 * Created by Banixc on 2016/8/18
 */

public class RemarkListFragment extends BaseListFragment<Remark> {

    public static final String TAG = "RRMARK";



    public static RemarkListFragment newInstance() {
        Bundle args = new Bundle();
        RemarkListFragment fragment = new RemarkListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new RemarkAdapter(items));
        refresh();

        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.get_remark(new ActionCallbackListener<List<Remark>>() {
            @Override
            public void onSuccess(List<Remark> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {
                updateError(error);
            }
        });
    }


}
