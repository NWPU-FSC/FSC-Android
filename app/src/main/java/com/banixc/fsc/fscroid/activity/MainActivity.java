package com.banixc.fsc.fscroid.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.fscroid.fragment.AwardListFragment;
import com.banixc.fsc.fscroid.fragment.ClassactListFragment;
import com.banixc.fsc.fscroid.fragment.ExamListFragment;
import com.banixc.fsc.fscroid.fragment.HomeworkListFragment;
import com.banixc.fsc.fscroid.fragment.MessagePagerFragment;
import com.banixc.fsc.fscroid.fragment.NewsListFragment;
import com.banixc.fsc.fscroid.fragment.ParentListFragment;
import com.banixc.fsc.fscroid.fragment.RemarkListFragment;
import com.banixc.fsc.fscroid.fragment.SettingFragment;
import com.banixc.fsc.fscroid.fragment.SignInListFragment;
import com.banixc.fsc.fscroid.fragment.StudentFragment;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.User;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected static final int FRAGMENT_NUMBER = 11;

    protected static final int NEWS_FRAGMENT = 0;
    protected static final int AWARD_FRAGMENT = 1;
    protected static final int STUDENT_FRAGMENT = 2;
    protected static final int HOMEWORK_FRAGMENT = 3;
    protected static final int EXAM_FRAGMENT = 4;
    protected static final int SIGNIN_FRAGMENT = 5;
    protected static final int CLASSACT_FRAGMENT = 6;
    protected static final int REMARK_FRAGMENT = 7;
    protected static final int MESSAGE_FRAGMENT = 8;
    protected static final int PARENT_FRAGMENT = 9;
    protected static final int SETTING_FRAGMENT = 10;
    //主Activity的所有Fragment
    protected Fragment[] fragments;
    //对应的名字
    protected String[] fragmentTitle;
    //记录当前是哪个Activity
    protected int current_fragment;
    private int type;
    private User user;
    private NavigationView navigationView;
    private TextView user_nameView;
    private TextView type_name;


    public static void start(Context context,User user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("User",user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
        toggle.syncState();

        initView();
        initData();

        fragments = new Fragment[FRAGMENT_NUMBER];
        fragmentTitle = new String[FRAGMENT_NUMBER];
        current_fragment = -1;
        setFragment(NEWS_FRAGMENT);
    }


    @Override
    protected void onStart() {
        super.onStart();
        switch (type) {
            case 1:
                navigationView.getMenu().removeItem(R.id.nav_student);
                navigationView.getMenu().removeItem(R.id.nav_par);
                break;
            case 2:
                navigationView.getMenu().removeItem(R.id.nav_par);
                break;
            case 3:
                navigationView.getMenu().removeItem(R.id.nav_student);
                navigationView.getMenu().removeItem(R.id.nav_homework);
                navigationView.getMenu().removeItem(R.id.nav_exam);
                navigationView.getMenu().removeItem(R.id.nav_classact);
                navigationView.getMenu().removeItem(R.id.nav_signin);
                navigationView.getMenu().removeItem(R.id.nav_remark);
                navigationView.getMenu().removeItem(R.id.nav_award);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            setFragment(NEWS_FRAGMENT);
        } else if (id == R.id.nav_award) {
            setFragment(AWARD_FRAGMENT);
        } else if (id == R.id.nav_student) {
            setFragment(STUDENT_FRAGMENT);
        } else if (id == R.id.nav_homework) {
            setFragment(HOMEWORK_FRAGMENT);
        } else if (id == R.id.nav_exam) {
            setFragment(EXAM_FRAGMENT);
        } else if (id == R.id.nav_signin) {
            setFragment(SIGNIN_FRAGMENT);
        } else if (id == R.id.nav_classact) {
            setFragment(CLASSACT_FRAGMENT);
        } else if (id == R.id.nav_remark) {
            setFragment(REMARK_FRAGMENT);
        } else if (id == R.id.nav_message) {
            setFragment(MESSAGE_FRAGMENT);
        } else if (id == R.id.nav_par) {
            setFragment(PARENT_FRAGMENT);
        } else if (id == R.id.nav_setting) {
            setFragment(SETTING_FRAGMENT);
        } else if (id == R.id.nav_logout) {
            getAction().logout(new ActionCallbackListener<Void>() {
                @Override
                public void onSuccess(Void data) {
                    SignInActivity.start(MainActivity.this);
                    finish();
                }

                @Override
                public void onFailure(Error error) {
                    Toast.makeText(MainActivity.this, "登出失败", Toast.LENGTH_SHORT).show();
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void initView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(this);

        user_nameView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);
        type_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_type);


    }

    @Override
    protected void initData() {
        type = getAction().get_type();
        user = (User) getIntent().getSerializableExtra("User");

        switch (type){
            case 1:
                type_name.setText("学生");
                break;
            case 2:
                type_name.setText("家长");
                break;
            case 3:
                type_name.setText("老师");
                break;
            default:
                type_name.setText("未知");
        }
        user_nameView.setText(user.getName());
    }

    protected void setFragment(int fragment_id) {
        if (current_fragment == fragment_id) return;
        Fragment fragment = getFragment(fragment_id);
        if (null == fragment) return;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (current_fragment == -1) {
            fragmentTransaction.add(R.id.frame_main_fragment, getFragment(fragment_id)).commit();
        } else if (fragment.isAdded()) {
            fragmentTransaction.hide(getFragment(current_fragment)).show(getFragment(fragment_id)).commit();
        } else {
            fragmentTransaction.hide(getFragment(current_fragment)).add(R.id.frame_main_fragment, getFragment(fragment_id)).commit();
        }

        current_fragment = fragment_id;
        setTitle();
    }

    //获取一个对应的fragment
    protected Fragment getFragment(int fragment_id) {
        Fragment fragment = fragments[fragment_id];
        if (null != fragment) return fragment;

        switch (fragment_id) {
            case NEWS_FRAGMENT:
                fragment = NewsListFragment.newInstance();
                break;
            case AWARD_FRAGMENT:
                fragment = AwardListFragment.newInstance();
                break;
            case STUDENT_FRAGMENT:
                fragment = StudentFragment.newInstance();
                break;
            case HOMEWORK_FRAGMENT:
                fragment = HomeworkListFragment.newInstance();
                break;
            case EXAM_FRAGMENT:
                fragment = ExamListFragment.newInstance();
                break;
            case SIGNIN_FRAGMENT:
                fragment = SignInListFragment.newInstance();
                break;
            case CLASSACT_FRAGMENT:
                fragment = ClassactListFragment.newInstance();
                break;
            case REMARK_FRAGMENT:
                fragment = RemarkListFragment.newInstance();
                break;
            case MESSAGE_FRAGMENT:
                fragment = MessagePagerFragment.newInstance();
                break;
            case PARENT_FRAGMENT:
                fragment = ParentListFragment.newInstance();
                break;
            case SETTING_FRAGMENT:
                fragment = SettingFragment.newInstance();
                break;
            default:
                return null;
        }
        fragments[fragment_id] = fragment;
        return fragment;

    }

    //修改当前的标题
    protected void setTitle() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setFragment(current_fragment);
    }
}
