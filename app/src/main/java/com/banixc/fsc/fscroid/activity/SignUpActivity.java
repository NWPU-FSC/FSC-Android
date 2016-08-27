package com.banixc.fsc.fscroid.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.User;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {
    private EditText editSignupUsername;
    private EditText editSignupRealname;
    private EditText editSignupPassword;
    private EditText editSignupDoespassword;
    private Button buttonSignupSignup;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getResources().getString(R.string.signup));

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //当点击不同的menu item 是执行不同的操作
        switch (id) {
            case R.id.actionbar_signup_signin:
                SignInActivity.startActivity(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        editSignupUsername = (EditText) findViewById(R.id.edit_signup_username);
        editSignupRealname = (EditText) findViewById(R.id.edit_signup_realname);
        editSignupPassword = (EditText) findViewById(R.id.edit_signup_password);
        editSignupDoespassword = (EditText) findViewById(R.id.edit_signup_doespassword);
        buttonSignupSignup = (Button) findViewById(R.id.button_signup_signup);
        buttonSignupSignup.setOnClickListener(this);
        TextChangedListener(editSignupUsername);
        TextChangedListener(editSignupRealname);
        TextChangedListener(editSignupPassword);
        TextChangedListener(editSignupDoespassword);


    }

    @Override
    protected void initData() {

    }

    protected void TextChangedListener(final EditText edittext) {
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().contains(" ")) {
                    String[] str = charSequence.toString().split(" ");
                    String str1 = "";
                    for (String aStr : str) {
                        str1 += aStr;
                    }
                    edittext.setText(str1);
                    edittext.setSelection(i);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_signup_signup:
                register();
                break;

        }
    }

    public boolean checkinput() {
        if (editSignupUsername.getText().length() == 0 | editSignupPassword.length() == 0 |
                editSignupDoespassword.length() == 0 | editSignupRealname.length() == 0) {
            Toast.makeText(this, "输入为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!editSignupDoespassword.getText().toString().equals(editSignupPassword.getText().toString())) {
            Toast.makeText(this, "密码输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;
    }

    protected void register() {
        buttonSignupSignup.setClickable(false);
        String username = editSignupUsername.getText().toString();
        String password = editSignupPassword.getText().toString();
        String realname = editSignupRealname.getText().toString();
        if (checkinput()) {
            getAction().register(realname, username, password, new ActionCallbackListener<User>() {
                @Override
                public void onSuccess(User user) {
                    new AlertDialog.Builder(SignUpActivity.this).setTitle("注册成功")//设置对话框标题
                            .setMessage("请尽快添加关联学生，以便为您提供更好的服务" +
                                    "在此之前您可以查看新闻公告")//设置显示的内容
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    SignInActivity.startActivity(SignUpActivity.this);
                                }
                            }).show();
                }

                @Override
                public void onFailure(Error error) {
                    buttonSignupSignup.setClickable(true);
                }
            });
        } else buttonSignupSignup.setClickable(true);
    }


}
