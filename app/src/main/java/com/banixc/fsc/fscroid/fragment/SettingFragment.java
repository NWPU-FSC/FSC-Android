package com.banixc.fsc.fscroid.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.banixc.fsc.core.Action;
import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.core.ActionImplement;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.model.Connect;
import com.banixc.fsc.model.Error;

import java.util.Date;


/**
 * Created by TianshuiGong on 2016/8/24
 */
public class SettingFragment extends Fragment {


    private TextView textSettingTitle;
    private EditText editSettingAddress;
    private Button buttonSettingConfirm;
    private TextView textSettingAddport;


    public static SettingFragment newInstance() {

        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        final Action action = new ActionImplement(getActivity());

        textSettingTitle = (TextView) view.findViewById(R.id.text_setting_title);
        editSettingAddress = (EditText) view.findViewById(R.id.edit_setting_address);
        buttonSettingConfirm = (Button) view.findViewById(R.id.button_setting_confirm);
        textSettingAddport = (TextView) view.findViewById(R.id.text_setting_addport);
        textSettingAddport.setText(action.get_url());
        buttonSettingConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                action.set_url(editSettingAddress.getText().toString());
                action.connect(new ActionCallbackListener<Connect>() {
                    @Override
                    public void onSuccess(Connect data) {
                        if (data.is_token_correct()) {
                            textSettingAddport.setText(action.get_url());
                        } else Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Error error) {

                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

}


