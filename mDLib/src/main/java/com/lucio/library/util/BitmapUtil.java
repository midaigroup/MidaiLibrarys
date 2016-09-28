package com.lucio.library.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;

/**
 * 图片处理类
 * @author Roy
 * @version 1.0
 *
 */
public class BitmapUtil {
	
	/**
	 * 根据路径和文件名保存相应的位图到本地
	 * @param context 上下文对象
	 * @param mBitmap 指定的位图
	 * @param picPath 指定的路径
	 * @param picName 指定的文件名
	 */
	public static void saveShareBitmap(Context context, Bitmap mBitmap, String picPath, String picName) {
		File file = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			file = new File(Environment.getExternalStorageDirectory(), picPath);

		} else {
			file = new File(context.getCacheDir().getAbsolutePath());
		}

		if (!file.exists()) {
			file.mkdir();
		}

		File logofile = new File(file.getAbsolutePath()
				+ File.separator + picName);
		try {
			logofile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		LogUtil.e("是不是文件==", logofile.isFile() + "");
		LogUtil.e("文件全路径==", logofile.toString());
		try {
			if(logofile.isFile()){
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(logofile));

				if(mBitmap != null){
					mBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				}
				if(bos != null){
					bos.flush();
					bos.close();
				}
				if (mBitmap != null && !mBitmap.isRecycled()) {
					mBitmap.recycle();
					mBitmap = null;
				}
			}
		} catch (IOException e) {
		}
	}
	
	
	/**
	 * 缩放位图
	 * @param context 上下文对象
	 * @param resId 资源ID
	 * @param width 要缩放到的固定宽度
	 * @param height 要缩放到的固定高度
	 * @return 位图对象
	 */
	public static Bitmap zoomBitmap(Context context,int resId, int width, int height) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
		int bitmapWidth = bitmap.getWidth();//图片宽度
		int bitmapHeight = bitmap.getHeight();//图片高度
		Matrix matrix = new Matrix(); // 创建操作图片用的Matrix对象
		float scaleWidth = ((float) width / bitmapWidth); // 计算缩放比例
		float scaleHeight = ((float) height / bitmapHeight);
		matrix.postScale(scaleWidth, scaleHeight); // 设置缩放比例
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth,
				bitmapHeight, matrix, true); // 建立新的bitmap，其内容是对原bitmap的缩放后的图
		return newbmp;
		
	}
	

	/*
	 *  等比例缩放图片
	 */
	public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
				true);
		return newbm;
	}
	
	
	/**
	 * 从URI中获取路径信息
	 */
	public static String getPathFromUri(Context context,Uri uri){
		String res = null;
		Cursor cursor = null;
		try {
			String[] proj = {MediaColumns.DATA};
			cursor = context.getContentResolver().query(uri, proj, null, null, null);
			if (cursor.moveToFirst()) {
				int columnIndex = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
				res = cursor.getString(columnIndex);
			}
		} catch (Exception e) {
			res = null;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return res;
	}
	
	
	/**
	 * 解码位图
	 */
	public static Bitmap decodeBitmap(Context context, File file, int width,
			int height) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		if (options.outWidth < 1 || options.outHeight < 1) {
			return null;
		}
		options.inSampleSize = calculateInSampleSize(options, bitmap.getWidth(), bitmap.getHeight());

		options.inJustDecodeBounds = false;
		options.inPreferredConfig = Config.RGB_565;
		options.inPurgeable = true;
		options.inInputShareable = true;

		try {
			return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		} catch (OutOfMemoryError e) {
			return null;
		}

	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		int inSampleSize = 1;
		final int height = options.outHeight;
		final int width = options.outWidth;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
			final float totalPixels = width * height;
			final float totalReqPixelsCap = reqWidth * reqHeight * 3;

			while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
				inSampleSize++;
			}
		}
		return inSampleSize;
	}
	
	public static Bitmap getBitmap(Context context,int resId) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.ARGB_8888;
		options.inPurgeable = true;// 允许可清除
		options.inInputShareable = true;// 以上options的两个属性必须联合使用才会有效果
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, options);
	}

}
