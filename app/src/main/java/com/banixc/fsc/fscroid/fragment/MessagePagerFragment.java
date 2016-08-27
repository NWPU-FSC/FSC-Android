package com.banixc.fsc.fscroid.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.adapter.MessagePagerAdapter;


import static com.banixc.fsc.fscroid.adapter.MessagePagerAdapter.*;


public class MessagePagerFragment extends Fragment {

    private String titles[];

    public static MessagePagerFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MessagePagerFragment fragment = new MessagePagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        initData();
        initView(view);
        return view;
    }

    public void initData(){
        titles = new String[NUM];
        titles[RECEIVE_MESSAGE_LIST] = "收件箱";
        titles[SEND_MESSAGE_LIST] = "发件箱";
        titles[SEND_PAGE] = "发信息";
    }


    private void initView(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_message_tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_message_viewpager);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(titles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[1]));
        tabLayout.addTab(tabLayout.newTab().setText(titles[2]));

        FragmentStatePagerAdapter messageAdapter = new MessagePagerAdapter(getActivity().getFragmentManager(),titles);

        viewPager.setAdapter(messageAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

}
