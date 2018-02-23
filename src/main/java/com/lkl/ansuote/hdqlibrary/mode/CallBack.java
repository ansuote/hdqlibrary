package com.lkl.ansuote.hdqlibrary.mode;

/**
 * 用于数据处理完的回调
 */
public class CallBack<T> {

    public void onStart() {
    }

    public void onSuccess(T t) {
    }

    public void onFailure(String msg, Exception e) {
    }
}
