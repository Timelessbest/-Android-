package com.cn;

/***
 * 保存公共信息，用户名和密码 状态标志位
 * @author Administrator
 *
 */
public class Info {

	public String username = "";
	public String password = "";
//	public String imagestr = "zhangjiajie.jpg";
	//temp为0为未登录
	//temp为1为登录成功，通过temp获取该用户的用户名和密码
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
