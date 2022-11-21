package controller.MSTKOKYAKU;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KokyakuDAO;
import model.Mstkokyaku;

/**
 * Servlet implementation class Create
 */
@WebServlet("/CreateMSTKOKYAKU")
public class CreateMSTKOKYAKU extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/mstkokyaku/createmstkokyaku.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");			
		String kokyaku_code=request.getParameter("KOKYAKU_CODE");
		String kokyaku_name=request.getParameter("KOKYAKU_NAME");
		String kokyaku_address=request.getParameter("KOKYAKU_ADDRESS");
		String kokyaku_tel=request.getParameter("KOKYAKU_TEL");
				
		Mstkokyaku mstkokyaku=new Mstkokyaku(kokyaku_code,kokyaku_name,kokyaku_address,kokyaku_tel);
		
		mstkokyaku.setKokyaku_code(kokyaku_code);
		mstkokyaku.setKokyaku_name(kokyaku_name);
		mstkokyaku.setKokyaku_address(kokyaku_address);
		mstkokyaku.setKokyaku_tel(kokyaku_tel);
		request.setAttribute("mstkokyaku",mstkokyaku);
		
		KokyakuDAO ld=new KokyakuDAO();
		
		String dummyname = ("");
		List<Mstkokyaku> list=ld.searchAll(kokyaku_code,dummyname);
		RequestDispatcher rd;
		if(list.isEmpty()) {
			ld.insertOne(mstkokyaku);
			rd=request.getRequestDispatcher("/WEB-INF/lib/view/mstkokyaku/kanryomstkokyaku.jsp");
			rd.forward(request, response);				
		}else {
			rd=request.getRequestDispatcher("/WEB-INF/lib/view/mstkokyaku/uniqueerror_createmstkokyaku.jsp");
			rd.forward(request, response);	
		}
	}

}