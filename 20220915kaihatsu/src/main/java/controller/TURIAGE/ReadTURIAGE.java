package controller.TURIAGE;

import java.io.IOException;
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

	@WebServlet("/ReadTURIAGE")
	public class ReadTURIAGE extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(

					);
			UriageDAO dao=new UriageDAO();
			List<Turiage> list=dao.findAll();
			UriageDAO dao2=new UriageDAO();
			List<Turiage> list2=dao2.listAll();
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);

			
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/turiage/readturiage.jsp");
			rd.forward(request, response);
			session.invalidate();			

		}

	}
