package com.banixc.fsc.fscroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.Award;


public class AwardDetailActivity extends BaseActivity {

    public static final String TAG = "AWARD";

    private TextView title;
    private TextView sender;
    private TextView content;
    private  Award award;

    public static void start(Context context, Award award){
        Intent intent = new Intent();
        intent.putExtra(TAG,award);
        intent.setClass(context,AwardDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("学生奖罚");
        actionBar.setHomeButtonEnabled(true);

        initView();
        initData();
        loadData();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
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
        title = (TextView) findViewById(R.id.text_detial_title);
        sender = (TextView) findViewById(R.id.text_news_detail_sender);
        content = (TextView) findViewById(R.id.text_detail_content);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    protected void initData() {
        award=(Award)getIntent().getSerializableExtra(TAG);
    }

    private void loadData(){
        if(award==null) return;
        String senderDetail=award.getType_name()+"  时间："+award.getDate();
        title.setText(award.getName());
        sender.setText(senderDetail);
        content.setText(award.getDescription());
    }
}
