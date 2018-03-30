package com.lkl.ansuote.hdqlibrary.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.Logger;


/**
 * 跟踪全部 Activity 的生命周期
 *
 * @author huangdongqiang
 * @date 2018/3/30
 */
public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    private Application mApplication;
    private static AppStatusTracker sAppstatusTracker;
    /**
     * 应用是否处于前台
     */
    private boolean mIsForground;

    /**
     * 前台界面计数
     */
    private int mForgroundCount;

    private AppStatusTracker(Application application) {
        mApplication = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    /**
     * Application初始化的时候操作
     * @param application
     */
    public static void init(Application application) {
        sAppstatusTracker = new AppStatusTracker(application);

    }

    public static AppStatusTracker getInstance() {
        return sAppstatusTracker;
    }

    /**
     * 应用是否处于前台
     * @return
     */
    public boolean isForground() {
        return mIsForground;
    }

    private void setForground(boolean forground) {
        mIsForground = forground;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Logger.i("onActivityCreated: " + activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

        Logger.i("onActivityStarted: " + activity);
        mForgroundCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Logger.i("onActivityResumed: " + activity);
        setForground(true);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Logger.i( "onActivityPaused: " + activity);
        mForgroundCount--;
        if (mForgroundCount == 0) {
            mIsForground = false;
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Logger.i("onActivityStopped: " + activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Logger.i("onActivitySaveInstanceState: ");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Logger.i("onActivityDestroyed: ");
    }
}
