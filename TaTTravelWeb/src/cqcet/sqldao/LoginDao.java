package cqcet.sqldao;

import java.sql.ResultSet;
import java.sql.SQLException;

import cqcet.sqldbu.DBUtil;

//用户登录和注册
public class LoginDao {

	ResultSet rs;
	/***
	 * 判断该用户是否存在
	 * @param username 用户名
	 * @param password 密码
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
	// 添加新用户到数据库中
			public boolean addUserinfo(String username ,String password) {// 添加

				String sql = "insert into userinfo (username,password) values('"+username+"','"+password+"')";
				return new DBUtil().executeUpdate(sql);
			}
}
