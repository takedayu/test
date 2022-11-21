package controller.TURIAGE;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UriageDAO;
import model.Turiage;

	@WebServlet("/SearchTURIAGE")
	public class SearchTURIAGE extends HttpServlet {
		private static final long serialVersionUID = 1L;


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		    request.setCharacterEncoding("UTF-8");

			HttpSession session = request.getSession(true);

//			int searchno=Integer.parseInt(request.getParameter("searchno"));
			String searchno=request.getParameter("searchno");
			
			java.util.Date searchdate = null;
			java.sql.Date searchdateST2 = null;
			java.sql.Date searchdateEN2 = null;
			try {
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");	
				
				if (request.getParameter("searchdateST") != "") {
				searchdate=sdFormat.parse(request.getParameter("searchdateST"));
				String formattedDate = sdFormat.format(searchdate);
		        searchdateST2 = java.sql.Date.valueOf(formattedDate);
				}
				
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			try {
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");		
				
				if (request.getParameter("searchdateEN") != "") {
				searchdate=sdFormat.parse(request.getParameter("searchdateEN"));
				String formattedDate = sdFormat.format(searchdate);
		        searchdateEN2 = java.sql.Date.valueOf(formattedDate);
				}

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		
			String searchkokyaku=request.getParameter("searchkokyaku");
			String searchseihin=request.getParameter("searchseihin");

			session.setAttribute("searchno", searchno);		
			session.setAttribute("searchdateST", searchdateST2);	
			session.setAttribute("searchdateEN", searchdateEN2);	
			session.setAttribute("searchkokyaku", searchkokyaku);		
			session.setAttribute("searchseihin", searchseihin);	
						
//			String searchname2 = (String)session.getAttribute("searchname");
//			String searchcode2 = (String)session.getAttribute("searchcode");
			UriageDAO dao=new UriageDAO();
			List<Turiage> list=dao.searchAll(searchno,searchdateST2,searchdateEN2,searchkokyaku,searchseihin);
			session.setAttribute("list", list);
			UriageDAO dao2=new UriageDAO();
			List<Turiage> list2=dao2.listAll();
			session.setAttribute("list2", list2);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/turiage/searchturiage.jsp");
			rd.forward(request, response);
//			response.sendRedirect("/20220915kaihatsu/ReadMSTSEIHIN");
			
		}

	}
