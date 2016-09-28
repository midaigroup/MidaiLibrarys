package com.lucio.library.widget;

import com.lucio.mdlib.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


public class SALoadingDialog extends Dialog {
	
	private static SALoadingDialog loadingDialog;
	
	public static SALoadingDialog getIntence(Context context){
//		if (loadingDialog == null) {
			return new SALoadingDialog(context);
//		}
//		return loadingDialog;
	}

    public SALoadingDialog(Context context) {
        super(context, R.style.SALoadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saloadingdialog);
        setCanceledOnTouchOutside(false);
    }
}
