package com.example.tat;

/***
 *    主界面(MainActivity)
 */
import android.app.ActionBar.LayoutParams;

import com.cn.Info;
import com.example.tat.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements android.view.GestureDetector.OnGestureListener {
	private Button lvyou;// 旅游
	private Button ziyouxing, zijiayou, gentuanyou, travelOther;
	private Button jiudian;// 酒店
	private Button liansuoHotel, xingjiHotel, pingminHotel, hotelOther;
	private Button jipiao;// 机票
	private Button guoneiPlane, haiwaiPlane, kuaguoPlane, tejiaPlane;
	private Button myOrder;// 订单

	private int[] imgs = { R.drawable.tuo2, R.drawable.tou4, R.drawable.tou3 };

	private GestureDetector gestureDetector = null;
	private ViewFlipper viewFlipper = null;

	private Activity mActivity = null;
	Global global;

	Info info = new Info();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mian_1);
		global = ((Global) getApplicationContext());
		// 关于旅游，酒店和机票的监听
		lvyou = (Button) findViewById(R.id.travelButton);

	
		lvyou.setOnClickListener(new taskOnClickListener());
		jiudian = (Button) findViewById(R.id.hotelButton);
		jiudian.setOnClickListener(new taskOnClickListener());
		myOrder = (Button) findViewById(R.id.myOrder);
		myOrder.setOnClickListener(new taskOnClickListener());
		jipiao = (Button) findViewById(R.id.planeButton);
		jipiao.setOnClickListener(new taskOnClickListener());
		// 关于旅游的详细监听
		ziyouxing = (Button) findViewById(R.id.ziyouxing);
		ziyouxing.setOnClickListener(new taskOnClickListener());
		zijiayou = (Button) findViewById(R.id.zijiayou);
		zijiayou.setOnClickListener(new taskOnClickListener());
		gentuanyou = (Button) findViewById(R.id.gentuanyou);
		gentuanyou.setOnClickListener(new taskOnClickListener());
		travelOther = (Button) findViewById(R.id.travelOther);
		travelOther.setOnClickListener(new taskOnClickListener());

		// 关于酒店的详细监听
		liansuoHotel = (Button) findViewById(R.id.liansuoHotel);
		liansuoHotel.setOnClickListener(new taskOnClickListener());
		xingjiHotel = (Button) findViewById(R.id.xingjiHotel);
		xingjiHotel.setOnClickListener(new taskOnClickListener());
		pingminHotel = (Button) findViewById(R.id.pingminHotel);
		pingminHotel.setOnClickListener(new taskOnClickListener());
		hotelOther = (Button) findViewById(R.id.hotelOther);
		hotelOther.setOnClickListener(new taskOnClickListener());
		// 关于机票的详细监听
		guoneiPlane = (Button) findViewById(R.id.guoneiPlane);
		guoneiPlane.setOnClickListener(new taskOnClickListener());
		haiwaiPlane = (Button) findViewById(R.id.haiwaiPlane);
		haiwaiPlane.setOnClickListener(new taskOnClickListener());
		kuaguoPlane = (Button) findViewById(R.id.kuaguoPlane);
		kuaguoPlane.setOnClickListener(new taskOnClickListener());
		tejiaPlane = (Button) findViewById(R.id.tejiaPlane);
		tejiaPlane.setOnClickListener(new taskOnClickListener());

		mActivity = this;

		viewFlipper = (ViewFlipper) findViewById(R.id.viewmain1);
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

	private class taskOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.travelButton:
				// 旅遊
				Intent intentTravel = new Intent(MainActivity.this, ScenicActivity.class);
				intentTravel.putExtra("name1", "travel");
				startActivity(intentTravel);
				break;
			case R.id.ziyouxing:
				// 自由行
				Intent intent1 = new Intent(MainActivity.this, ListActivity.class);
				intent1.putExtra("name", ziyouxing.getText().toString());
				intent1.putExtra("type", "travel");
				startActivity(intent1);
				break;
			case R.id.zijiayou:
				// 自驾游
				Intent intent2 = new Intent(MainActivity.this, ListActivity.class);
				intent2.putExtra("name", zijiayou.getText().toString());
				intent2.putExtra("type", "travel");
				startActivity(intent2);

				break;
			case R.id.gentuanyou:
				// 跟团游
				Intent intent3 = new Intent(MainActivity.this, ListActivity.class);
				intent3.putExtra("name", gentuanyou.getText().toString());
				intent3.putExtra("type", "travel");
				startActivity(intent3);

				break;
			case R.id.travelOther:
				// 其他游
				Intent intent4 = new Intent(MainActivity.this, ListActivity.class);
				intent4.putExtra("name", travelOther.getText().toString());
				intent4.putExtra("type", "travel");
				startActivity(intent4);

				break;

			case R.id.hotelButton:
				// 酒店
				Intent intentHotel = new Intent(MainActivity.this, ScenicActivity.class);
				intentHotel.putExtra("name1", "hotel");

				startActivity(intentHotel);
				break;
			case R.id.liansuoHotel:
				// 连锁酒店
				Intent intent5 = new Intent(MainActivity.this, ListActivity.class);
				intent5.putExtra("name", liansuoHotel.getText().toString());
				intent5.putExtra("type", "hotel");
				startActivity(intent5);
				break;
			case R.id.xingjiHotel:
				// 星级酒店
				Intent intent6 = new Intent(MainActivity.this, ListActivity.class);
				intent6.putExtra("name", xingjiHotel.getText().toString());
				intent6.putExtra("type", "hotel");
				startActivity(intent6);

				break;
			case R.id.pingminHotel:
				// 平民酒店
				Intent intent7 = new Intent(MainActivity.this, ListActivity.class);
				intent7.putExtra("name", pingminHotel.getText().toString());
				intent7.putExtra("type", "hotel");
				startActivity(intent7);
				break;
			case R.id.hotelOther:
				// 其他酒店
				Intent intent8 = new Intent(MainActivity.this, ListActivity.class);
				intent8.putExtra("name", hotelOther.getText().toString());
				intent8.putExtra("type", "hotel");
				startActivity(intent8);
				break;
			case R.id.planeButton:
				// 机票
				Intent intentPlane = new Intent(MainActivity.this, ScenicActivity.class);
				intentPlane.putExtra("name1", "plane");
				startActivity(intentPlane);
				break;
			case R.id.guoneiPlane:
				// 国内机票
				Intent intent9 = new Intent(MainActivity.this, ListActivity.class);
				intent9.putExtra("name", guoneiPlane.getText().toString());
				intent9.putExtra("type", "plane");
				startActivity(intent9);
				break;
			case R.id.haiwaiPlane:
				// 海外机票
				Intent intent10 = new Intent(MainActivity.this, ListActivity.class);
				intent10.putExtra("name", haiwaiPlane.getText().toString());
				intent10.putExtra("type", "plane");
				startActivity(intent10);
				break;
			case R.id.kuaguoPlane:
				// 跨国机票
				Intent intent11 = new Intent(MainActivity.this, ListActivity.class);
				intent11.putExtra("name", kuaguoPlane.getText().toString());
				intent11.putExtra("type", "plane");
				startActivity(intent11);
				break;
			case R.id.tejiaPlane:
				// 特价机票
				Intent intent12 = new Intent(MainActivity.this, ListActivity.class);
				intent12.putExtra("name", tejiaPlane.getText().toString());
				intent12.putExtra("type", "plane");
				startActivity(intent12);
				break;
			case R.id.myOrder:
				// 我的订单
				global.setYeoron("ok");
				Intent intent13 = new Intent();
				if (global.getTemp() == 1) {

					// 直接查看订单信息

					intent13.setClass(MainActivity.this, DingdanActivity.class);
					startActivity(intent13);

				} else {
					// 跳转到登录界面 再登录或者注册
					intent13.setClass(MainActivity.this, LoginActivity.class);
					startActivity(intent13);
				}
				break;

			default:
				break;
			}
		}

	}
}
