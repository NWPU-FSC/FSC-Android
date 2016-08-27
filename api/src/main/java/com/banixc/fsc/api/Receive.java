package com.banixc.fsc.api;

import com.banixc.fsc.model.Error;

public class Receive<T> {
    private Error error;
    private T result;

    public Receive(T result, Error error) {
        this.error = error;
        this.result = result;
    }

    public boolean success() {
        return null != result;
    }


    public Error getError() {
        return error;
    }

    public T getResult() {
        return result;
    }
}
