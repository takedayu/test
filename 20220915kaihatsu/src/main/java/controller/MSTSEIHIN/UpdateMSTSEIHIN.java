package controller.MSTSEIHIN;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeihinDAO;
import model.Mstseihin;

/**
 * Servlet implementation class Update
 */
@WebServlet("/UpdateMSTSEIHIN")
public class UpdateMSTSEIHIN extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s_id=request.getParameter("SEIHIN_CODE");
		if(s_id==null) {
			response.sendRedirect("/20220915kaihatsu/ReadMSTSEIHIN");
		}else {
			SeihinDAO dao=new SeihinDAO();
			Mstseihin koushin=dao.findOne(s_id);
			request.setAttribute("koushin", koushin);
			request.getRequestDispatcher("/WEB-INF/lib/view/updatemstseihin.jsp").forward(request, response);
		}
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
//		int seihingenka2 = Integer.parseInt(seihingenka);
//		int seihinteika2 = Integer.parseInt(seihinteika);
		Mstseihin koushin=new Mstseihin(seihincode,seihinname,seihingenka2,seihinteika2);
		koushin.setSeihincode(seihincode);
		koushin.setSeihinname(seihinname);
		koushin.setSeihingenka(seihingenka2);
		koushin.setSeihinteika(seihinteika2);
		SeihinDAO dao=new SeihinDAO();
		dao.updateOne(koushin);
//		response.sendRedirect("/20220915kaihatsu/ReadMSTSEIHIN");
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/kanryomstseihin.jsp");
		rd.forward(request, response);

	}

}
