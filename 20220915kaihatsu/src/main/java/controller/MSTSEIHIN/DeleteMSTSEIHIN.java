package controller.MSTSEIHIN;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SeihinDAO;
import model.Mstseihin;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/DeleteMSTSEIHIN")
public class DeleteMSTSEIHIN extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String id=request.getParameter("SEIHIN_CODE");
		if(id !=null) {
			SeihinDAO dao=new SeihinDAO();
			dao.deleteOne(id);
			
			String dummyname = ("");
			List<Mstseihin> list3=dao.searchAll(dummyname,id);
			session.setAttribute("list3", list3);
			
		}
		response.sendRedirect("/20220915kaihatsu/ReadMSTSEIHIN");
	}



}
