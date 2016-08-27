package com.banixc.fsc.fscroid.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.fscroid.R;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int STATUS_INIT = -1;

    public static final int STATUS_HAS_MORE = 0;
    public static final int STATUS_HAS_NO_MORE = 1;
    public static final int STATUS_LOAD_FAIL = 2;

    public static final int TYPE_FOOT_LOADING = -128;//loadMore的底部

    public static final int TYPE_FOOT_NO_MORE = -129;//NoMore的底部

    public static final int TYPE_FOOT_LOAD_FAIL = -130;//NoMore的底部


    protected List<T> list = new ArrayList<>();

    protected int status = STATUS_INIT;

    protected Context context;

    public BaseAdapter(List<T> list) {
        this.list = list;
    }

    protected int getFooterType() {
        switch (status) {
            case STATUS_INIT:
            case STATUS_HAS_MORE:
                return TYPE_FOOT_LOADING;
            case STATUS_HAS_NO_MORE:
                return TYPE_FOOT_NO_MORE;
            case STATUS_LOAD_FAIL:
                return TYPE_FOOT_LOAD_FAIL;
            default:
                return TYPE_FOOT_LOAD_FAIL;
        }
    }

    public void updateData(List<T> list) {
        this.list = list;
    }

    public void addData(List<T> list) {
        this.list.addAll(list);
    }

    public void setHasMore() {
        status = STATUS_HAS_MORE;
    }

    public void setHasNoMore() {
        status = STATUS_HAS_NO_MORE;
    }

    public void setLoadFail() {
        status = STATUS_LOAD_FAIL;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) context = parent.getContext();
        switch (viewType) {
            case TYPE_FOOT_LOADING:
                return new FootViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_footer_loading, parent, false));
            case TYPE_FOOT_NO_MORE:
                return new FootViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_footer_no_more, parent, false));
            case TYPE_FOOT_LOAD_FAIL:
                return new FootViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_footer_load_fail, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return getFooterType();
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        if (status == STATUS_INIT) return list.size();
        return list.size() + 1;
    }

    class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
