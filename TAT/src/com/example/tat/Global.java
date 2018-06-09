package com.example.tat;
/***
 * 用户登陆管理(Global)
 */
import android.app.Application;

public class Global extends Application {

	public String username = "未登陆";
	public String password = "";
//	public String imagestr = "zhangjiajie.jpg";
	//temp为0为未登录
	//temp为1为登录成功，通过temp获取该用户的用户名和密码
	public String yeoron="ok";
	public String getYeoron() {
		return yeoron;
	}
	public void setYeoron(String yeoron) {
		this.yeoron = yeoron;
	}
	public int temp = 0;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	
}
