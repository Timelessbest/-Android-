package com.example.tat;
/***
 *    模糊查询页(ScenicActivity)
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
			tabonestr = "自由行";
			tabtwostr = "跟团游";
			tabthreestr = "自驾游";
			typeServ = "JinDianStrs";
			//图
//			int[] imgs = { R.drawable.fenji, R.drawable.fengjin5, R.drawable.beijing2 };
			
		}if(typestr.equals("hotel")){
			tabonestr = "连锁酒店";
			tabtwostr = "星级酒店";
			tabthreestr = "平民酒店";
			typeServ = "HotelStr";
//			int[] imgs = { R.drawable.fenji, R.drawable.fengjin5, R.drawable.beijing2 };
			
		}if(typestr.equals("plane")){
			tabonestr = "国内机票";
			tabtwostr = "海外机票";
			tabthreestr = "跨国机票";
			typeServ = "PlaneStr";
//			int[] imgs = { R.drawable.fenji, R.drawable.fengjin5, R.drawable.beijing2 };
			
		}
		tabhost.setup(); // 初始化TabHost容器

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
					//更改适配器所显示的image
					sadapter.setViewBinder(new ViewBinder() {  
			              
			            public boolean setViewValue(View view, Object data,  
			                    String textRepresentation) {  
			                //判断是否为我们要处理的对象  
			                if(view instanceof ImageView  && data instanceof Bitmap){  
			                    ImageView iv = (ImageView) view;  
			                  
			                    iv.setImageBitmap((Bitmap) data);  
			                    return true;  
			                }else  
			                return false;  
			            }  
			        });  
					lv.setAdapter(sadapter);
					//再次加载适配器
				}
			
			}

		};
		//导航的列表添加监听
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
		gestureDetector = new GestureDetector(this); // 声明检测手势事件

		for (int i = 0; i < imgs.length; i++) { // 添加图片源
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgs[i]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			viewFlipper.addView(iv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		}

		viewFlipper.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
		viewFlipper.setFlipInterval(3000);
		if (viewFlipper.isAutoStart() && !viewFlipper.isFlipping()) {
			viewFlipper.startFlipping();
		}
	
	}

	// public boolean onTouchEvent(MotionEvent event) {
	// viewFlipper.stopFlipping(); // 点击事件后，停止自动播放
	// viewFlipper.setAutoStart(false);
	// Intent intent=new Intent(WellcomeActivity.this,MainActivity.class);
	// startActivity(intent);
	// return gestureDetector.onTouchEvent(event); // 注册手势事件
	// }

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if (e2.getX() - e1.getX() > 120) { // 从左向右滑动（左进右出）
			Animation rInAnim = AnimationUtils.loadAnimation(mActivity, R.layout.push_right_in); // 向右滑动左侧进入的渐变效果（alpha
																									// 0.1
																									// ->
																									// 1.0）
			Animation rOutAnim = AnimationUtils.loadAnimation(mActivity, R.layout.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha
																									// 1.0
																									// ->
																									// 0.1）

			viewFlipper.setInAnimation(rInAnim);
			viewFlipper.setOutAnimation(rOutAnim);
			viewFlipper.showPrevious();
			return true;
		} else if (e2.getX() - e1.getX() < -120) { // 从右向左滑动（右进左出）
			Animation lInAnim = AnimationUtils.loadAnimation(mActivity, R.layout.push_left_in); // 向左滑动左侧进入的渐变效果（alpha
																								// 0.1
																								// ->
																								// 1.0）
			Animation lOutAnim = AnimationUtils.loadAnimation(mActivity, R.layout.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha
																									// 1.0
																									// ->
																									// 0.1）

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
		//将获取的JSon数据转换成List
		Type listType = new TypeToken<List<Hotel>>() {
		}.getType();
		List<Hotel> list1 = json.fromJson(result, listType);
		
		for (int i = 0; i < list1.size(); i++) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			//6个参数
			Hotel hotel = list1.get(i);
			map.put("name",hotel.getName());
			map.put("price", hotel.getPrice());
			map.put("id", hotel.getId());
			map.put("telephone", hotel.getTelephone());
			map.put("content", hotel.getContent());
			final String image = hotel.getImage();
			//把图片设置成final避免图片被随意更改
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
					//当图片下载成功了再添加map，否则容易为空
					handler.sendEmptyMessage(0x12);
				}
			
		    }.start();
			
			
			list.add(map);
		}
		return list;
	}

	
}
