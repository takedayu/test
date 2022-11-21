package controller.MSTSEIHIN;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeihinDAO;
import model.Mstseihin;

/**
 * Servlet implementation class Create
 */
@WebServlet("/CreateMSTSEIHIN")
public class CreateMSTSEIHIN extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/createmstseihin.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String seihincode=request.getParameter("SEIHIN_CODE");
		String seihinname=request.getParameter("SEIHIN_NAME");
		String seihingenka=request.getParameter("SEIHIN_GENKA");
		String seihinteika=request.getParameter("SEIHIN_TEIKA");
		int seihingenka2;
		int seihinteika2; 
		
		try{
		seihingenka2 = Integer.parseInt(seihingenka);			
		} catch(Exception e){
		seihingenka2 = 0;
		} 
		
		try{
		seihinteika2 = Integer.parseInt(seihinteika);			
		} catch(Exception e){
		seihinteika2 = 0;
		} 
		
		Mstseihin tsuika=new Mstseihin(seihincode,seihinname,seihingenka2,seihinteika2);		
		tsuika.setSeihincode(seihincode);
		tsuika.setSeihinname(seihinname);
		tsuika.setSeihingenka(seihingenka2);
		tsuika.setSeihinteika(seihinteika2);
		request.setAttribute("tsuika", tsuika);
		
		SeihinDAO ld=new SeihinDAO();
		
		String dummyname = ("");
		List<Mstseihin> list=ld.searchAll(dummyname,seihincode);
		RequestDispatcher rd;
		if(list.isEmpty()) {
			ld.insertOne(tsuika);		
			rd=request.getRequestDispatcher("/WEB-INF/lib/view/kanryomstseihin.jsp");
			rd.forward(request, response);
		}else {
			rd=request.getRequestDispatcher("/WEB-INF/lib/view/uniqueerror_createmstseihin.jsp");
			rd.forward(request, response);
		}
	}
}
