package cqcet.sqldao;

import java.sql.ResultSet;
import java.sql.SQLException;

import cqcet.sqldbu.DBUtil;

//�û���¼��ע��
public class LoginDao {

	ResultSet rs;
	/***
	 * �жϸ��û��Ƿ����
	 * @param username �û���
	 * @param password ����
	 * @return
	 */
	public boolean isExist(String username,String password){
		boolean bol = false;
		String sql = "select * from userinfo where username='"+username+"' and password='"+password+"'";
		rs = new DBUtil().executeQuery(sql);
		try {
			if (rs.next()) {
				bol = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			new DBUtil().close();
		}
		return bol;
	}
	// ������û������ݿ���
			public boolean addUserinfo(String username ,String password) {// ���

				String sql = "insert into userinfo (username,password) values('"+username+"','"+password+"')";
				return new DBUtil().executeUpdate(sql);
			}
}
