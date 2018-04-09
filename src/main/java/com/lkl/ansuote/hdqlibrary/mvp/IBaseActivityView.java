package com.lkl.ansuote.hdqlibrary.mvp;

/**
 * MVP 架构框架 界面基类接口
 *
 * @author huangdongqiang
 * @date 2018/3/30
 */
public interface IBaseActivityView {

    /**
     * 显示错误信息
     * @param errorMsg
     */
    void showErrorMsg(String errorMsg);

    /**
     * 结束当前 Activity
     */
    void finish();
}
