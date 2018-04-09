package com.lkl.ansuote.hdqlibrary.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lkl.ansuote.hdqlibrary.base.BaseActivity;

import javax.inject.Inject;


/**
 * MVP 架构框架类
 *
 * @author huangdongqiang
 * @date 2018/3/30
 */
public abstract class BaseMVPActivity<V, P extends BasePresenter<V>, C> extends BaseActivity implements IBaseActivityView {
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initInject();
        if (null == mPresenter) {
            throw new NullPointerException("Cannot inject presenter into a null reference");
        }

        mPresenter.attachView((V)this);
        mPresenter.initVariables(savedInstanceState, getIntent());
        mPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        if (null != mPresenter) {
            mPresenter.detachView();
        }

        super.onDestroy();
    }

    /**
     * 依赖注入当前的 Activity 需要的变量，必须注入Presenter，否则会抛出异常
     */
    protected abstract void initInject();

    /**
     * 初始化界面 设置 setContentView
     */
    protected abstract void initView();

    /**
     * 获取项目相关的 ActivityComponent
     * @return
     */
    protected abstract C getActivityComponent();
}
