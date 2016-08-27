package com.banixc.fsc.fscroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.adapter.MessageDetailPaperAdapter;
import com.banixc.fsc.fscroid.adapter.MessagePagerAdapter;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Message;
import com.beardedhen.androidbootstrap.BootstrapButton;

import cn.xm.weidongjian.progressbuttonlib.ProgressButton;

public class MessageDetailActivity extends BaseActivity {

    private CoordinatorLayout mainContent;
    private AppBarLayout activityMessageAppBar;
    private CollapsingToolbarLayout activityMessageCollapsingToolbar;
    private TextView activityMessageDetailTitle;
    private TextView activityMessageDetailContent;
    private TextView activityMessageDetailTime;
    private ViewPager activityMessageViewpager;
    private TabLayout activityMessageTabLayout;
    private FloatingActionButton floatButton;


    private static final String TAG = "MESSAGE_ACTIVITY";

    public static void start(Context context, Message message) {
        Intent intent = new Intent(context, MessageDetailActivity.class);
        intent.putExtra(TAG, message);
        context.startActivity(intent);
    }

    public Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("留言详情");
        actionBar.setHomeButtonEnabled(true);

        initView();
        initData();
        loadData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //对用户按home icon的处理，本例只需关闭activity，就可返回上一activity，即主activity。
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        mainContent = (CoordinatorLayout) findViewById(R.id.main_content);
        activityMessageAppBar = (AppBarLayout) findViewById(R.id.activity_message_app_bar);
        activityMessageCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.activity_message_collapsing_toolbar);
        activityMessageDetailTitle = (TextView) findViewById(R.id.activity_message_detail_title);
        activityMessageDetailContent = (TextView) findViewById(R.id.activity_message_detail_content);
        activityMessageDetailTime = (TextView) findViewById(R.id.activity_message_detail_time_sender);
        activityMessageViewpager = (ViewPager) findViewById(R.id.activity_message_viewpager);
        activityMessageTabLayout = (TabLayout) findViewById(R.id.activity_message_tab_layout);
        floatButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
    }

    @Override
    protected void initData() {
        message = (Message) getIntent().getSerializableExtra(TAG);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessageDetailActivity.this);
                builder.setView(R.layout.dialog_post_message);
                final AlertDialog dialog = builder.create();


                dialog.show();

                final TextView contentView = (TextView) dialog.findViewById(R.id.dialog_post_content);
                final ProgressButton progressButton = (ProgressButton) dialog.findViewById(R.id.dialog_post_send);
                Button cancel = (Button) dialog.findViewById(R.id.dialog_post_cancel);


                assert cancel != null;
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });


                assert progressButton != null;
                progressButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressButton.start();
                        String cont = String.valueOf(contentView.getText());
                        getAction().post_message(message.getId(), cont, new ActionCallbackListener<Void>() {
                            @Override
                            public void onSuccess(Void data) {
                                progressButton.stop();
                                dialog.cancel();
                            }

                            @Override
                            public void onFailure(Error error) {
                                progressButton.animError();
                            }
                        });
                    }
                });
            }
        });
    }


    private void loadData() {
        activityMessageDetailTitle.setText(message.getTitle());
        activityMessageDetailContent.setText(message.getContent());
        activityMessageDetailTime.setText(message.getSender_name()+"@"+message.getTime());
        FragmentStatePagerAdapter messageAdapter = new MessageDetailPaperAdapter(getFragmentManager(),message);
        activityMessageViewpager.setAdapter(messageAdapter);
        activityMessageTabLayout.setupWithViewPager(activityMessageViewpager);
    }


}
