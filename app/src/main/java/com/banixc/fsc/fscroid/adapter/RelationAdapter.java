package com.banixc.fsc.fscroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.model.User;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianshuiGong on 2016/8/24
 */
public class RelationAdapter extends android.widget.BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<User> mDatas;

    public RelationAdapter(Context context, List<User> mDatas) {
        this.mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.mDatas = mDatas;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_list_relation, null);
            viewHolder = new ViewHolder();
            viewHolder.mCheckBox = (TextView) view.findViewById(R.id.checkBox);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mCheckBox.setText("id："+mDatas.get(i).getId()+" name："+mDatas.get(i).getName());
        return view;
    }

    private final class ViewHolder {
        TextView mCheckBox;
    }
    public void refreshData(List<User> userInfos) {
        mDatas = userInfos;
    }

}

