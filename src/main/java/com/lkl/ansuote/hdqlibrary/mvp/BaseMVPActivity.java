package com.lkl.ansuote.hdqlibrary.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * MVP 架构框架类
 * Created by huangdongqiang on 15/05/2017.
 */
public abstract class BaseMVPActivity<V, P extends BasePresenter<V>> extends AppCompatActivity implements IBaseActivityView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView((V)this);
            mPresenter.initVariables(savedInstanceState, getIntent());
            mPresenter.onStart();
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

    /**
     * 初始化变量
     */
    //protected abstract void initVariables(Bundle savedInstanceState);
}
