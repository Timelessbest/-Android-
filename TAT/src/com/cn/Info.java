package com.cn;

/***
 * ���湫����Ϣ���û��������� ״̬��־λ
 * @author Administrator
 *
 */
public class Info {

	public String username = "";
	public String password = "";
//	public String imagestr = "zhangjiajie.jpg";
	//tempΪ0Ϊδ��¼
	//tempΪ1Ϊ��¼�ɹ���ͨ��temp��ȡ���û����û���������
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
