package com.example.tat;

/**
 * �ο�ע��ҳ(UserZhuceActivity)
 */
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
import android.widget.Toast;

public class UserZhuceActivity extends Activity {
	private EditText name2, password;
	private Button register_complete;
	String result;
	String name;
	String psw;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_zhuce);
		name2 = (EditText) findViewById(R.id.name);
		password = (EditText) findViewById(R.id.password);
		register_complete = (Button) findViewById(R.id.register_complete);

		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);

				if (msg.what == 0x13) {
					if ("ok".equals(result)) {
						Toast.makeText(UserZhuceActivity.this, "ע��ɹ�", 1).show();
						// //ע��ɹ� ��ת��
						Intent intent = new Intent(UserZhuceActivity.this, DingdanActivity.class);
						startActivity(intent);

					} else {
						Toast.makeText(UserZhuceActivity.this, "ע��ʧ�ܣ����û����Ѵ���", 1).show();

					}
				}
			}

		};

		register_complete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String re = panDuan();
				if (re != null) {
					AlertDialog.Builder builder = new Builder(UserZhuceActivity.this);
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
					name = name2.getText().toString();
					psw = password.getText().toString();

					// �����жϵ����߳�
					new RegisterThread().start();
				}

			}
		});

	}

	private class RegisterThread extends Thread {
		public void run() {
			String url = "http://10.0.2.2:8080/TaTTravelWeb/RegisterServ";
			url = url + "?name=" + name + "&psw=" + psw;
			result = new WebUtil().executeGet(url);
			handler.sendEmptyMessage(0x13);

		}
	}

	public String panDuan() {
		if (name2 == null || name2.getText().toString().trim().length() == 0) {
			return "���벻��Ϊ��";
		}
		if (password == null || password.getText().toString().trim().length() == 0) {
			return "�û�������Ϊ��";
		}

		return null;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_zhuce, menu);
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
