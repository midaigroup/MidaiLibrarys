package com.lucio.library.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author zhaoyi
 * 公用的工具类方法
 *
 */
public class Util {
 
	@SuppressWarnings("unused")
	private static final String APP_KEY = "nisdhfe$@kjdfje%djf&*$ksdf";

	// token生成机制
	// public static String getToken(RequestParams params) {
	// StringBuilder builder = new StringBuilder();
	// builder.append(APP_KEY);
	// Object[] keys = params.keySet().toArray();
	// Arrays.sort(keys);
	// for (Object key : keys) {
	// builder.append(key + params.get((String) key));
	// }
	// builder.append(APP_KEY);
	// try {
	// return
	// MD5.getMessageDigest(builder.toString().getBytes("UTF-8")).toUpperCase();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return "";
	// }



	// EditText 保留小数点后两位
	public static void setPricePoint(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.toString().contains(".")) {
					if (s.length() - 1 - s.toString().indexOf(".") > 2) {
						s = s.toString().subSequence(0,
								s.toString().indexOf(".") + 3);
						editText.setText(s);
						editText.setSelection(s.length());
					}
				}
				if (s.toString().trim().substring(0).equals(".")) {
					s = "0" + s;
					editText.setText(s);
					editText.setSelection(2);
				}
				if (s.toString().startsWith("0")
						&& s.toString().trim().length() > 1) {
					if (!s.toString().substring(1, 2).equals(".")) {
						editText.setText(s.subSequence(0, 1));
						editText.setSelection(1);
						return;
					}
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});

	}

//	// 获取本地IP
//	public static String getlocalIp(Context context) {
//		if (isNetConnected(context)) {
//			if (isWifi(context)) {
//				WifiManager wifiManager = (WifiManager) context
//						.getSystemService(Context.WIFI_SERVICE);
//				WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//				int ipAddress = wifiInfo.getIpAddress();
//				if (ipAddress != 0)
//					return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
//							+ (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
//			} else {
//				try {
//					for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
//						NetworkInterface intf = en.nextElement();
//						for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
//							InetAddress inetAddress = enumIpAddr.nextElement();
//							if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
//								//if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet6Address) {
//								return inetAddress.getHostAddress().toString();
//							}
//						}
//					}
//				} catch (Exception e) {
//				}
//			}
//		}
//		return null;
//	}


	public static String getlocalIp(Context context)
	{
		String IP = "";
		JSONObject data;
		try
		{
			String address = "http://ip.taobao.com/service/getIpInfo2.php?ip=myip";
			URL url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setUseCaches(false);

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				InputStream in = connection.getInputStream();

				// 将流转化为字符串
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String tmpString = "";
				StringBuilder retJSON = new StringBuilder();
				while ((tmpString = reader.readLine()) != null)
				{
					retJSON.append(tmpString + "\n");
				}

				JSONObject jsonObject = new JSONObject(retJSON.toString());
				String code = jsonObject.getString("code");
				if (code.equals("0"))
				{
					data = jsonObject.getJSONObject("data");
					IP = data.getString("ip");

//					Log.e("提示", "您的IP地址是：" + IP);
					context.getSharedPreferences("config", 0).edit().putString("IP", "" + IP).commit();
				}
				else
				{
					IP = "";
//					Log.e("提示", "IP接口异常，无法获取IP地址！");
				}
			}
			else
			{
				IP = "";
//				Log.e("提示", "网络连接异常，无法获取IP地址！");
			}
		}
		catch (Exception e)
		{
			IP = "";
//			Log.e("提示", "获取IP地址时出现异常，异常信息是：" + e.toString());
		}

		return IP;
	}

	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifi(Context context) {
		if (isNetConnected(context)) {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cm == null) {
				return false;
			}
			return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
		}
		return false;
	}

	/**
	 * 判断网络是否连接
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetConnected(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (null != connectivity) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}
}
