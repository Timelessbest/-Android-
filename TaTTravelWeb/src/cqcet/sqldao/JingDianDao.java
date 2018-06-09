package cqcet.sqldao;
/**
 * 数据库添加,查询,删除,修改
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cqcet.sqldbu.DBUtil;

public class JingDianDao {
	public static ResultSet rs;
	

	// 查询并存入list中  String typeName
		public List<JingDian> query1(String typeName)  {
			List<JingDian>  list = new ArrayList<JingDian>();
			String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image "
					+ "FROM jingdian AS j,typetb WHERE typeid=typetb.id  AND typetb.name='"+typeName+"'";  //查询语句
			DBUtil dbUtil = new DBUtil();
			rs = dbUtil.executeQuery(sql);
			try {
				while (rs.next()) {
					JingDian jd = new JingDian();
					jd.setId(rs.getInt("id"));
					jd.setName(rs.getString("name"));
					jd.setContent(rs.getString("content"));
					jd.setPrice(rs.getFloat("price"));
					jd.setTelephone(rs.getString("telephone"));
					jd.setImage(rs.getString("image"));
					
				
					
					list.add(jd);

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

	// 查询并存入list中  String typeName
	public List<JingDian> query(String typeName,String dizhi)  {
//		SELECT j.id,j.name,j.content,j.price,j.telephone,j.image FROM jingdian AS j,typetb,citytb WHERE typeid=typetb.id  AND  citytb.name='泸州' AND cityid=citytb.id AND typetb.name='自由行'
//		SELECT j.id,j.name,j.content,j.price,j.telephone,j.image FROM jingdian AS j,typetb,citytb WHERE typeid=typetb.id  AND  citytb.name LIKE'%' AND cityid=citytb.id AND typetb.name='自由行'
		List<JingDian>  list = new ArrayList<JingDian>();
		String sql = "SELECT j.id,j.name,j.content,j.price,j.telephone,j.image FROM jingdian AS j,typetb,citytb WHERE typeid=typetb.id  AND  citytb.name LIKE '"+dizhi+"' AND cityid=citytb.id AND typetb.name='"+typeName+"'"; //查询语句
		DBUtil dbUtil = new DBUtil();
		rs = dbUtil.executeQuery(sql);
		try {
			while (rs.next()) {
				JingDian jd = new JingDian();
				jd.setId(rs.getInt("id"));
				jd.setName(rs.getString("name"));
				jd.setContent(rs.getString("content"));
				jd.setPrice(rs.getFloat("price"));
				jd.setTelephone(rs.getString("telephone"));
				jd.setImage(rs.getString("image"));
				
			
				
				list.add(jd);

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
