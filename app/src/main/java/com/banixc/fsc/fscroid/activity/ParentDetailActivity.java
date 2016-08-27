package com.banixc.fsc.fscroid.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.TeacherParent;
import com.beardedhen.androidbootstrap.BootstrapButton;


public class ParentDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "TEACHERPARENT";

    private TextView textParentDetialParentid;
    private TextView textParentDetialParentuaername;
    private TextView textParentDetialParentname;
    private TextView textParentDetialRelation;
    private TextView textParentDetialStudentid;

    private TeacherParent teacherParent;
    private BootstrapButton buttonParentDetialPass;
    private BootstrapButton buttonParentDetialNotpass;
    private BootstrapButton buttonParentDetialAbolish;

    public static void start(Context context, TeacherParent teacherParent) {
        Intent intent = new Intent();
        intent.putExtra(TAG, teacherParent);
        intent.setClass(context, ParentDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_detail);
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("家长管理");
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

        textParentDetialParentid = (TextView) findViewById(R.id.text_parent_detial_parentid);
        textParentDetialParentuaername = (TextView) findViewById(R.id.text_parent_detial_parentuaername);
        textParentDetialParentname = (TextView) findViewById(R.id.text_parent_detial_parentname);
        textParentDetialRelation = (TextView) findViewById(R.id.text_parent_detial_relation);
        textParentDetialStudentid = (TextView) findViewById(R.id.text_parent_detial_studentid);
        buttonParentDetialPass = (BootstrapButton) findViewById(R.id.button_parent_detial_pass);
        buttonParentDetialPass.setOnClickListener(this);
        buttonParentDetialNotpass = (BootstrapButton) findViewById(R.id.button_parent_detial_notpass);
        buttonParentDetialNotpass.setOnClickListener(this);
        buttonParentDetialAbolish = (BootstrapButton) findViewById(R.id.button_parent_detial_abolish);
        buttonParentDetialAbolish.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        teacherParent = (TeacherParent) getIntent().getSerializableExtra(TAG);
    }

    private void loadData() {
        if (teacherParent == null) return;
        String parentid = "id：" + teacherParent.getParent_id();
        String parentusername = "用户名：" + teacherParent.getParent_username();
        textParentDetialParentuaername.setText(parentusername);
        textParentDetialParentid.setText(parentid);
        textParentDetialParentname.setText(teacherParent.getParent_name());
        String relation = teacherParent.getStudent_name() + teacherParent.getRelation_type_name();
        textParentDetialRelation.setText(relation);
        String studentid = "学生id:" + teacherParent.getStudent_id();
        textParentDetialStudentid.setText(studentid);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_parent_detial_pass:
                changebuttonclickable(0);
                updatestate(1, "当前关联关系状态已更改为【审核通过】");
                break;
            case R.id.button_parent_detial_notpass:
                changebuttonclickable(0);
                updatestate(3, "当前关联关系状态已更改为【审核失败】");
                break;
            case R.id.button_parent_detial_abolish:
                finish();
                break;
        }

    }

    private void updatestate(int state, final String hint) {
        getAction().update_parent_student_status(teacherParent.getParent_id(), teacherParent.getStudent_id(), state, new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data) {
                new AlertDialog.Builder(ParentDetailActivity.this).setTitle("提示")//设置对话框标题
                        .setMessage(hint)//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        }).show();
            }

            @Override
            public void onFailure(Error error) {
                changebuttonclickable(1);
            }
        });
    }

    private void changebuttonclickable(int state) {
        switch (state) {
            case 0:
                buttonParentDetialAbolish.setClickable(false);
                buttonParentDetialNotpass.setClickable(false);
                buttonParentDetialPass.setClickable(false);
                break;
            case 1:
                buttonParentDetialAbolish.setClickable(true);
                buttonParentDetialNotpass.setClickable(true);
                buttonParentDetialPass.setClickable(true);
                break;
        }


    }
}
