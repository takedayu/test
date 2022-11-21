package controller.MSTKOKYAKU;



import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KokyakuDAO;
import model.Mstkokyaku;



@WebServlet("/SearchMSTKOKYAKU")
public class SearchMSTKOKYAKU extends HttpServlet {
    private static final long serialVersionUID = 1L;




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	    request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(true);
		
		String searchname=request.getParameter("searchname");
		String searchcode=request.getParameter("searchcode");

		session.setAttribute("searchname", searchname);		
		session.setAttribute("searchcode", searchcode);				
		
//		String searchname2 = (String)session.getAttribute("searchname");
//		String searchcode2 = (String)session.getAttribute("searchcode");
		KokyakuDAO dao=new KokyakuDAO();
		List<Mstkokyaku> list=dao.searchAll(searchcode,searchname);
		session.setAttribute("list", list);
		KokyakuDAO dao2=new KokyakuDAO();
		List<Mstkokyaku> list2=dao2.listAll();
		session.setAttribute("list2", list2);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/mstkokyaku/searchmstkokyaku.jsp");
		rd.forward(request, response);
//		response.sendRedirect("/20220915kaihatsu/ReadMSTSEIHIN");
		
	}
    
}