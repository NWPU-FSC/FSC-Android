package com.banixc.fsc.fscroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Exam;
import com.banixc.fsc.model.Mark;

import java.util.List;

public class ExamDetailActivity extends BaseActivity {

    public static final String TAG = "EXAM";
    private int student_id;

    private TextView title;
    private TextView sender;
    private TextView content;
    private Exam exam;

    public static void start(Context context, Exam exam) {
        Intent intent = new Intent();
        intent.putExtra(TAG, exam);
        intent.setClass(context, ExamDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("学生成绩");
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
        exam = (Exam) getIntent().getSerializableExtra(TAG);

    }

    private void loadData() {
        if (exam == null) return;
        String senderDetail = "  时间：" + exam.getTime();
        title.setText(exam.getExam_name());
        sender.setText(senderDetail);
        getAction().get_mark(exam.getExam_id(), new ActionCallbackListener<List<Mark>>() {
            @Override
            public void onSuccess(List<Mark> data) {
                String summark="";
                for (int j = 0; j < data.size(); j++) {
                    String imark=data.get(j).getCourse_name()+":"+data.get(j).getMark()+"\n";
                     summark+=imark;
                }
                content.setText(summark);
            }

            @Override
            public void onFailure(Error error) {

            }
        });
        content.setText("");
    }
}
