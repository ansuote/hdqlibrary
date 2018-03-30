package com.lkl.ansuote.hdqlibrary.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

/**
 * Activity 基类
 * @author huangdongqiang
 * @date 2018/3/30
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.i("onRestart -- " + this.toString());
    }

    /**
     * 开启只定界面
     * @param context
     * @param cls
     */
    public static void actionStrat(Context context, Class cls) {
        if (null != context) {
            Intent intent = new Intent(context, cls);
            if (null != intent) {
                context.startActivity(intent);
            }
        }
    }
}
