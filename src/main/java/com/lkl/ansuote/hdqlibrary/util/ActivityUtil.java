package com.lkl.ansuote.hdqlibrary.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by huangdongqiang on 27/09/2017.
 */
public class ActivityUtil {
    /**
     * 开启手机的设置界面
     * @param context
     */
    public static void startSettingsActivity(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
