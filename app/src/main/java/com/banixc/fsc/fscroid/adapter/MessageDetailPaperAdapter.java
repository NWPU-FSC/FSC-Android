package com.banixc.fsc.fscroid.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.banixc.fsc.fscroid.fragment.MessagePostListFragment;
import com.banixc.fsc.fscroid.fragment.MessageReceiverListFragment;
import com.banixc.fsc.model.Message;

/**
 * Created by Banixc on 2016/8/23.
 */

public class MessageDetailPaperAdapter extends FragmentStatePagerAdapter {

    private static final int PAGE_NUMBER = 2;


    public static final int POST_LIST = 0;
    public static final int RECEIVER_LIST = 1;

    private Fragment[] fragments = new Fragment[PAGE_NUMBER];
    private String[] titles = new String[PAGE_NUMBER];

    private Message message;

    public MessageDetailPaperAdapter(FragmentManager fm,Message message) {
        super(fm);
        this.message = message;
        titles[0] = "Post";
        titles[1] = "Receiver";
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    private Fragment getFragment(int position) {
        if (null != fragments[position]) return fragments[position];
        switch (position){
            case POST_LIST:
                fragments[position] = MessagePostListFragment.newInstance(message);
                break;
            case RECEIVER_LIST:
                fragments[position] = MessageReceiverListFragment.newInstance(message);
                break;
            default:

                break;
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position % titles.length];
    }
}
