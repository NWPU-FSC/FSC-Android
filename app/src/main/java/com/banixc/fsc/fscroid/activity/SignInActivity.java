package com.banixc.fsc.fscroid.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.Connect;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.User;

import java.lang.reflect.Field;


public class SignInActivity extends BaseActivity implements View.OnClickListener {


    private Spinner spinnerSigninUsertype;
    private EditText editSigninUsername;
    private EditText editSigninPassword;
    private Button buttonSigninSignin;
    private int mUserType;

    public static void start(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getResources().getString(R.string.signin));
        //若登陆则跳转
        if (!getAction().is_set_url_correct()) {
            LayoutInflater factory = LayoutInflater.from(SignInActivity.this);//提示框
            final View view = factory.inflate(R.layout.dialog_editbox, null);//这里必须是final的
            final EditText edit = (EditText) view.findViewById(R.id.editText);//获得输入框对象


            new AlertDialog.Builder(SignInActivity.this)
                    .setTitle("设置服务器地址")//提示框标题
                    .setView(view)
                    .setPositiveButton("确定",//提示框的两个按钮
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog,
                                                    int which) {
                                    try {

                                        Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");

                                        field.setAccessible(true);

                                        field.set(dialog, false);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    String url = edit.getText().toString();
                                    getAction().set_url(url);
                                    getAction().connect(new ActionCallbackListener<Connect>() {
                                        @Override
                                        public void onSuccess(Connect data) {
                                            if (data.is_token_correct()) {
                                                Toast.makeText(SignInActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                                initView();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Error error) {
                                            Toast.makeText(SignInActivity.this, "设置失败", Toast.LENGTH_SHORT).show();
                                            SignInActivity.start(SignInActivity.this);

                                        }
                                    });

                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).setCancelable(false).create().show();

        } else {
            if (getAction().is_login())
                loginSuccess(getAction().get_log_user());


            initView();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.signin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //当点击不同的menu item 是执行不同的操作
        switch (id) {
            case R.id.actionbar_signin_signup:
                SignUpActivity.startActivity(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView() {

        spinnerSigninUsertype = (Spinner) findViewById(R.id.spinner_signin_usertype);
        spinnerSigninUsertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] type = getResources().getStringArray(R.array.usertype);
                switch (type[i]) {
                    case "学生":
                        mUserType = 1;
                        break;
                    case "家长":
                        mUserType = 2;
                        break;
                    case "教师":
                        mUserType = 3;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        editSigninUsername = (EditText) findViewById(R.id.edit_signin_username);
        editSigninPassword = (EditText) findViewById(R.id.edit_signin_password);
        buttonSigninSignin = (Button) findViewById(R.id.button_signin_signin);
        buttonSigninSignin.setOnClickListener(this);
        findViewById(R.id.text_signin_findback).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_signin_findback:
                new AlertDialog.Builder(SignInActivity.this).setTitle("友情提示")//设置对话框标题
                        .setMessage("请携带有效证件到XXX处办理密码找回业务，谢谢合作")//设置显示的内容
                        .setPositiveButton("确定", null).show();
                break;
            case R.id.button_signin_signin:
                login();
                break;
        }
    }

    protected void login() {
        buttonSigninSignin.setClickable(false);
        String username = editSigninUsername.getText().toString();
        String password = editSigninPassword.getText().toString();
        getAction().login(username, password, mUserType, new ActionCallbackListener<User>() {
            @Override
            public void onSuccess(User data) {

                loginSuccess(data);
            }

            @Override
            public void onFailure(Error error) {
                buttonSigninSignin.setClickable(true);

            }
        });
    }

    public void loginSuccess(User user) {
        MainActivity.start(this,user);
        finish();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }
}
