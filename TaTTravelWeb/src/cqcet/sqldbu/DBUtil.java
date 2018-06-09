package cqcet.sqldbu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * & ���ݿ⴦����
 * 
 * @author Administrator
 *
 */
public class DBUtil {
	private Connection conn;
	private Statement sta;
	private ResultSet rs;
	private final String USERNAME="root";//�û���
	private final String PASSWORD="root";//����
	private final String URL="jdbc:mysql://localhost:3306/trip?characterEncoding=utf-8";//���ʷ�������Э��://��ַ:�˿ں�//���ݿ�?�ַ����뷽ʽ
	
	public DBUtil(){
		try {
			//��������
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
	
	// ִ�и��µ�SQL���
	
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
			//�ر����ݿ�����
			close();
		}
		return bol;
	}
	// ִ�в�ѯ��SQL���
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
	 * �ر����ݿ�����
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
