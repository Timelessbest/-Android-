package cqcet.sqldbu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * & 数据库处理类
 * 
 * @author Administrator
 *
 */
public class DBUtil {
	private Connection conn;
	private Statement sta;
	private ResultSet rs;
	private final String USERNAME="root";//用户名
	private final String PASSWORD="root";//密码
	private final String URL="jdbc:mysql://localhost:3306/trip?characterEncoding=utf-8";//访问服务器的协议://地址:端口号//数据库?字符编码方式
	
	public DBUtil(){
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setConnection(){
		try {
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setSta(){
		if(conn==null){
			setConnection();
		}
		try {
			sta=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 执行更新的SQL语句
	
	public boolean executeUpdate(String sql){
		boolean bol=false;
		if(sta==null){
			setSta();
		}
		try {
			bol=sta.executeUpdate(sql)>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//关闭数据库连接
			close();
		}
		return bol;
	}
	// 执行查询的SQL语句
	public ResultSet executeQuery(String sql){
		if(sta==null){
			setSta();
		}
		try {
			rs=sta.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 关闭数据库连接
	 */
	public void close(){
		try{
		if(rs!=null){
			rs.close();
		}
		if(sta!=null){
			sta.close();
		}
		if(conn!=null){
			conn.close();
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
}
