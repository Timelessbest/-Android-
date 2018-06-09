package com.cn;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 连接HTTP Web服务器类
 *
 * 
 * 
7 */
public class WebUtil {
	final String TAG = "WebUtil";
	final String CHARSET = "utf-8";

	/**
	 * 采用get方式连接服务器
	 * 
	 * @param url
	 *            地址
	 * @return String 响应
	 */
	public String executeGet(String url) {
		System.out.println(url);
		String result = "";
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpResponse response = httpClient.execute(httpGet); // 发起GET请求
			int status = response.getStatusLine().getStatusCode();
			if (status != HttpStatus.SC_OK) {// 如果获得的相应码不是成功的响应码
				Log.i(TAG, "resCode = "
						+ response.getStatusLine().getStatusCode());
				Log.i(TAG, "连接失败 ");
			} else {// 连接成功的话,获得相应的数据
				result = EntityUtils.toString(response.getEntity(), CHARSET);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "result = " + result);
		return result;
	}

	/**
	 * Post方式
	 * 
	 * @param url
	 *            http地址
	 * @param valuePair
	 *            参数键值对列
	 * @return String 响应的值
	 */
	public String executePost(String url,
			LinkedList<BasicNameValuePair> valuePair) {
		String result = "";
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			if (valuePair != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(valuePair, CHARSET));// 将参数编码后放入
			}
			HttpResponse response = httpClient.execute(httpPost); // 发起Post请求
			int status = response.getStatusLine().getStatusCode();
			if (status != HttpStatus.SC_OK) {// 如果获得的响应码不是成功的响应码
				Log.i(TAG, "resCode = "
						+ response.getStatusLine().getStatusCode());
				Log.i(TAG, "连接失败 ");
			} else {// 连接成功的话,获得响应的数据
				result = EntityUtils.toString(response.getEntity(), CHARSET);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "result = " + result);
		return result;
	}

	/**
	 * 获得网络图片
	 * 
	 * @param url
	 *            图片地址
	 * @return Bitmap 位图对象
	 */
	public Bitmap returnBitMap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
