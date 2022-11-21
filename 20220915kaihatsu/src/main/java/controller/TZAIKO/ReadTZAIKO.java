package controller.TZAIKO;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ZaikoDAO;
import model.Tzaiko;

	@WebServlet("/ReadTZAIKO")
	public class ReadTZAIKO extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(

					);
			ZaikoDAO dao=new ZaikoDAO();
			List<Tzaiko> list=dao.findAll();
			ZaikoDAO dao2=new ZaikoDAO();
			List<Tzaiko> list2=dao2.listAll();
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/tzaiko/readtzaiko.jsp");
			rd.forward(request, response);
			session.invalidate();			

		}

	}
