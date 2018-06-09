package cqcet.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cqcet.sqldao.Hotel;
import cqcet.sqldao.HotelDao;

/**
 * Servlet implementation class HotelStr
 */
public class HotelStr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelStr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String typeName = request.getParameter("typeName");
//		String type = request.getParameter("type");
//		String typeName = "������";
		//�Ի�ȡ���������ַ�����ת�봦��
		String strl = new String(typeName.getBytes("ISO-8859-1"),"utf-8");
//		String strtype = new String(type.getBytes("ISO-8859-1"),"utf-8");
		List<Hotel> list= new HotelDao().queryTypeName1(strl);
//		List<Hotel> list1= new HotelDao().queryTypeName(strtype);
		System.out.println("str="+strl);
		Gson g = new Gson();
		String str = g.toJson(list);
//		String str1 = g.toJson(list1);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(str);
//		response.getWriter().print(str1);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
