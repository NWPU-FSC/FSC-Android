package com.banixc.fsc.fscroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.MessageAdapter;
import com.banixc.fsc.fscroid.adapter.NewsAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Message;
import com.banixc.fsc.model.News;
import static com.banixc.fsc.fscroid.adapter.MessagePagerAdapter.*;
import java.util.List;

/**
 * Created by Banixc on 2016/8/22.
 */

public class MessageListFragment extends BaseListFragment<Message> {

    public static final String TAG = "MESSAGE_LIST";

    private int type;

    public static MessageListFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(TAG,type);
        MessageListFragment fragment = new MessageListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater,container,new MessageAdapter(items));
        initData();
        refresh();
        return view;
    }

    public void initData(){
        type = getArguments().getInt(TAG,RECEIVE_MESSAGE_LIST);
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        switch (type){
            case SEND_MESSAGE_LIST:
                action.get_send_message(OperateType == REFRESH ? 0 : currentPage,new ActionCallbackListener<List<Message>>() {
                    @Override
                    public void onSuccess(List<Message> data) {
                        updateSuccess(data, OperateType);
                    }

                    @Override
                    public void onFailure(Error error) {
                        updateError(error);
                    }
                });
                break;
            case RECEIVE_MESSAGE_LIST:
                action.get_receive_message(OperateType == REFRESH ? 0 : currentPage,new ActionCallbackListener<List<Message>>() {
                    @Override
                    public void onSuccess(List<Message> data) {
                        updateSuccess(data, OperateType);
                    }

                    @Override
                    public void onFailure(Error error) {
                        updateError(error);
                    }
                });
                break;
        }
    }
}
