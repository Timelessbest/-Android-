package com.example.tat;

/***
 * 模糊显示Listview (ListActivity)
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Spinner;
import android.widget.TextView;

public class ListActivity extends Activity {
	String result;
	Handler handler;
	private Spinner spinner2=null;
	String dizhizhi="";
	 private ListView trip_listview;
	TextView textView1ses;
	ImageView imageViewss; //地址下拉按钮
	List<Map<String, Object>> list;
	String typeName = "";
	String type = "";
	String Didian = "";
	String Sousuo = "";

	String typeServ = "";
	Bitmap bitmap;
	SimpleAdapter sadapter;
	private ArrayAdapter<CharSequence> adapterArea=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trip_list);
		spinner2=(Spinner) findViewById(R.id.spinner2);
        trip_listview = (ListView) findViewById(R.id.trip_listview);
//		textView1ses = (TextView) findViewById(R.id.textView1ses);
		imageViewss = (ImageView) findViewById(R.id.imageViewss);
		Intent intent = new Intent();
		intent = this.getIntent();
		dizhizhi = (String) spinner2.getSelectedItem(); 
		typeName = intent.getStringExtra("name").toString();
		type = intent.getStringExtra("type").toString();
		System.out.println("name:  " + typeName + "  type  " + type);
//		System.out.println("name:  " + typeName + "  type  " + type);
		if (type.equals("travel")) {
			typeServ = "JingDianServ";
		}
		if (type.equals("hotel")) {
			typeServ = "HotelServ";
		}
		if (type.equals("plane")) {
			typeServ = "PlaneServ";
		}

		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x11) {
					sadapter = new SimpleAdapter(ListActivity.this, getData(), R.layout.trips_list,
							new String[] { "name", "content", "telephone", "price", "image" },
							new int[] { R.id.names_list, R.id.content_lists, R.id.telphone_list, R.id.price_list,
									R.id.inmage_list });
					trip_listview.setAdapter(sadapter);
				}
				if (msg.what == 0x12) {
					// 更改适配器所显示的image
					sadapter.setViewBinder(new ViewBinder() {

						public boolean setViewValue(View view, Object data, String textRepresentation) {
							// 判断是否为我们要处理的对象
							if (view instanceof ImageView && data instanceof Bitmap) {
								ImageView iv = (ImageView) view;

								iv.setImageBitmap((Bitmap) data);
								return true;
							} else
								return false;
						}
					});
					trip_listview.setAdapter(sadapter);
					// 再次加载适配器
				}

			}

		};
		new MyThreadone().start();

		// 给listview添加监听
		
		
		//  地址下拉
		
		
		
		
		
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				dizhizhi = (String) spinner2.getSelectedItem(); 
				new MyThreadone().start();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		trip_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(ListActivity.this, ListUpdateActivity.class);
				int i = position;
				Gson json = new Gson();
				// 将获取的JSon数据转换成List
				Type listType = new TypeToken<List<Hotel>>() {
				}.getType();
				List<Hotel> list1 = json.fromJson(result, listType);
				Hotel hotel = list1.get(i);
				String image = hotel.getImage();
				// System.out.println("99"+image+"");
				intent.putExtra("image", image);
				intent.putExtra("name", list.get(position).get("name").toString());
				intent.putExtra("telephone", list.get(position).get("telephone").toString());
				intent.putExtra("price", list.get(position).get("price").toString());
				intent.putExtra("content", list.get(position).get("content").toString());

				startActivity(intent);

			}
		});
	}

	public List<Map<String, Object>> getData() {
		list = new ArrayList<Map<String, Object>>();

		Gson json = new Gson();
		// 将获取的JSon数据转换成List
		Type listType = new TypeToken<List<Hotel>>() {
		}.getType();
		List<Hotel> list1 = json.fromJson(result, listType);

		for (int i = 0; i < list1.size(); i++) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			// 6个参数
			Hotel hotel = list1.get(i);
			map.put("name", hotel.getName());
			map.put("price", hotel.getPrice());
			map.put("id", hotel.getId());
			map.put("telephone", hotel.getTelephone());
			map.put("content", hotel.getContent());
			final String image = hotel.getImage();
			// 把图片设置成final避免图片被随意更改
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
					// 当图片下载成功了再添加map，否则容易为空
					handler.sendEmptyMessage(0x12);
				}

			}.start();

			list.add(map);
		}
		return list;
	}

	// 全部查询
	private class MyThreadone extends Thread {
		public void run() {
			result = new WebUtil()
					.executeGet("http://10.0.2.2:8080/TaTTravelWeb/" + typeServ + "?typeName=" + typeName+ "&dizhi=" + dizhizhi);
			Log.i("result", result);
			handler.sendEmptyMessage(0x11);

		}
	}
	
	// 选择性查询
	private class MyThreadtow extends Thread {
		public void run() {
			result = new WebUtil()
					.executeGet("http://10.0.2.2:8080/TaTTravelWeb/" + typeServ + "?typeName=" + typeName+ "&=dizhi" + dizhizhi);
			Log.i("result", result);
			handler.sendEmptyMessage(0x11);

		}
	}

}
