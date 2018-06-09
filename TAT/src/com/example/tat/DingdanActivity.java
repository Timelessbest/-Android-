package com.example.tat;
/****
 * 
 */
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.Hotel;
import com.cn.WebUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class DingdanActivity extends Activity {

	String result;
	Handler handler;
	List<Map<String, Object>> list;
	String typeName = "";
	String type = "";
	Bitmap bitmap;
	SimpleAdapter sadapter;

	private ListView list_dingdanview;
	private TextView username_dingdan;

	Global global;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dingdan);
		global = ((Global) getApplicationContext());
		list_dingdanview = (ListView) findViewById(R.id.list_dingdanview);
		username_dingdan = (TextView) findViewById(R.id.username_dingdan);
	
		// ���û�����ʾ�ڶ���ҳ��
//		typeName=global.username;

		
		typeName = global.getUsername().toString();
		username_dingdan.setText(typeName);
		//
		// temptxt = (TextView) findViewById(R.id.temptxt);
		// ��ѯ���� ������Ϣ��ʾ
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x11) {
					sadapter = new SimpleAdapter(DingdanActivity.this, getData(), R.layout.list_jindian,
							new String[] { "name", "content", "telephone", "price", "image" },
							new int[] { R.id.namelist1, R.id.contentlist1, R.id.telephonelist1, R.id.pricelist1,
									R.id.inmagelist1 });
					list_dingdanview.setAdapter(sadapter);
				}
				if (msg.what == 0x12) {
					// ��������������ʾ��image
					sadapter.setViewBinder(new ViewBinder() {

						public boolean setViewValue(View view, Object data, String textRepresentation) {
							// �ж��Ƿ�Ϊ����Ҫ����Ķ���
							if (view instanceof ImageView && data instanceof Bitmap) {
								ImageView iv = (ImageView) view;

								iv.setImageBitmap((Bitmap) data);
								return true;
							} else
								return false;
						}
					});
					list_dingdanview.setAdapter(sadapter);
					// �ٴμ���������
				}

			}

		};
		new MyThreadtow().start();

	}
	public List<Map<String, Object>> getData() {
		list = new ArrayList<Map<String, Object>>();

		Gson json = new Gson();
		// ����ȡ��JSon����ת����List
		Type listType = new TypeToken<List<Hotel>>() {
		}.getType();
		List<Hotel> list1 = json.fromJson(result, listType);

		for (int i = 0; i < list1.size(); i++) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			// 6������
			Hotel hotel = list1.get(i);
			map.put("name", hotel.getName());
			map.put("price", hotel.getPrice());
			map.put("id", hotel.getId());
			map.put("telephone", hotel.getTelephone());
			map.put("content", hotel.getContent());
			final String image = hotel.getImage();
			// ��ͼƬ���ó�final����ͼƬ���������
			new Thread() {

				public void run() {
					try {
						URL url = new URL("http://10.0.2.2:8080/TaTTravelWeb/image/" + image);
						InputStream is = url.openStream();
						bitmap = BitmapFactory.decodeStream(is);
						is.close();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					map.put("image", bitmap);
					// ��ͼƬ���سɹ��������map����������Ϊ��
					handler.sendEmptyMessage(0x12);
				}

			}.start();

			list.add(map);
		}
		return list;
	}

	// ȫ����ѯ
//	private class MyThreadone extends Thread {
//		public void run() {
//			result = new WebUtil()
//					.executeGet("http://10.0.2.2:8080/TaTTravelWeb/" + typeServ + "?typeName=" + typeName);
//			Log.i("result", result);
//			handler.sendEmptyMessage(0x11);
//
//		}
//	}

	// ѡ���Բ�ѯ
	 private class MyThreadtow extends Thread {
		public void run() {
			result = new WebUtil()
					.executeGet("http://10.0.2.2:8080/TaTTravelWeb/DingDanServ?username="+typeName);
			Log.i("result", result);
			handler.sendEmptyMessage(0x11);

		}
	}

}
