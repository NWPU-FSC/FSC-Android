package com.banixc.fsc.fscroid.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.Classact;
import com.banixc.fsc.model.Remark;
import com.zzhoujay.markdown.MarkDown;

public class RemarkDetailActivity extends BaseActivity {

    public static final String TAG = "Remark";
    private String sendername;
    private String sendertime;

    public static void start(Context context, Remark remark) {
        Intent intent = new Intent();
        intent.setClass(context, RemarkDetailActivity.class);
        intent.putExtra(TAG, remark);
        context.startActivity(intent);
    }

    private TextView title;
    private TextView sender;
    private TextView content;

    private Remark remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
        title = (TextView) findViewById(R.id.text_detial_title);
        sender = (TextView) findViewById(R.id.text_news_detail_sender);
        content = (TextView) findViewById(R.id.text_detail_content);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    @Override
    protected void initData() {
        remark = (Remark) getIntent().getSerializableExtra(TAG);
    }

    private void loadData() {
        if (remark == null) return;
        sendertime = "时间：" + remark.getTime();
        sendername = "来自" + remark.getTeacher_name() + "老师的评价";
        title.setText(sendername);
        sender.setText(sendertime);
        content.setText(remark.getContent());


    }
}
