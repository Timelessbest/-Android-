/***
 *    �û���½����    (LoginActivity)
 */
package com.example.tat;

import com.cn.Info;
import com.cn.WebUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button loginbutton, registerlogin; // ���� ��ע��
	private EditText accountlogin, passwordlogin;// �û��� /����
	private TextView wangjimimalogin;// ��������
	String result;
	String name;
	String psw;
	Handler handler;
	Info info = new Info();
	Global global;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		global = ((Global) getApplicationContext());
		loginbutton = (Button) findViewById(R.id.loginbutton);
		registerlogin = (Button) findViewById(R.id.registerlogin);
		accountlogin = (EditText) findViewById(R.id.accountlogin);
		passwordlogin = (EditText) findViewById(R.id.passwordlogin);
		wangjimimalogin = (TextView) findViewById(R.id.wangjimimalogin);
		Toast.makeText(LoginActivity.this, "yeor   "+global.getYeoron(), 2).show();
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 0x12) {
					if ("ok".equals(result)) {
						if (global.getYeoron().equals("ok")) {
							global.setTemp(1);
							global.setUsername(accountlogin.getText().toString());
							Intent intent = new Intent(LoginActivity.this, DingdanActivity.class);
							startActivity(intent);
						} 
						else if (global.getYeoron().equals("no")) {
							Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
							startActivity(intent2);

							
						}

					} else {
						Toast.makeText(LoginActivity.this, "�����������������", 1).show();
					}
				}

			}

		};

		loginbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String re = panDuan();
				if (re != null) {
					AlertDialog.Builder builder = new Builder(LoginActivity.this);
					builder.setTitle("��ܰ��ʾ��");
					builder.setMessage(re);
					builder.setCancelable(false);

					builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
					builder.create();
					builder.show();

				} else {
					// TODO Auto-generated method stub

					name = accountlogin.getText().toString();
					psw = passwordlogin.getText().toString();

					// �����жϵ����߳�
					new LoginThread().start();
			
				}

			}
		});
		// ע��
		registerlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, UserZhuceActivity.class);
				startActivity(intent);

			}
		});
	}

	private class LoginThread extends Thread {
		public void run() {
			String url = "http://10.0.2.2:8080/TaTTravelWeb/LoginServ";
			url = url + "?name=" + name + "&psw=" + psw;
			result = new WebUtil().executeGet(url);
			handler.sendEmptyMessage(0x12);

		}
	}

	public String panDuan() {
		if (accountlogin == null || accountlogin.getText().toString().trim().length() == 0) {
			return "�û�������Ϊ��";
		}
		if (passwordlogin == null || passwordlogin.getText().toString().trim().length() == 0) {
			return "���벻��Ϊ��";
		}

		return null;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
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
