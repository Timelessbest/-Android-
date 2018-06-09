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
 * ����HTTP Web��������
 *
 * 
 * 
7 */
public class WebUtil {
	final String TAG = "WebUtil";
	final String CHARSET = "utf-8";

	/**
	 * ����get��ʽ���ӷ�����
	 * 
	 * @param url
	 *            ��ַ
	 * @return String ��Ӧ
	 */
	public String executeGet(String url) {
		System.out.println(url);
		String result = "";
		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpResponse response = httpClient.execute(httpGet); // ����GET����
			int status = response.getStatusLine().getStatusCode();
			if (status != HttpStatus.SC_OK) {// �����õ���Ӧ�벻�ǳɹ�����Ӧ��
				Log.i(TAG, "resCode = "
						+ response.getStatusLine().getStatusCode());
				Log.i(TAG, "����ʧ�� ");
			} else {// ���ӳɹ��Ļ�,�����Ӧ������
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
	 * Post��ʽ
	 * 
	 * @param url
	 *            http��ַ
	 * @param valuePair
	 *            ������ֵ����
	 * @return String ��Ӧ��ֵ
	 */
	public String executePost(String url,
			LinkedList<BasicNameValuePair> valuePair) {
		String result = "";
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			if (valuePair != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(valuePair, CHARSET));// ��������������
			}
			HttpResponse response = httpClient.execute(httpPost); // ����Post����
			int status = response.getStatusLine().getStatusCode();
			if (status != HttpStatus.SC_OK) {// �����õ���Ӧ�벻�ǳɹ�����Ӧ��
				Log.i(TAG, "resCode = "
						+ response.getStatusLine().getStatusCode());
				Log.i(TAG, "����ʧ�� ");
			} else {// ���ӳɹ��Ļ�,�����Ӧ������
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
	 * �������ͼƬ
	 * 
	 * @param url
	 *            ͼƬ��ַ
	 * @return Bitmap λͼ����
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
