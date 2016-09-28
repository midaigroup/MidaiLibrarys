package com.lucio.library.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 键盘控制
 *
 * @author zhaoyi
 */
public class KeyboardUtil {

    /**
     * 如果输入法在窗口上已经显示，则隐藏，反之则显示
     *
     * @param mContext
     */
    public static void toggleSoftInput(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制显示软键盘
     *
     * @param mContext
     * @param root     为接受软键盘输入的视图
     */
    public static void showSoftKeyboard(Context mContext, View root) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (root != null) {
            imm.showSoftInput(root, InputMethodManager.SHOW_FORCED);
        }

    }

    /**
     * 隐藏软键盘
     *
     * @param mContext
     * @param root     为接受软键盘输入的视图
     */
    public static void hideSoftKeyboard(Activity mContext, View root) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (root != null) {
            imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
        } else if (mContext.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(mContext.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            if (isOpenSoftInput(mContext)) {
                toggleSoftInput(mContext);
            }
        }
    }

    /**
     * 获取输入法的显示状态
     *
     * @param mContext
     * @return true==show，false=hide
     */
    public static boolean isOpenSoftInput(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isAcceptingText();
    }

    /**
     * 软键盘显示时，将页面root移动到指定控件anchor的上沿位置
     *
     * @param root
     * @param anchor
     */
    public static void openSoftKeyboard(View root, View anchor) {
        int[] location = new int[2];
        anchor.getLocationInWindow(location);
        if (anchor != null)
            root.scrollTo(0, -location[1]);
    }

    /**
     * 软键盘隐藏时，将页面root移回原位
     *
     * @param root
     * @param anchor
     */
    public static void closeSoftKeyboard(View root, View anchor) {
        if (anchor != null)
            root.scrollTo(0, 0);
    }

    /**
     * 获取软键盘的高度
     */
    private static int keyBoardHeight = -1;
    public static int getSoftInputHeight(final LinearLayout myLayout) {

        myLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        myLayout.getWindowVisibleDisplayFrame(r);

                        int screenHeight = myLayout.getRootView().getHeight();
                        keyBoardHeight = screenHeight - (r.bottom - r.top);
                    }
                });

        return keyBoardHeight;
    }
}
