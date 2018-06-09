package com.example.tat;

/***
 *    ������(MainActivity)
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
	private Button lvyou;// ����
	private Button ziyouxing, zijiayou, gentuanyou, travelOther;
	private Button jiudian;// �Ƶ�
	private Button liansuoHotel, xingjiHotel, pingminHotel, hotelOther;
	private Button jipiao;// ��Ʊ
	private Button guoneiPlane, haiwaiPlane, kuaguoPlane, tejiaPlane;
	private Button myOrder;// ����

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
		// �������Σ��Ƶ�ͻ�Ʊ�ļ���
		lvyou = (Button) findViewById(R.id.travelButton);

	
		lvyou.setOnClickListener(new taskOnClickListener());
		jiudian = (Button) findViewById(R.id.hotelButton);
		jiudian.setOnClickListener(new taskOnClickListener());
		myOrder = (Button) findViewById(R.id.myOrder);
		myOrder.setOnClickListener(new taskOnClickListener());
		jipiao = (Button) findViewById(R.id.planeButton);
		jipiao.setOnClickListener(new taskOnClickListener());
		// �������ε���ϸ����
		ziyouxing = (Button) findViewById(R.id.ziyouxing);
		ziyouxing.setOnClickListener(new taskOnClickListener());
		zijiayou = (Button) findViewById(R.id.zijiayou);
		zijiayou.setOnClickListener(new taskOnClickListener());
		gentuanyou = (Button) findViewById(R.id.gentuanyou);
		gentuanyou.setOnClickListener(new taskOnClickListener());
		travelOther = (Button) findViewById(R.id.travelOther);
		travelOther.setOnClickListener(new taskOnClickListener());

		// ���ھƵ����ϸ����
		liansuoHotel = (Button) findViewById(R.id.liansuoHotel);
		liansuoHotel.setOnClickListener(new taskOnClickListener());
		xingjiHotel = (Button) findViewById(R.id.xingjiHotel);
		xingjiHotel.setOnClickListener(new taskOnClickListener());
		pingminHotel = (Button) findViewById(R.id.pingminHotel);
		pingminHotel.setOnClickListener(new taskOnClickListener());
		hotelOther = (Button) findViewById(R.id.hotelOther);
		hotelOther.setOnClickListener(new taskOnClickListener());
		// ���ڻ�Ʊ����ϸ����
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

	private class taskOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.travelButton:
				// ���[
				Intent intentTravel = new Intent(MainActivity.this, ScenicActivity.class);
				intentTravel.putExtra("name1", "travel");
				startActivity(intentTravel);
				break;
			case R.id.ziyouxing:
				// ������
				Intent intent1 = new Intent(MainActivity.this, ListActivity.class);
				intent1.putExtra("name", ziyouxing.getText().toString());
				intent1.putExtra("type", "travel");
				startActivity(intent1);
				break;
			case R.id.zijiayou:
				// �Լ���
				Intent intent2 = new Intent(MainActivity.this, ListActivity.class);
				intent2.putExtra("name", zijiayou.getText().toString());
				intent2.putExtra("type", "travel");
				startActivity(intent2);

				break;
			case R.id.gentuanyou:
				// ������
				Intent intent3 = new Intent(MainActivity.this, ListActivity.class);
				intent3.putExtra("name", gentuanyou.getText().toString());
				intent3.putExtra("type", "travel");
				startActivity(intent3);

				break;
			case R.id.travelOther:
				// ������
				Intent intent4 = new Intent(MainActivity.this, ListActivity.class);
				intent4.putExtra("name", travelOther.getText().toString());
				intent4.putExtra("type", "travel");
				startActivity(intent4);

				break;

			case R.id.hotelButton:
				// �Ƶ�
				Intent intentHotel = new Intent(MainActivity.this, ScenicActivity.class);
				intentHotel.putExtra("name1", "hotel");

				startActivity(intentHotel);
				break;
			case R.id.liansuoHotel:
				// �����Ƶ�
				Intent intent5 = new Intent(MainActivity.this, ListActivity.class);
				intent5.putExtra("name", liansuoHotel.getText().toString());
				intent5.putExtra("type", "hotel");
				startActivity(intent5);
				break;
			case R.id.xingjiHotel:
				// �Ǽ��Ƶ�
				Intent intent6 = new Intent(MainActivity.this, ListActivity.class);
				intent6.putExtra("name", xingjiHotel.getText().toString());
				intent6.putExtra("type", "hotel");
				startActivity(intent6);

				break;
			case R.id.pingminHotel:
				// ƽ��Ƶ�
				Intent intent7 = new Intent(MainActivity.this, ListActivity.class);
				intent7.putExtra("name", pingminHotel.getText().toString());
				intent7.putExtra("type", "hotel");
				startActivity(intent7);
				break;
			case R.id.hotelOther:
				// �����Ƶ�
				Intent intent8 = new Intent(MainActivity.this, ListActivity.class);
				intent8.putExtra("name", hotelOther.getText().toString());
				intent8.putExtra("type", "hotel");
				startActivity(intent8);
				break;
			case R.id.planeButton:
				// ��Ʊ
				Intent intentPlane = new Intent(MainActivity.this, ScenicActivity.class);
				intentPlane.putExtra("name1", "plane");
				startActivity(intentPlane);
				break;
			case R.id.guoneiPlane:
				// ���ڻ�Ʊ
				Intent intent9 = new Intent(MainActivity.this, ListActivity.class);
				intent9.putExtra("name", guoneiPlane.getText().toString());
				intent9.putExtra("type", "plane");
				startActivity(intent9);
				break;
			case R.id.haiwaiPlane:
				// �����Ʊ
				Intent intent10 = new Intent(MainActivity.this, ListActivity.class);
				intent10.putExtra("name", haiwaiPlane.getText().toString());
				intent10.putExtra("type", "plane");
				startActivity(intent10);
				break;
			case R.id.kuaguoPlane:
				// �����Ʊ
				Intent intent11 = new Intent(MainActivity.this, ListActivity.class);
				intent11.putExtra("name", kuaguoPlane.getText().toString());
				intent11.putExtra("type", "plane");
				startActivity(intent11);
				break;
			case R.id.tejiaPlane:
				// �ؼۻ�Ʊ
				Intent intent12 = new Intent(MainActivity.this, ListActivity.class);
				intent12.putExtra("name", tejiaPlane.getText().toString());
				intent12.putExtra("type", "plane");
				startActivity(intent12);
				break;
			case R.id.myOrder:
				// �ҵĶ���
				global.setYeoron("ok");
				Intent intent13 = new Intent();
				if (global.getTemp() == 1) {

					// ֱ�Ӳ鿴������Ϣ

					intent13.setClass(MainActivity.this, DingdanActivity.class);
					startActivity(intent13);

				} else {
					// ��ת����¼���� �ٵ�¼����ע��
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
