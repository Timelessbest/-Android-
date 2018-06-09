package com.example.tat;

/***
 * 引导页(WellcomeActivity)
 */
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class WellcomeActivity extends Activity implements android.view.GestureDetector.OnGestureListener {

	private int[] imgs = { R.drawable.womentwo, R.drawable.womenone};

	private GestureDetector gestureDetector = null;
	private ViewFlipper viewFlipper = null;

	private Activity mActivity = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wellcome);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);

		mActivity = this;

		viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		viewFlipper.stopFlipping(); // 点击事件后，停止自动播放
		viewFlipper.setAutoStart(false);
		Intent intent = new Intent(WellcomeActivity.this, MainActivity.class);
		startActivity(intent);
		return gestureDetector.onTouchEvent(event); // 注册手势事件
	}

	@Override
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wellcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
