package com.cn;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

public class MyOnclisenr implements OnClickListener {
	private Context context;
	 public  MyOnclisenr(Context context){
		this.context=context;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new Builder(context);
		builder.setTitle("退出提示！");
		builder.setMessage("你确定退出?");
		builder.setCancelable(false);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
		});
builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				
			}
		});
builder.create();
builder.show();
		
	}
	
}

