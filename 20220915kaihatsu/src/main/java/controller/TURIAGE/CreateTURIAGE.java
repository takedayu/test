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

import dao.KokyakuDAO;
import dao.UriageDAO;
import dao.ZaikoDAO;
import model.Mstkokyaku;
import model.Turiage;
import model.Tzaiko;

/**
 * Servlet implementation class Create
 */
@WebServlet("/CreateTURIAGE")
public class CreateTURIAGE extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		UriageDAO dao2=new UriageDAO();
		List<Turiage> list2=dao2.listAll();
		request.setAttribute("list2", list2);

//		SeihinDAO dao3=new SeihinDAO();
//		List<Mstseihin> seihinlist=dao3.listAll();
//		request.setAttribute("seihinlist", seihinlist);
		
		ZaikoDAO dao3=new ZaikoDAO();
		List<Tzaiko> zaikolist=dao3.listAll();
		request.setAttribute("zaikolist", zaikolist);
		
		KokyakuDAO dao4=new KokyakuDAO();
		List<Mstkokyaku> kokyakulist=dao4.listAll();
		request.setAttribute("kokyakulist", kokyakulist);
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/view/turiage/createturiage.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uriagedate=request.getParameter("URIAGE_DATE");
		String kokyakucode=request.getParameter("KOKYAKU_CODE");
		String seihincode=request.getParameter("SEIHIN_CODE");
		String uriagesuryo=request.getParameter("URIAGE_SURYO");
		String uriage=request.getParameter("URIAGE");	
		java.util.Date uriagedate2 = null;
//		java.sql.Date uriagedatetoSQL = null;
		int uriagesuryo2;
		int uriage2; 
		int dummyno = 0;
		
		try{
		uriagesuryo2 = Integer.parseInt(uriagesuryo);			
		} catch(Exception e){
		uriagesuryo2 = 0;
		} 
		
		try{
		uriage2 = Integer.parseInt(uriage);			
		} catch(Exception e){
		uriage2 = 0;
		} 
		
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");		
			uriagedate2=sdFormat.parse(uriagedate);
//			String formattedDate = sdFormat.format(uriagedate2);
//			uriagedatetoSQL = java.sql.Date.valueOf(formattedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		Turiage tsuika=new Turiage(dummyno,uriagedate2,kokyakucode,seihincode,uriagesuryo2,uriage2);
		tsuika.setUriageno(dummyno);
		tsuika.setUriagedate(uriagedate2);
		tsuika.setKokyakucode(kokyakucode);
		tsuika.setSeihincode(seihincode);
		tsuika.setUriagesuryo(uriagesuryo2);
		tsuika.setUriage(uriage2);
//		request.setAttribute("tsuika", tsuika);
		
		if(uriagedate2 == null || kokyakucode == "" || seihincode == "") {
			response.sendRedirect("/20220915kaihatsu/CreateTURIAGE");
		}else {	
		UriageDAO ld=new UriageDAO();
		ld.insertOne(tsuika);

//		response.sendRedirect("/20220915kaihatsu/ReadMSTSEIHIN");
		RequestDispatcher rd;

			rd=request.getRequestDispatcher("/WEB-INF/lib/view/turiage/kanryoturiage.jsp");
			rd.forward(request, response);
		}
		
	}

}
