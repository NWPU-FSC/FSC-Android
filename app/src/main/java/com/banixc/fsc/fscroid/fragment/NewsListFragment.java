package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.NewsAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.News;
import com.banixc.fsc.model.Error;


import java.util.List;


public class NewsListFragment extends BaseListFragment<News> {

    //暂时不做条件判断的处理
    private int page = 0;
    private int status = 0;
    private int sender = 0;
    private int type = 0;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new NewsAdapter(items));
        refresh();
        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.news_list(OperateType == REFRESH ? 0 : currentPage,status,sender,type, new ActionCallbackListener<List<News>>() {
            @Override
            public void onSuccess(List<News> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {
                updateError(error);
            }
        });
    }
}
