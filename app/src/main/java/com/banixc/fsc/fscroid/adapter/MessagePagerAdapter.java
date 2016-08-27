package com.banixc.fsc.fscroid.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.banixc.fsc.fscroid.fragment.MessageListFragment;
import com.banixc.fsc.fscroid.fragment.MessageSendFragment;

import java.util.ArrayList;
import java.util.List;

public class MessagePagerAdapter extends FragmentStatePagerAdapter {
    public static final int NUM = 3;

    public static final int RECEIVE_MESSAGE_LIST = 0;
    public static final int SEND_MESSAGE_LIST = 1;
    public static final int SEND_PAGE = 2;

    private Fragment fragmentList[];
    private String titles[];

    public MessagePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        fragmentList = new Fragment[NUM];
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    @Override
    public int getCount() {
        return NUM;
    }

    private Fragment getFragment(int id) {
        if (fragmentList[id] == null)
            switch (id) {
                case RECEIVE_MESSAGE_LIST:
                    fragmentList[id] = MessageListFragment.newInstance(RECEIVE_MESSAGE_LIST);
                    break;
                case SEND_MESSAGE_LIST:
                    fragmentList[id] = MessageListFragment.newInstance(SEND_MESSAGE_LIST);
                    break;
                case SEND_PAGE:
                    fragmentList[id] = MessageSendFragment.newInstance();
                    break;
                default:
                    break;
            }
        return fragmentList[id];
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position % titles.length];
    }
}
