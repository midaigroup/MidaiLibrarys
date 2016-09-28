package com.lucio.library.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.lucio.mdlib.R;

@SuppressLint("NewApi")
public class SASingleChoiceDialog implements OnClickListener, OnShowListener, OnDismissListener {

    private Dialog dialog;
    private View root;
    private View[] layouts = new View[9];
    private TextView[] textViews = new TextView[9];
    private View content;
    private Animation contentAnimIn;
    private Animation rootAnimIn;

    public SASingleChoiceDialog(Context context, String[] items, OnItemClickListener onItemClickListener) {
        dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setOnShowListener(this);
        dialog.setOnDismissListener(this);
        contentAnimIn = AnimationUtils.loadAnimation(context, R.anim.slide_up_in);
        rootAnimIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        View view = LayoutInflater.from(context).inflate(R.layout.sasinglechoicedialog, null);
        dialog.setContentView(view);
        root = view.findViewById(R.id.root);
        root.setOnClickListener(this);
        content = view.findViewById(R.id.content);
        layouts[0] = view.findViewById(R.id.layout1);
        layouts[1] = view.findViewById(R.id.layout2);
        layouts[2] = view.findViewById(R.id.layout3);
        layouts[3] = view.findViewById(R.id.layout4);
        layouts[4] = view.findViewById(R.id.layout5);
        layouts[5] = view.findViewById(R.id.layout6);
        layouts[6] = view.findViewById(R.id.layout7);
        layouts[7] = view.findViewById(R.id.layout8);
        layouts[8] = view.findViewById(R.id.layout9);
        textViews[0] = (TextView) view.findViewById(R.id.text1);
        textViews[1] = (TextView) view.findViewById(R.id.text2);
        textViews[2] = (TextView) view.findViewById(R.id.text3);
        textViews[3] = (TextView) view.findViewById(R.id.text4);
        textViews[4] = (TextView) view.findViewById(R.id.text5);
        textViews[5] = (TextView) view.findViewById(R.id.text6);
        textViews[6] = (TextView) view.findViewById(R.id.text7);
        textViews[7] = (TextView) view.findViewById(R.id.text8);
        textViews[8] = (TextView) view.findViewById(R.id.text9);
        for (View layout : layouts) {
            layout.setOnClickListener(this);
        }
        View btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        for (int i = 0; i < items.length; i++) {
            layouts[i].setVisibility(View.VISIBLE);
            textViews[i].setText(items[i]);
        }
        this.onItemClickListener = onItemClickListener;
    }

    public void show() {
        dialog.show();
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(int position, String item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnCancel) {
            dialog.dismiss();
        } else if (id == R.id.root) {
            dialog.dismiss();
        } else if (onItemClickListener != null) {
            for (int i = 0; i < layouts.length; i++) {
                View layout = layouts[i];
                if (id == layout.getId()) {
                    onItemClickListener.onItemClick(i, textViews[i].getText().toString());
                    break;
                }
            }
            dialog.dismiss();
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        content.startAnimation(contentAnimIn);
        root.startAnimation(rootAnimIn);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }

}
