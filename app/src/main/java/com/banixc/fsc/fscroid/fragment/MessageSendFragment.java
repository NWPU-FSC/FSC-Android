package com.banixc.fsc.fscroid.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.banixc.fsc.core.Action;
import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.core.ActionImplement;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.adapter.RelationAdapter;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Message;
import com.banixc.fsc.model.User;

import java.util.ArrayList;
import java.util.List;

public class MessageSendFragment extends Fragment {


    private ListView mListView;
    private Button sender;
    private EditText title;
    private EditText content;
    private RelationAdapter mAdapter;
    Action action;

    private List<User> mDatas = new ArrayList<User>();
    private ArrayList<Integer> mchoise = new ArrayList<Integer>();

    public static MessageSendFragment newInstance() {
        Bundle args = new Bundle();
        MessageSendFragment fragment = new MessageSendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_message, container, false);

        title = (EditText) view.findViewById(R.id.edittext1);
        content = (EditText) view.findViewById(R.id.edittext2);
        sender = (Button) view.findViewById(R.id.button1);
        mListView = (ListView) view.findViewById(R.id.list_relation);
        action = new ActionImplement(getActivity());
        mListView.setAdapter(mAdapter = new RelationAdapter(getActivity(), mDatas));


        action.get_contacts(new ActionCallbackListener<List<User>>() {
            @Override
            public void onSuccess(List<User> data) {
                mDatas.clear();
                if (data != null) {
                    for (int m = 0; m < data.size(); m++) {
                        mDatas.add(data.get(m));
                    }

                    mAdapter.refreshData(mDatas);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "当前无可选用户", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Error error) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mDatas.get(i).getIschoise() == 0) {
                    mDatas.get(i).setIschoise(1);
                    view.setBackgroundColor(Color.LTGRAY);
                } else {
                    mDatas.get(i).setIschoise(0);
                    view.setBackgroundColor(Color.WHITE);
                }

            }
        });

        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendtitle = title.getText().toString();
                String sendcontent = content.getText().toString();
                for (int m = 0; m < mDatas.size(); m++) {
                    if(mDatas.get(m).getIschoise()==1)
                    mchoise.add(mDatas.get(m).getId());
                }

                if (mchoise == null) {
                    Toast.makeText(getActivity(), "可选关联人数为0", Toast.LENGTH_SHORT).show();
                } else {

                    action.send_message(sendtitle, sendcontent, mchoise, new ActionCallbackListener<Void>() {
                        @Override
                        public void onSuccess(Void data) {
                            Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                            mchoise.clear();
                        }

                        @Override
                        public void onFailure(Error error) {
                            Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
        return view;
    }

}
