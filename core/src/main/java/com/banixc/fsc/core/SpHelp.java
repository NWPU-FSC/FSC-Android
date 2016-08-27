package com.banixc.fsc.core;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

class SpHelp {

    private static final String DEFAULT_URL = "http://fsc.banixc.com/index.php/api";


    private Context context;
    private SharedPreferences urlSp;
    private SharedPreferences accountSp;

    SpHelp(Context context) {
        this.context = context;
        accountSp = getAccountSp();
        urlSp = getUrlSp();
    }

    private SharedPreferences getAccountSp() {
        return context.getSharedPreferences("account", MODE_PRIVATE);
    }

    private SharedPreferences getUrlSp() {
        return context.getSharedPreferences("url", MODE_PRIVATE);
    }

    void saveType(int type){
        SharedPreferences.Editor editor = accountSp.edit();
        editor.putInt("type", type);
        editor.apply();
    }

    void setUserName(String name){
        SharedPreferences.Editor editor = accountSp.edit();
        editor.putString("name",name);
        editor.apply();
    }

    String getUserName(){
        return accountSp.getString("name","");
    }


    public int getType(){
        return accountSp.getInt("type", -1);
    }

    void saveToken(String token) {
        SharedPreferences.Editor editor = accountSp.edit();
        editor.putString("token", token);
        editor.apply();
    }

    String getToken() {
        return accountSp.getString("token", "");
    }

    void setUrl(String url) {
        SharedPreferences.Editor editor = urlSp.edit();
        editor.putString("url",url).apply();
    }

    String getUrl(){
        return urlSp.getString("url","");
    }

}
