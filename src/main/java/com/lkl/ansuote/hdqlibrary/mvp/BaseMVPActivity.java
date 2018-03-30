package com.lkl.ansuote.hdqlibrary.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lkl.ansuote.hdqlibrary.base.BaseActivity;

/**
 * MVP 架构框架类
 *
 * @author huangdongqiang
 * @date 2018/3/30
 */
public abstract class BaseMVPActivity<V, P extends BasePresenter<V>> extends BaseActivity implements IBaseActivityView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView((V)this);
            mPresenter.inject();
            mPresenter.initVariables(savedInstanceState, getIntent());
            mPresenter.onCreate();
        }

    }

    @Override
    protected void onDestroy() {
        if (null != mPresenter) {
            mPresenter.detachView();
        }

        super.onDestroy();
    }

    /**
     * 创建 Presenter
     * @return
     */
    protected abstract P createPresenter();

    /**
     * 初始化界面
     */
    protected abstract void initView();
}
