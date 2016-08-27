package com.banixc.fsc.core;

import com.banixc.fsc.model.Error;

public interface ActionCallbackListener<T> {
    /**
     * 成功时调用
     *
     * @param data 返回的数据
     */
    void onSuccess(T data);

    /**
     * 失败时调用
     *
     * @param error 错误
     */
    void onFailure(Error error);
}