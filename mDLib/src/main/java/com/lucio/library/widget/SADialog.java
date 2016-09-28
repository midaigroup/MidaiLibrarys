package com.lucio.library.widget;

import java.util.HashMap;
import java.util.Map;

import com.lucio.mdlib.R;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SADialog implements OnClickListener {

    private Dialog dialog;
    private View contentView;

    public SADialog(Context context) {
        dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.saalertdialog, null));
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (onCancelListener != null) {
                    onCancelListener.onCancel();
                }
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (onDismissListener != null) {
                    onDismissListener.onDismiss();
                }
            }
        });
    }

    public Context getContext() {
        return dialog.getContext();
    }

    public SADialog setContentView(View contentView) {
        this.contentView = contentView;
        dialog.setContentView(contentView);
        return this;
    }

    public SADialog setTitle(int resId) {
        return setTitle(getContext().getString(resId));
    }

    public SADialog setTitle(String title) {
        View textView = contentView.findViewById(R.id.title);
        if (textView != null && textView instanceof TextView) {
            ((TextView) textView).setText(title);
        }
        return this;
    }

    public SADialog setMessage(int resId) {
        return setMessage(getContext().getString(resId));
    }

    public SADialog setMessage(String message) {
        View textView = contentView.findViewById(R.id.message);
        if (textView != null && textView instanceof TextView) {
            ((TextView) textView).setText(message);
        }
        return this;
    }

    public SADialog setButton(int resId) {
        return setButton(getContext().getString(resId));
    }

    public SADialog setButton(String text) {
        if (text == null) {
            text = "";
        }
        View button = contentView.findViewById(R.id.button);
        if (button != null) {
            button.setVisibility(View.VISIBLE);
            if (button instanceof Button) {
                ((Button) button).setText(text);
            } else if (button instanceof TextView) {
                ((TextView) button).setText(text);
            }
        }
        return this;
    }

    public SADialog setButton2(int resId) {
        return setButton2(getContext().getString(resId));
    }

    public SADialog setButton2(String text) {
        if (text == null) {
            text = "";
        }
        View button2 = contentView.findViewById(R.id.button2);
        if (button2 != null) {
            button2.setVisibility(View.VISIBLE);
            if (button2 instanceof Button) {
                ((Button) button2).setText(text);
            } else if (button2 instanceof TextView) {
                ((TextView) button2).setText(text);
            }
        }
        return this;
    }

    public SADialog setButton3(int resId) {
        return setButton3(getContext().getString(resId));
    }

    public SADialog setButton3(String text) {
        if (text == null) {
            text = "";
        }
        View button3 = contentView.findViewById(R.id.button3);
        if (button3 != null) {
            button3.setVisibility(View.VISIBLE);
            if (button3 instanceof Button) {
                ((Button) button3).setText(text);
            } else if (button3 instanceof TextView) {
                ((TextView) button3).setText(text);
            }
        }
        return this;
    }

    public SADialog show() {
        dialog.show();
        return this;
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            if (onButtonClickListener != null) {
                onButtonClickListener.onClick(this);
            }
            dismiss();
        } else if (v.getId() == R.id.button2) {
            if (onButton2ClickListener != null) {
                onButton2ClickListener.onClick(this);
            }
            dismiss();
        } else if (v.getId() == R.id.button3) {
            if (onButton3ClickListener != null) {
                onButton3ClickListener.onClick(this);
            }
            dismiss();
        }
    }

    private OnButtonClickListener onButtonClickListener;
    private OnButton2ClickListener onButton2ClickListener;
    private OnButton3ClickListener onButton3ClickListener;
    private OnCancelListener onCancelListener;
    private OnDismissListener onDismissListener;

    public SADialog setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
        View button = contentView.findViewById(R.id.button);
        if (button != null) {
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(this);
        }
        return this;
    }

    public SADialog setOnButton2ClickListener(OnButton2ClickListener onButton2ClickListener) {
        this.onButton2ClickListener = onButton2ClickListener;
        View button2 = contentView.findViewById(R.id.button2);
        if (button2 != null) {
            button2.setVisibility(View.VISIBLE);
            button2.setOnClickListener(this);
        }
        return this;
    }

    public SADialog setOnButton3ClickListener(OnButton3ClickListener onButton3ClickListener) {
        this.onButton3ClickListener = onButton3ClickListener;
        View button3 = contentView.findViewById(R.id.button3);
        if (button3 != null) {
            button3.setVisibility(View.VISIBLE);
            button3.setOnClickListener(this);
        }
        return this;
    }

    public interface OnButtonClickListener {
        public void onClick(SADialog dialog);
    }

    public interface OnButton2ClickListener {
        public void onClick(SADialog dialog);
    }

    public interface OnButton3ClickListener {
        public void onClick(SADialog dialog);
    }

    public interface OnCancelListener {
        public void onCancel();
    }

    public interface OnDismissListener {
        public void onDismiss();
    }

    public Dialog getDialog() {
        return dialog;
    }

    public SADialog setCancelable(boolean flag) {
        dialog.setCancelable(flag);
        return this;
    }

    public SADialog setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    public SADialog setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    private Map<String, Object> mapObject = new HashMap<String, Object>();

    public void put(String key, Object obj) {
        mapObject.put(key, obj);
    }

    public Object get(String key) {
        return mapObject.get(key);
    }

}
