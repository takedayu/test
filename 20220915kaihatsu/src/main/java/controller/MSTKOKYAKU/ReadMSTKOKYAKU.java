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

@WebServlet("/ReadMSTKOKYAKU")
public class ReadMSTKOKYAKU extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		KokyakuDAO dao=new KokyakuDAO();
		List<Mstkokyaku> list=dao.findAll();
		KokyakuDAO dao2=new KokyakuDAO();
		List<Mstkokyaku> list2=dao2.listAll();
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/mstkokyaku/readmstkokyaku.jsp");
		rd.forward(request, response);
		session.invalidate();			

	}	
		
}


