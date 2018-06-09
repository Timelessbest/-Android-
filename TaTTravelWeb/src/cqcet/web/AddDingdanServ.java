package cqcet.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cqcet.sqldao.DingDanDao;

/**客户端返回信息到服务器将所提交的订单信息添加到我的订单中
 * Servlet implementation class AddDingdanServ
 */
public class AddDingdanServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDingdanServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String telephone = request.getParameter("telephone");
		String image = request.getParameter("image");
		String username = request.getParameter("username");
		Float pricestrs=Float.parseFloat(price);
		int useridstr=Integer.parseInt(username);
		
//		String usernamestr = new String(username.getBytes("ISO-8859-1"),"utf-8");
		String namestr = new String(name.getBytes("ISO-8859-1"),"utf-8");
	String contentstr = new String(content.getBytes("ISO-8859-1"),"utf-8");
//		String pricestr = new String(price.getBytes("ISO-8859-1"),"utf-8");
//		String telephonestr = new String(telephone.getBytes("ISO-8859-1"),"utf-8");
//		String imagestr = new String(image.getBytes("ISO-8859-1"),"utf-8");
		
		DingDanDao dao = new DingDanDao();
		if (dao.addDindan(namestr, contentstr, pricestrs, telephone, image, useridstr)) {
			response.getWriter().print("ok");
		}else{
			response.getWriter().print("no");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
