package com.example.tat;
/***
 * 确认订单
 */
import com.cn.WebUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DingDan_tureActivity extends Activity {
	private TextView name_dingdan, dindan_content, dindan_yingfu, dindan_jine;
	private Button dindan_button;
	String result;
	String inmages, telepnoe_dingdan1, name_dingdan1, dindan_content1, dindan_yingfu1, dindan_jine1;
	Handler handler;
	Global global;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ding_dan_ture);
		global = ((Global) getApplicationContext());
		// 控件实例化

		name_dingdan = (TextView) findViewById(R.id.name_dingdan);
		dindan_content = (TextView) findViewById(R.id.dindan_content);
		dindan_yingfu = (TextView) findViewById(R.id.dindan_yingfu);
		dindan_jine = (TextView) findViewById(R.id.dindan_jine);
		dindan_button = (Button) findViewById(R.id.dindan_button);
		// Toast.makeText(DingDan_tureActivity.this, "info " + global.temp + "",
		// 1).show();
		Intent intent1 = new Intent();
		intent1 = this.getIntent();

		telepnoe_dingdan1 = intent1.getStringExtra("dindan_telpnoe".toString());
		inmages = intent1.getStringExtra("dindan_inmage".toString());
		name_dingdan1 = intent1.getStringExtra("dindan_name".toString());
		dindan_content1 = intent1.getStringExtra("dindan_content".toString());
		dindan_yingfu1 = intent1.getStringExtra("dindan_price".toString());
		dindan_jine1 = intent1.getStringExtra("dindan_price".toString());
		name_dingdan.setText(name_dingdan1);
		dindan_content.setText(dindan_content1);
		dindan_yingfu.setText(dindan_yingfu1);
		dindan_jine.setText(dindan_jine1);

		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);

				if (msg.what == 0x13) {
					if ("ok".equals(result)) {
						Toast.makeText(DingDan_tureActivity.this, "支付成功", 1).show();
						// //注册成功 跳转到
						Intent intent13 = new Intent();
						
						intent13.setClass(DingDan_tureActivity.this, DingdanActivity.class);
						// 直接查看订单信息
                     	global.setYeoron("ok");
						startActivity(intent13);

					} else {
						Toast.makeText(DingDan_tureActivity.this, "支付失败", 1).show();
						global.setYeoron("no");
					}
				}
			}

		};

		dindan_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (global.getTemp() == 1) {
					new RegisterThread().start();
				
					//
				} else {
					Intent intent14 = new Intent();
					// 跳转到登录界面 再登录或者注册
					global.setYeoron("no");
					intent14.setClass(DingDan_tureActivity.this, LoginActivity.class);
				
					startActivity(intent14);
				}

			}
		});
	}

	private class RegisterThread extends Thread {
		public void run() {
			// "1"在数据库里对应的是用户<--->订单
			String url = "http://10.0.2.2:8080/TaTTravelWeb/AddDingdanServ";
			url = url + "?name=" + (name_dingdan.getText().toString()) + "&content="
					+ dindan_content.getText().toString() + "&price=" + dindan_jine.getText().toString() + "&telephone="
					+ telepnoe_dingdan1 + "&image=" + inmages + "&username=" + "1";
			result = new WebUtil().executeGet(url);
			handler.sendEmptyMessage(0x13);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ding_dan_ture, menu);
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
