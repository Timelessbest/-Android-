package cqcet.sqldao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cqcet.sqldbu.DBUtil;

public class DingDanDao {
	ResultSet rs;
	
	// ��������¼���û���ѯ���û��Ķ�����Ϣ
		public List<DingDan> query(String username)  {
			List<DingDan>  list = new ArrayList<DingDan>();
			String sql = "SELECT d.id,d.name,d.content,d.price,d.telephone,d.image "
					+ "FROM dingdan AS d,userinfo WHERE userid=userinfo.id  AND userinfo.username='"+username+"'";  //��ѯ���
			DBUtil dbUtil = new DBUtil();
			rs = dbUtil.executeQuery(sql);
			try {
				while (rs.next()) {
					DingDan dingdan = new DingDan();
					 
					dingdan.setName(rs.getString("name"));
					dingdan.setContent(rs.getString("content"));
					dingdan.setPrice(rs.getFloat("price"));
					dingdan.setTelephone(rs.getString("telephone"));
					dingdan.setImage(rs.getString("image"));
					
				
					
					list.add(dingdan);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				dbUtil.close();
			}
			return list;

		}
		
		// ����û��ύ�Ķ�����Ϣ�����ݿ�
		public boolean addDindan(String name ,String content,float price,String telephone,String image,int userid) {// ���

			String sql = "insert into dingdan (name,content,price,telephone,image,userid) values('"+name+"','"+content+"','"+price+"','"+telephone+"','"+image+"','"+userid+"')";
			return new DBUtil().executeUpdate(sql);
		}
}
