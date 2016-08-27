package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.AwardAdapter;
import com.banixc.fsc.fscroid.adapter.SigninAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Signin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Banixc on 2016/8/18.
 */

public class SignInListFragment extends BaseListFragment<Signin> {

    public static final String TAG = "SIGNIN";


    public static SignInListFragment newInstance() {
        Bundle args = new Bundle();
        SignInListFragment fragment = new SignInListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater, container, new SigninAdapter(items));
        refresh();

        return view;
    }



    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.student_signin(new Timestamp(System.currentTimeMillis()), new ActionCallbackListener<List<Signin>>() {
            @Override
            public void onSuccess(List<Signin> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {
                updateError(error);
            }
        });
    }


}
