package com.lucio.library.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lucio.library.widget.SAToast;
import com.lucio.mdlib.R;

/**
 * 应用更新管理工具
 * 
 * @author zhaoyi
 *
 */
public class VersionUtil {

	private Context context;

	//存储的末级目录
	private static String PATH = "Midai";

	//更新回调
	private UpdateCallBack callBack;

	//更新的地址
	private String updateUrl;

	//更新中断的标记
	private boolean canCancel;
	
	//安全的协议
	private String https = "https://";
	//一般的协议
	private String http = "http://";

	// 下载中
	private static final int DOWNLOADING = 1;
	// 下载结束
	private static final int DOWNLOAD_FINISH = 2;
	
	// 下载保存路径
	private String mSavePath;
	//下载文件名称
	private String mSaveName = PATH;
	
	// 记录进度条进度
	private int progress_count;
	// 是否取消更新
	private boolean cancelUpdate = false;

	// 更新进度条
	private ProgressBar mPb_progress;
	private TextView mTv_progress;
	private TextView mTv_cancel;
	private Dialog mDownloadDialog;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 正在下载
			case DOWNLOADING:
				// 设置进度条位置
				mPb_progress.setProgress(progress_count);
				mTv_progress.setText(progress_count + "%");
				break;
			case DOWNLOAD_FINISH:
				SAToast.makeText(context, "下载完成").show();
				// 安装文件
				installApk();
				break;
			default:
				break;
			}
		};
	};

	@SuppressWarnings("static-access")
	public VersionUtil(Context context, String path, String updateUrl, boolean canCancel) {
		this.context = context;
		this.PATH = path;
		this.canCancel = canCancel;
		/*
		 * 因为服务器协议的原因，URL如果用的是HTTP协议，则获取不到服务器apk文件，
		 * 只能使用HTTPS协议。
		 */
		this.updateUrl = updateUrl.replace(http, https);
		
		LogUtil.i("URL", updateUrl);
		
		//URL健壮性判断，防止截取的名称不规范
		if (updateUrl.startsWith(https) && updateUrl.contains(".apk")) {
			mSaveName = updateUrl.substring(updateUrl.lastIndexOf("/") + 1);
			LogUtil.i("SAVENAME: ", "==" + mSaveName);
		}
	}
	 

	public VersionUtil(Context context, String updateUrl, boolean canCancel) {
		this(context, PATH, updateUrl, canCancel);
	}

	public UpdateCallBack getCallBack() {
		return callBack;
	}

	public void setCallBack(UpdateCallBack callBack) {
		this.callBack = callBack;
	}

	/**
	 * 下载apk文件
	 */
	private void downloadApk() {
		// 启动新线程下载软件
		new downloadApkThread().start();
	}

	/**
	 * 显示软件下载对话框
	 */
	public void showDownloadDialog() {
		// 构造软件下载对话框
		AlertDialog.Builder builder = new Builder(context);
		// 给下载对话框增加进度条
		final LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.softupdate_progress, null);
		mTv_progress = (TextView) v.findViewById(R.id.progress_txt);
		mPb_progress = (ProgressBar) v.findViewById(R.id.update_progress);
		mTv_cancel = (TextView) v.findViewById(R.id.update_cancel);
		builder.setView(v);

		builder.setCancelable(false);
		if (canCancel) {
			// 取消下载
			mTv_cancel.setVisibility(View.VISIBLE);
			mTv_cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mDownloadDialog.dismiss();
					cancelUpdate = true;
					if (callBack != null && canCancel) {
						callBack.onCancel(canCancel);
					}
				}
			});
		}

		mDownloadDialog = builder.create();
		mDownloadDialog.show();

		// 开始文件
		downloadApk();
	}

	/**
	 * 下载文件线程
	 * 
	 */
	private class downloadApkThread extends Thread {
		@Override
		public void run() {
			LogUtil.i("XIAZAI:", "==" + "下载文件线程");
			Looper.prepare();
			try {
				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// 获得存储卡的路径
					String sdpath = Environment.getExternalStorageDirectory()
							+ File.separator;
					mSavePath = sdpath + PATH;
					URL url = new URL(updateUrl);
					LogUtil.i("XIAZAI:", "==" + updateUrl);
					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					
					LogUtil.i("XIAZAI:", length + "==");
					
					// 创建输入流
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();
					}
					File apkFile = new File(mSavePath, mSaveName);

					if (apkFile.exists()) {
						apkFile.delete();
					}

					FileOutputStream fos = new FileOutputStream(apkFile);
//					FileOutputStream fos = context.openFileOutput(apkFile.getName(),
//                            Context.MODE_WORLD_READABLE);

					int count = 0;
					// 缓存
					byte buf[] = new byte[1024 * 64];
					// 写入到文件中
					do {
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress_count = (int) (((float) count / length) * 100);
						// 更新进度
						mHandler.sendEmptyMessage(DOWNLOADING);
						if (numread <= 0) {
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					fos.flush();  
					fos.close();
					is.close();
				} else {
					if (callBack != null) {
						callBack.onStorageError(canCancel);
					}
				}
			} catch (MalformedURLException e) {
				if (callBack != null) {
					callBack.onDownError(canCancel);
				}
			} catch (IOException e) {
				if (callBack != null) {
					callBack.onDownError(canCancel);
				}
			}
			// 取消下载对话框显示
			mDownloadDialog.dismiss();
			Looper.loop();
		}
	};

	/**
	 * 安装APK文件
	 */
	private void installApk() {
		File apkfile = new File(mSavePath, mSaveName);
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		// 设置Activity可处理文件的类型
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
		if (callBack != null) {
			callBack.onInstall();
		}
	}
	
	public interface UpdateCallBack {
		// 取消下载
		void onCancel(boolean cancel);

		// 存储异常
		void onStorageError(boolean cancel);

		// 网络异常
		void onDownError(boolean cancel);

		// 开始安装
		void onInstall();
	}
}
