package com.banixc.fsc.fscroid.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.banixc.fsc.core.Action;
import com.banixc.fsc.core.ActionImplement;

public abstract class BaseActivity extends AppCompatActivity {
    //基类 所有Activity都要继承此类 以便初始化


    private static Action action;

    protected abstract void initView();

    protected abstract void initData();

    protected Action getAction(){
        if(action!=null) return action;
        action = new ActionImplement(this);
        return action;
    }


}
