package com.banixc.fsc.fscroid.fragment;

import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.adapter.MessageReceiverAdapter;
import com.banixc.fsc.fscroid.adapter.NewsAdapter;
import com.banixc.fsc.fscroid.base.BaseListFragment;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Message;
import com.banixc.fsc.model.Post;
import com.banixc.fsc.model.Receiver;

import java.util.List;

/**
 * Created by Banixc on 2016/8/23.
 */

public class MessageReceiverListFragment extends BaseListFragment<Receiver> {

    private static final String TAG = "MESSAGE_RECEIVER_LIST_FRAGMENT";

    private Message message;

    public static MessageReceiverListFragment newInstance(Message message) {

        Bundle args = new Bundle();
        args.putSerializable(TAG, message);
        MessageReceiverListFragment fragment = new MessageReceiverListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initData() {
        message = (Message) getArguments().getSerializable(TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater, container, new MessageReceiverAdapter(items));
        initData();
        refresh();
        return view;
    }

    @Override
    protected void update() {
        final int OperateType = requireStatus;
        action.get_message_receiver(message.getId(), getNextPage(OperateType), new ActionCallbackListener<List<Receiver>>() {

            @Override
            public void onSuccess(List<Receiver> data) {
                updateSuccess(data, OperateType);
            }

            @Override
            public void onFailure(Error error) {
                updateError(error);
            }
        });
    }
}
