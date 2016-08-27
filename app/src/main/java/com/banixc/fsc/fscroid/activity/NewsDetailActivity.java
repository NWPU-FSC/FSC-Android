package com.banixc.fsc.fscroid.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.News;
import com.zzhoujay.markdown.MarkDown;

public class NewsDetailActivity  extends BaseActivity {

    public static final String TAG = "NEWS";

    public static void start(Context context,News news){
        Intent intent = new Intent();
        intent.setClass(context,NewsDetailActivity.class);
        intent.putExtra(TAG,news);
        context.startActivity(intent);
    }

    private TextView title;
    private TextView sender;
    private TextView content;

    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
        news=(News)getIntent().getSerializableExtra(TAG);
    }

    private void loadData(){
        if(news==null) return;
        String senderDetail="发布："+news.getSender_name()+"  时间："+news.getTime();
        title.setText(news.getTitle());
        sender.setText(senderDetail);
        content.post(new Runnable() {
            @Override
            public void run() {
                Spanned spanned = MarkDown.fromMarkdown(news.getContent(), new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                        drawable.setBounds(0, 0, 400, 400);
                        return drawable;
                    }
                }, content);
                content.setText(spanned);
            }
        });
    }



}
