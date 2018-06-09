package com.example.tat;
/***
 * 景点/酒店/机票  详情显示页(ListUpdateActivity)
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.cn.Info;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListUpdateActivity extends Activity {
	Handler handler;
	Bitmap bitmap;
	String result;
	SimpleAdapter sadapter;
	private Button myorder;
	private TextView namelistcontent,contentlisttcontent,pricelistcontent,telpnoelistcontent;
	ImageView image_update;
	Bitmap bm;
    Info info = new Info();
    String imagestr  = "";
   String image  = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listcontent);
		myorder = (Button) findViewById(R.id.myorder);

		image_update = (ImageView) findViewById(R.id.image_update);
		namelistcontent = (TextView) findViewById(R.id.namelistcontent);

		contentlisttcontent = (TextView) findViewById(R.id.contentlisttcontent);

		pricelistcontent = (TextView) findViewById(R.id.pricelistcontent);

		telpnoelistcontent = (TextView) findViewById(R.id.telpnoelistcontent);


		Intent intent1 = new Intent();
		intent1 = this.getIntent();
		 imagestr = intent1.getStringExtra("image".toString());
		 namelistcontent.setText(intent1.getStringExtra("name".toString()));
		 contentlisttcontent.setText(intent1.getStringExtra("content".toString()));
		 pricelistcontent.setText(intent1.getStringExtra("price".toString()));
		 telpnoelistcontent.setText(intent1.getStringExtra("telephone".toString()));
		 
		 
//		Toast.makeText(ListUpdateActivity.this, "11"+imagestr+"11", 2).show();
		
//			image = intent.getStringExtra("image".toString());
		
//		image = info.imagestr;
		
		handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				if(msg.what==0x12){
					image_update.setImageBitmap(bm);
//			Toast.makeText(ListUpdateActivity.this, "!!!!!!!图片信息：  "+imagestr, 2).show();
			
				}
				
			}
			
		};
		
		new ImageThread().start();
		
	
		
		
		telpnoelistcontent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showMultiDia(telpnoelistcontent.getText().toString());
			}
		});
		myorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				
				Intent intent = new Intent();
				intent.setClass(ListUpdateActivity.this,DingDan_tureActivity.class);
				intent.putExtra("dindan_inmage",imagestr);
				intent.putExtra("dindan_name",namelistcontent.getText().toString());
				intent.putExtra("dindan_content",contentlisttcontent.getText().toString());
				intent.putExtra("dindan_price",pricelistcontent.getText().toString());
				intent.putExtra("dindan_telpnoe",telpnoelistcontent.getText().toString());
				startActivity(intent);

				
			}
		});
	}
	private void showMultiDia(final String telephone)  
	{  
	    AlertDialog.Builder multiDia=new AlertDialog.Builder(ListUpdateActivity.this);  
	    multiDia.setTitle("请选择操作");  
	    multiDia.setPositiveButton("打电话", new DialogInterface.OnClickListener() {  
	          
	        @Override  
	        public void onClick(DialogInterface dialog, int which) {  
	            // TODO Auto-generated method stub  
	          //  showClickMessage("按钮一");  
	        	//拨打电话
	        	Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+telephone));  
	        	ListUpdateActivity.this.startActivity(intent);  
	        }  
	    });  
	    multiDia.setNeutralButton("发短信", new DialogInterface.OnClickListener() {  
	          
	        @Override  
	        public void onClick(DialogInterface dialog, int which) {  
	            // TODO Auto-generated method stub  
	          //  发送短信
	        	 if(PhoneNumberUtils.isGlobalPhoneNumber(telephone)){  
	                 Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+telephone));            
	                 intent.putExtra("sms_body", "测试发短信");            
	                 startActivity(intent);  
	             }  
	        }  
	    });  
	    multiDia.setNegativeButton("按钮三", new DialogInterface.OnClickListener() {  
	          
	        @Override  
	        public void onClick(DialogInterface dialog, int which) {  
	            // TODO Auto-generated method stub  
	            //showClickMessage("按钮三");  
	        }  
	    });  
	    multiDia.create().show();  
	} 

	 public class ImageThread extends Thread{

			public void run() {
				try {
					URL url = new URL("http://10.0.2.2:8080/TaTTravelWeb/image/"+imagestr);
					InputStream is = url.openStream();
					bm = BitmapFactory.decodeStream(is);
					is.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//当图片下载成功了再添加map，否则容易为空
				handler.sendEmptyMessage(0x12);
			}
		
	    }

}
