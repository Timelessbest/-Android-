package com.example.tat;
/***
 * �û���½����(Global)
 */
import android.app.Application;

public class Global extends Application {

	public String username = "δ��½";
	public String password = "";
//	public String imagestr = "zhangjiajie.jpg";
	//tempΪ0Ϊδ��¼
	//tempΪ1Ϊ��¼�ɹ���ͨ��temp��ȡ���û����û���������
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
