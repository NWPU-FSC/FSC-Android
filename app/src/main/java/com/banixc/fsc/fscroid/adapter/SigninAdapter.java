package com.banixc.fsc.fscroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.AwardDetailActivity;
import com.banixc.fsc.fscroid.base.BaseAdapter;
import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Signin;

import java.util.List;


public class SigninAdapter extends BaseAdapter<Signin> {

    public SigninAdapter(List<Signin> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder != null) return holder;
        return new SigninAdapter.SigninViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_signin, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == list.size()) return;
        SigninAdapter.SigninViewHolder viewHolder = (SigninAdapter.SigninViewHolder) holder;
        final Signin signin = list.get(position);
        viewHolder.time.setText(signin.getTime());
    }


    public class SigninViewHolder extends RecyclerView.ViewHolder {

        public TextView time;
        public SigninViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.text_signin_datetime);
        }


    }

}
