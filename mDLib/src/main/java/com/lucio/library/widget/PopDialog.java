package com.lucio.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucio.mdlib.R;

/**
 * 自定义的Pop弹窗，可设置自动隐藏
 * Created by zhaoyi on 2016/3/7.
 */
public class PopDialog extends Dialog {

    private Context context;
    private String content;
    private boolean state;
    private TextView tv;
    private ImageView iv;

    /**
     * 自定义简单弹窗的构造函数
     * @param context 上下文对象
     * @param content 弹窗内容
     * @param state 弹窗所指示的状态，false为失败状态
     */
    public PopDialog(Context context, String content, boolean state) {
        super(context, R.style.PopDialog);
        this.context=context;
        this.content=content;
        this.state = state;

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.fragment_popup, null);
        tv = (TextView) view.findViewById(R.id.tv_popfragment_tip);
        tv.setText(content);
        iv = (ImageView) view.findViewById(R.id.iv_popfragment_state);
        if (state) {
            iv.setImageResource(R.drawable.iv_state_right);

        } else {
            iv.setImageResource(R.drawable.iv_state_error);
        }
        //dialog添加视图
        setContentView(view);
    }

}
