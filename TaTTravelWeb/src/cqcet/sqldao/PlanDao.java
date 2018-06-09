package cqcet.sqldao;
/**
 * ���ݿ����,��ѯ,ɾ��,�޸�
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cqcet.sqldbu.DBUtil;

public class PlanDao {
	public static ResultSet rs;
	

	// ��ѯ��Ʊ��Ϣ������list��  String typeName
		public List<Hotel> query1(String typeName)  {
			List<Hotel>  list = new ArrayList<Hotel>();
			String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image "
					+ "FROM plane AS j,typeplanetb WHERE typeid=typeplanetb.id  AND typeplanetb.name='"+typeName+"'";  //��ѯ���
			DBUtil dbUtil = new DBUtil();
			rs = dbUtil.executeQuery(sql);
			try {
				while (rs.next()) {
					Hotel hotel = new Hotel();
					hotel.setId(rs.getInt("id"));
					hotel.setName(rs.getString("name"));
					hotel.setContent(rs.getString("content"));
					hotel.setPrice(rs.getFloat("price"));
					hotel.setTelephone(rs.getString("telephone"));
					hotel.setImage(rs.getString("image"));
					
					list.add(hotel);

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

	// ��ѯ��Ʊ��Ϣ������list��  String typeName
	public List<Hotel> query(String typeName,String dizhi )  {
//		String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image FROM jingdian AS j,typetb,citytb WHERE typeid=typetb.id  AND  citytb.name LIKE'"+dizhi+"' AND cityid=citytb.id AND typetb.name='"+typeName+"' ";
		List<Hotel>  list = new ArrayList<Hotel>();
		String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image FROM plane AS j,typeplanetb,citytb WHERE typeid=typeplanetb.id  AND  citytb.name LIKE '"+dizhi+"' AND cityid=citytb.id AND typeplanetb.name='"+typeName+"'";
  //��ѯ���
		DBUtil dbUtil = new DBUtil();
		rs = dbUtil.executeQuery(sql);
		try {
			while (rs.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(rs.getInt("id"));
				hotel.setName(rs.getString("name"));
				hotel.setContent(rs.getString("content"));
				hotel.setPrice(rs.getFloat("price"));
				hotel.setTelephone(rs.getString("telephone"));
				hotel.setImage(rs.getString("image"));
				
				list.add(hotel);

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
	
	
}
