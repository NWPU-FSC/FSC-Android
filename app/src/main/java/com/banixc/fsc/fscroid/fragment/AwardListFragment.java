package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.AwardAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Error;

import java.util.List;

/**
 * Created by Banixc on 2016/8/18
 */

public class AwardListFragment extends BaseListFragment<Award> {

    public static final String TAG = "AWARDLIST";



    public static AwardListFragment newInstance() {
        Bundle args = new Bundle();
        AwardListFragment fragment = new AwardListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new AwardAdapter(items));
        refresh();

        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.award_list( new ActionCallbackListener<List<Award>>() {
            @Override
            public void onSuccess(List<Award> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {
                updateError(error);
            }
        });
    }


}
