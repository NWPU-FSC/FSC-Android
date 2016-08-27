package com.banixc.fsc.model;

/**
 * Created by Banixc on 2016/8/25.
 */

public class Connect {

    public static final String TOKEN = "NWPU-FSC";


    /**
     * token : NWPU-FSC
     * message : Hello world
     */

    private String token;
    private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean is_token_correct(){
        return TOKEN.equals(token);
    }
}
