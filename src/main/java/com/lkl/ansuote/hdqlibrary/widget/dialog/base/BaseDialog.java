package com.lkl.ansuote.hdqlibrary.widget.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ConvertUtils;
import com.lkl.ansuote.hdqlibrary.R;


/**
 * 对话框封装
 * Created by huangdongqiang on 10/08/2017.
 */
public class BaseDialog implements IBaseDialog{
    private Dialog mDialog;
    private BaseDialogParams mBaseDialogParams;

    public static class Builder {
        private BaseDialogParams mParams;

        public Builder(Context context, @LayoutRes int resource) {
            this(context, LayoutInflater.from(context).inflate(resource, null, false));
        }

        public Builder(Context context, View view) {
            this(context, view, R.style.dialog_transparent);
        }

        public Builder(Context context, View view, int themeResId) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            mParams = new BaseDialogParams();
            mParams.view = view;
            mParams.context = context;
            mParams.themeResId = themeResId;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mParams.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mParams.cancelable = cancelable;
            return this;
        }


        public Builder setPaddingLeft(int left) {
            mParams.paddingLeft = left;
            return this;
        }

        public Builder setGravity(int gravity) {
            mParams.gravity = gravity;
            return this;
        }

        /**
         * 设置距离底部的距离
         *  仅在设置 gravity 为 bottom 的时候有效
         * @param bottom
         * @return
         */
        public Builder setMarginBottom(int bottom) {
            mParams.marginBottom = bottom;
            return this;
        }

        /**
         * 宽度占据屏幕宽度的比例 （0～1）
         * @param widthScale
         * @return
         */
        public Builder setWidthScale(float widthScale) {
            mParams.widthScale = widthScale;
            return this;
        }

        public BaseDialog create() {
            final BaseDialog baseDialog = new BaseDialog(mParams);
            return baseDialog;
        }
    }

    private BaseDialog(BaseDialogParams params) {
        mBaseDialogParams = params;
        mDialog = new Dialog(params.context, params.themeResId);
        if (null != mDialog) {
            mDialog.setContentView(params.view);
            mDialog.setCanceledOnTouchOutside(params.canceledOnTouchOutside);
            mDialog.setCancelable(params.cancelable);
        }
    }

    /**
     * 设置对话框的属性，比如宽度，位置
     * @param dialog
     */
    public void setCustomAttributes(Dialog dialog) {
        if (null != dialog) {
            Window window = dialog.getWindow();
            if (null != window) {
                WindowManager.LayoutParams wlp = window.getAttributes();
                Display d = window.getWindowManager().getDefaultDisplay();
                DisplayMetrics dm = new DisplayMetrics();
                if (null != wlp && null != dm && null != d) {
                    d.getMetrics(dm);
                    //获取屏幕宽
                    int width = dm.widthPixels;
                    //设置新的宽度
                    wlp.width = (int)(width * mBaseDialogParams.widthScale);
                    //宽度按屏幕大小的百分比设置，这里我设置的是全屏显示
                    //wlp.gravity = Gravity.BOTTOM;
                    wlp.gravity = mBaseDialogParams.gravity;
                    if (wlp.gravity == Gravity.BOTTOM) {
                        //如果是底部显示，则距离底部的距离
                        wlp.y = ConvertUtils.dp2px(16);
                    }
                    window.setAttributes(wlp);
                }
            }
        }
    }

    @Override
    public void show() {
        if (null != mDialog) {
            mDialog.show();
            setCustomAttributes(mDialog);
        }
    }

    @Override
    public void dimiss() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onDestory() {
        dimiss();
        mDialog = null;
    }
}
