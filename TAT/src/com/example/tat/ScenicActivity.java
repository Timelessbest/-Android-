package com.example.tat;
/***
 *    ģ����ѯҳ(ScenicActivity)
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

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ScenicActivity extends Activity implements android.view.GestureDetector.OnGestureListener {
	String result;
	Handler handler;
	List<Map<String, Object>> list;
String typeName="";
	Bitmap bitmap;
	SimpleAdapter sadapter;
	private TabHost tabhost;
	   private LinearLayout tab1, tab2, tab3;
	private ListView lv1, lv2, lv3,lv;
	String typestr="";
	String tabonestr="";
	String tabtwostr="";
	String tabthreestr="";

	String typeServ ="";

//	int[] imgs = new int[3];
	private int[] imgs = { R.drawable.kai1, R.drawable.kai0, R.drawable.kai2 };
	private GestureDetector gestureDetector = null;
	private ViewFlipper viewFlipper = null;

	private Activity mActivity = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenic);

		
		 

		tabhost = (TabHost) findViewById(R.id.tabhost);
		tab1 = (LinearLayout) findViewById(R.id.tab1);
		tab2 = (LinearLayout) findViewById(R.id.tab2);
		tab3 = (LinearLayout) findViewById(R.id.tab3);
		
		lv1 = (ListView) findViewById(R.id.lv1);
		lv2 = (ListView) findViewById(R.id.lv2);
		lv3 = (ListView) findViewById(R.id.lv3);
		
		Intent intent = new Intent();
		intent = this.getIntent();
		 typestr = intent.getStringExtra("name1").toString();
		if(typestr.equals("travel")){
			tabonestr = "������";
			tabtwostr = "������";
			tabthreestr = "�Լ���";
			typeServ = "JinDianStrs";
			//ͼ
//			int[] imgs = { R.drawable.fenji, R.drawable.fengjin5, R.drawable.beijing2 };
			
		}if(typestr.equals("hotel")){
			tabonestr = "�����Ƶ�";
			tabtwostr = "�Ǽ��Ƶ�";
			tabthreestr = "ƽ��Ƶ�";
			typeServ = "HotelStr";
//			int[] imgs = { R.drawable.fenji, R.drawable.fengjin5, R.drawable.beijing2 };
			
		}if(typestr.equals("plane")){
			tabonestr = "���ڻ�Ʊ";
			tabtwostr = "�����Ʊ";
			tabthreestr = "�����Ʊ";
			typeServ = "PlaneStr";
//			int[] imgs = { R.drawable.fenji, R.drawable.fengjin5, R.drawable.beijing2 };
			
		}
		tabhost.setup(); // ��ʼ��TabHost����

		tabhost.addTab(tabhost.newTabSpec("tab1")
				.setIndicator(tabonestr, getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.tab1));
		tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator(tabtwostr, null).setContent(R.id.tab2));
		tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator(tabthreestr, null).setContent(R.id.tab3));
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x11) {
					 sadapter = new SimpleAdapter(ScenicActivity.this, getData(), R.layout.list_jindian,
							new String[] { "name", "content" ,"telephone","price","image"}, new int[] { R.id.namelist1, R.id.contentlist1, R.id.telephonelist1,R.id.pricelist1,R.id.inmagelist1});
					lv.setAdapter(sadapter);
				}
				if (msg.what == 0x12) {
					//��������������ʾ��image
					sadapter.setViewBinder(new ViewBinder() {  
			              
			            public boolean setViewValue(View view, Object data,  
			                    String textRepresentation) {  
			                //�ж��Ƿ�Ϊ����Ҫ����Ķ���  
			                if(view instanceof ImageView  && data instanceof Bitmap){  
			                    ImageView iv = (ImageView) view;  
			                  
			                    iv.setImageBitmap((Bitmap) data);  
			                    return true;  
			                }else  
			                return false;  
			            }  
			        });  
					lv.setAdapter(sadapter);
					//�ٴμ���������
				}
			
			}

		};
		//�������б���Ӽ���
		lv=lv1;
		typeName=tabonestr;
		new MyThread().start();
		tabhost.setOnTabChangedListener(new OnTabChangeListener() {
		
			@Override
			 public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				
				if("tab1".equals(tabId)){
		      			lv=lv1;
					typeName=tabonestr;
					
				}
				if("tab2".equals(tabId)){
					lv=lv2;
					typeName=tabtwostr;
					
				}
				if("tab3".equals(tabId)){
					lv=lv3;
					typeName=tabthreestr;
					
				}
				new MyThread().start();
			}
		});
		
		
		mActivity = this;

		viewFlipper = (ViewFlipper) findViewById(R.id.viewmain2);
		gestureDetector = new GestureDetector(this); // ������������¼�

		for (int i = 0; i < imgs.length; i++) { // ���ͼƬԴ
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgs[i]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			viewFlipper.addView(iv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		}

		viewFlipper.setAutoStart(true); // �����Զ����Ź��ܣ�����¼���ǰ�Զ����ţ�
		viewFlipper.setFlipInterval(3000);
		if (viewFlipper.isAutoStart() && !viewFlipper.isFlipping()) {
			viewFlipper.startFlipping();
		}
	
	}

	// public boolean onTouchEvent(MotionEvent event) {
	// viewFlipper.stopFlipping(); // ����¼���ֹͣ�Զ�����
	// viewFlipper.setAutoStart(false);
	// Intent intent=new Intent(WellcomeActivity.this,MainActivity.class);
	// startActivity(intent);
	// return gestureDetector.onTouchEvent(event); // ע�������¼�
	// }

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if (e2.getX() - e1.getX() > 120) { // �������һ���������ҳ���
			Animation rInAnim = AnimationUtils.loadAnimation(mActivity, R.layout.push_right_in); // ���һ���������Ľ���Ч����alpha
																									// 0.1
																									// ->
																									// 1.0��
			Animation rOutAnim = AnimationUtils.loadAnimation(mActivity, R.layout.push_right_out); // ���һ����Ҳ໬���Ľ���Ч����alpha
																									// 1.0
																									// ->
																									// 0.1��

			viewFlipper.setInAnimation(rInAnim);
			viewFlipper.setOutAnimation(rOutAnim);
			viewFlipper.showPrevious();
			return true;
		} else if (e2.getX() - e1.getX() < -120) { // �������󻬶����ҽ������
			Animation lInAnim = AnimationUtils.loadAnimation(mActivity, R.layout.push_left_in); // ���󻬶�������Ľ���Ч����alpha
																								// 0.1
																								// ->
																								// 1.0��
			Animation lOutAnim = AnimationUtils.loadAnimation(mActivity, R.layout.push_left_out); // ���󻬶��Ҳ໬���Ľ���Ч����alpha
																									// 1.0
																									// ->
																									// 0.1��

			viewFlipper.setInAnimation(lInAnim);
			viewFlipper.setOutAnimation(lOutAnim);
			viewFlipper.showNext();
			return true;
		}
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	private class MyThread extends Thread {
		public void run() {
			result = new WebUtil().executeGet("http://10.0.2.2:8080/TaTTravelWeb/"+typeServ+"?typeName="+typeName);
			Log.i("result", result);
			handler.sendEmptyMessage(0x11);

		}
	}
	
	public List<Map<String, Object>> getData() {
		list = new ArrayList<Map<String, Object>>();
		
		Gson json = new Gson();
		//����ȡ��JSon����ת����List
		Type listType = new TypeToken<List<Hotel>>() {
		}.getType();
		List<Hotel> list1 = json.fromJson(result, listType);
		
		for (int i = 0; i < list1.size(); i++) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			//6������
			Hotel hotel = list1.get(i);
			map.put("name",hotel.getName());
			map.put("price", hotel.getPrice());
			map.put("id", hotel.getId());
			map.put("telephone", hotel.getTelephone());
			map.put("content", hotel.getContent());
			final String image = hotel.getImage();
			//��ͼƬ���ó�final����ͼƬ���������
		    new Thread(){

				public void run() {
					try {
						URL url = new URL("http://10.0.2.2:8080/TaTTravelWeb/image/"+image);
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
					//��ͼƬ���سɹ��������map����������Ϊ��
					handler.sendEmptyMessage(0x12);
				}
			
		    }.start();
			
			
			list.add(map);
		}
		return list;
	}

	
}
