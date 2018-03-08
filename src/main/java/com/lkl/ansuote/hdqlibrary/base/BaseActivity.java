package com.lkl.ansuote.hdqlibrary.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by huangdongqiang on 23/02/2018.
 */
public class BaseActivity extends AppCompatActivity {

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
