package controller.MSTSEIHIN;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SeihinDAO;
import model.Mstseihin;

	@WebServlet("/ReadMSTSEIHIN")
	public class ReadMSTSEIHIN extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(

					);
			SeihinDAO dao=new SeihinDAO();
			List<Mstseihin> list=dao.findAll();
			SeihinDAO dao2=new SeihinDAO();
			List<Mstseihin> list2=dao2.listAll();
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/readmstseihin.jsp");
			rd.forward(request, response);
			session.invalidate();			

		}

	}
