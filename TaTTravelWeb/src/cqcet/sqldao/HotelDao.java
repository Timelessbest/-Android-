package cqcet.sqldao;
/**
 * 数据库添加,查询,删除,修改
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cqcet.sqldbu.DBUtil;

public class HotelDao {
	public static ResultSet rs;
	


	// 查询并存所有酒店信息入list中  String typeName
	public List<Hotel> queryTypeName1(String typeName)  {
		List<Hotel>  list = new ArrayList<Hotel>();
		String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image "
				+ "FROM hotel AS j,typehoteltb WHERE typeid=typehoteltb.id  AND typehoteltb.name='"+typeName+"'";  //查询语句
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
	// 查询并存所有酒店信息入list中  String typeName
	public List<Hotel> queryTypeName(String typeName,String dizhi)  {
		List<Hotel>  list = new ArrayList<Hotel>();
//		String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image FROM jingdian AS j,typetb,citytb WHERE typeid=typetb.id  AND  citytb.name LIKE'"+dizhi+"' AND cityid=citytb.id AND typetb.name='"+typeName+"' ";
		String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image FROM hotel AS j,typehoteltb,citytb WHERE typeid=typehoteltb.id  AND  citytb.name LIKE  '"+dizhi+"' AND cityid=citytb.id AND typehoteltb.name='"+typeName+"'";  //查询语句
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
	
	// 查询并对应类型酒店信息存入list中  
		public List<Hotel> queryType(String type)  {
			List<Hotel>  list = new ArrayList<Hotel>();
			String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image "
					+ "FROM hotel AS j,typehoteltb WHERE typeid=typehoteltb.id  AND typehoteltb.name='"+type+"'";  //查询语句
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
