package com.lkl.ansuote.hdqlibrary.widget.dialog.base;

import android.content.Context;
import android.view.View;

/**
 * Created by huangdongqiang on 10/08/2017.
 */
public class BaseDialogParams {
    public Context context;
    public int paddingLeft;
    public View view;
    public int themeResId;
    public boolean canceledOnTouchOutside = true;
    public boolean cancelable = true;
    /**
     * 宽度占据屏幕宽度的比例 （0～1）
     */
    public float widthScale = 0.8f;
    /**
     * 对齐方向 Gravity.TOP Gravity.BOTTOM
     */
    public int gravity;

    /**
     * 具体底部的距离 (如果是底部显示，则距离底部的距离)
     */
    public int marginBottom;
}
