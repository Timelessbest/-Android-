package cqcet.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cqcet.sqldao.Hotel;
import cqcet.sqldao.PlanDao;

/**
 * Servlet implementation class PlaneStr
 */
public class PlaneStr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaneStr() {
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
//		String typeName = "自由行";
		//对获取到的中文字符进行转码处理
		String strl = new String(typeName.getBytes("ISO-8859-1"),"utf-8");
		List<Hotel> list= new PlanDao().query1(strl);
		System.out.println("str="+strl);
		Gson g = new Gson();
		String str = g.toJson(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(str);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
