package com.banixc.fsc.fscroid.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.Action;
import com.banixc.fsc.core.ActionImplement;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.model.Error;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseListFragment<T> extends Fragment {


    public static final int NOTASK = 0;

    public static final int LOADMORE = 1;

    public static final int REFRESH = 2;

    public static final int REQUIRE_QUE = 30;

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;

    protected int currentPage = 0;
    protected List<T> items = new ArrayList<>();
    protected BaseAdapter<T> adapter;
    protected int requireStatus = NOTASK;
    protected boolean hasMore = true; //记录是否还有更多
    protected Action action;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public View initViews(LayoutInflater inflater, ViewGroup container, BaseAdapter<T> adapter) {

        View view = inflater.inflate(R.layout.list_base, container, false);

        action = new ActionImplement(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        this.adapter = adapter;
        recyclerView.setAdapter(adapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    if (requireStatus == NOTASK && hasMore) {
                        requireStatus = LOADMORE;
                        update();
                    }
                }
            }
        });
        assert swipeRefreshLayout != null;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        return view;
    }

    protected void refresh() {
        requireStatus = REFRESH;
        currentPage = currentPage >= 0 ? 0 : -1;
        update();
    }

    protected void updateSuccess(List<T> list, int OperateType) {
        if (OperateType != requireStatus)
            return;
        switch (requireStatus) {
            case LOADMORE:
                hasMore = list.size() == REQUIRE_QUE;
                if (hasMore) adapter.setHasMore();
                else adapter.setHasNoMore();
                addPage();
                int start = adapter.list.size();
                adapter.addData(list);
                adapter.notifyItemRangeInserted(start, list.size());
                break;

            case REFRESH:
                swipeRefreshLayout.setRefreshing(false);
                hasMore = list.size() == REQUIRE_QUE;
                if (hasMore) adapter.setHasMore();
                else adapter.setHasNoMore();
                addPage();
                adapter.updateData(list);
                adapter.notifyDataSetChanged();
                break;
        }
        requireStatus = NOTASK;
    }

    protected void updateError(Error error) {
        swipeRefreshLayout.setRefreshing(false);
        if (error != null)
            Snackbar.make(getView(), "Error:" + error + " " + error.getMessage(), Snackbar.LENGTH_LONG).show();
    }


    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState);

    protected abstract void update();

    private void addPage() {
        currentPage = currentPage >= 0 ? currentPage + 1 : currentPage - 1;
    }

    protected int getNextPage(int OperateType){
        return OperateType == REFRESH ? 0 : currentPage;
    }

}
