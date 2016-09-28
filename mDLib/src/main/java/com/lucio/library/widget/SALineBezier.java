package com.lucio.library.widget;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;

public class SALineBezier extends View{

	private Paint mPaint;
	
	public SALineBezier(Context context) {
		super(context);
		
		mPaint = new Paint();  
        mPaint.setAntiAlias(true);  
        mPaint.setDither(true);  
        mPaint.setStyle(Paint.Style.STROKE);  
        mPaint.setStrokeJoin(Paint.Join.ROUND);  
        mPaint.setStrokeCap(Paint.Cap.ROUND);  
        mPaint.setStrokeWidth(10); 
	}
	
}
