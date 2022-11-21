package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Turiage;

public class UriageDAO {
	private Connection db = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
		
//    //ホスト名
//    String hostname = "163.149.99.38";
//    //SID
//    String sid = "oracle";
//    //ユーザー名
//    String username = "kenshu";
//    //パスワード
//    String passwd = "kenshu";	
//    
//    private void connect() throws NamingException, SQLException {	
//
//        try {
//        	
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        db = DriverManager.getConnection(
//                "jdbc:oracle:thin:@" + hostname + ":1521:" + sid,username,passwd);
//        
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    }  
//    	private void disconnect() {
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//			if (ps != null) {
//				ps.close();
//			}
//			if (db != null) {
//				db.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
    	
	
	
	//接続共通処理
	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Test-DB");
		this.db = ds.getConnection();
	}

	//切断共通処理
	private void disconnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (db != null) {
				db.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	String sql;
	
//	ボックス用のリスト
	public List<Turiage> listAll() {
		List<Turiage> list2 = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM T_URIAGE");
			rs = ps.executeQuery();
			while (rs.next()) {
				int uriageno = rs.getInt("URIAGE_NO");
				Date uriagedate = rs.getDate("URIAGE_DATE");
				String kokyakucode = rs.getString("KOKYAKU_CODE");
				String seihincode = rs.getString("SEIHIN_CODE");
				int uriagesuryo = rs.getInt("URIAGE_SURYO");
				int uriage = rs.getInt("URIAGE");
//				Mstseihin l = new Mstseihin(seihincode,seihinname,seihingenka,seihinteika);
//				list.add(l);			
				Turiage l2 = new Turiage(
						uriageno,uriagedate,kokyakucode,seihincode,uriagesuryo,uriage
						);
				l2.setUriageno(uriageno);
				l2.setUriagedate(uriagedate);
				l2.setKokyakucode(kokyakucode);
				l2.setSeihincode(seihincode);
				l2.setUriagesuryo(uriagesuryo);
				l2.setUriage(uriage);
				list2.add(l2);

			}
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		}finally {
			this.disconnect();
		}

		return list2;
	}

	

	public List<Turiage> findAll() {
		List<Turiage> list = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM T_URIAGE");
			rs = ps.executeQuery();
			while (rs.next()) {
				int uriageno = rs.getInt("URIAGE_NO");
				Date uriagedate = rs.getDate("URIAGE_DATE");
				String kokyakucode = rs.getString("KOKYAKU_CODE");
				String seihincode = rs.getString("SEIHIN_CODE");
				int uriagesuryo = rs.getInt("URIAGE_SURYO");
				int uriage = rs.getInt("URIAGE");
//				Mstseihin l = new Mstseihin(seihincode,seihinname,seihingenka,seihinteika);
//				list.add(l);			
				Turiage l = new Turiage(
						uriageno,uriagedate,kokyakucode,seihincode,uriagesuryo,uriage
						);
				l.setUriageno(uriageno);
				l.setUriagedate(uriagedate);
				l.setKokyakucode(kokyakucode);
				l.setSeihincode(seihincode);
				l.setUriagesuryo(uriagesuryo);
				l.setUriage(uriage);
				list.add(l);

			}
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		}finally {
			this.disconnect();
		}

		return list;
	}
	
	
	
	public List<Turiage> searchAll(String searchno,java.sql.Date searchdateST2,java.sql.Date searchdateEN2,String searchkokyaku,String searchseihin) {
		List<Turiage> list = new ArrayList<>();
		String afterconcatSQL;
		try {
			this.connect();
			int searchno2 = 0;
				if (searchno != "") {
					searchno2=Integer.parseInt(searchno);
					sql = ("SELECT * FROM T_URIAGE WHERE URIAGE_NO = ? AND KOKYAKU_CODE LIKE ? AND SEIHIN_CODE LIKE ? ");
						if(searchdateST2 != null && searchdateEN2 != null) {
							afterconcatSQL = sql.concat("AND URIAGE_DATE BETWEEN ? AND ?");
							ps = db.prepareStatement(afterconcatSQL);	
							ps.setDate(4,searchdateST2);
							ps.setDate(5,searchdateEN2);
						}else if (searchdateST2 != null){
							afterconcatSQL = sql.concat("AND URIAGE_DATE >= ?");
							ps = db.prepareStatement(afterconcatSQL);	
							ps.setDate(4,searchdateST2);
						}else if (searchdateEN2 != null){
							afterconcatSQL = sql.concat("AND URIAGE_DATE <= ?");
							ps = db.prepareStatement(afterconcatSQL);
							ps.setDate(4,searchdateEN2);
						}else {
							ps = db.prepareStatement(sql);	
						}
					ps.setInt(1,searchno2);
					ps.setString(2,"%"+searchkokyaku+"%");
					ps.setString(3,"%"+searchseihin+"%");	
					rs = ps.executeQuery();
				}else {
					sql = ("SELECT * FROM T_URIAGE WHERE KOKYAKU_CODE LIKE ? AND SEIHIN_CODE LIKE ? ");
						if(searchdateST2 != null && searchdateEN2 != null) {
							afterconcatSQL = sql.concat("AND URIAGE_DATE BETWEEN ? AND ?");
							ps = db.prepareStatement(afterconcatSQL);	
							ps.setDate(3,searchdateST2);
							ps.setDate(4,searchdateEN2);
						}else if (searchdateST2 != null){
							afterconcatSQL = sql.concat("AND URIAGE_DATE >= ?");
							ps = db.prepareStatement(afterconcatSQL);	
							ps.setDate(3,searchdateST2);
						}else if (searchdateEN2 != null){
							afterconcatSQL = sql.concat("AND URIAGE_DATE <= ?");
							ps = db.prepareStatement(afterconcatSQL);
							ps.setDate(3,searchdateEN2);
						}else {
							ps = db.prepareStatement(sql);	
						}
					ps.setString(1,"%"+searchkokyaku+"%");
					ps.setString(2,"%"+searchseihin+"%");	
					rs = ps.executeQuery();
				}

			while (rs.next()) {
				int uriageno = rs.getInt("URIAGE_NO");
				Date uriagedate = rs.getDate("URIAGE_DATE");
				String kokyakucode = rs.getString("KOKYAKU_CODE");
				String seihincode = rs.getString("SEIHIN_CODE");
				int uriagesuryo = rs.getInt("URIAGE_SURYO");
				int uriage = rs.getInt("URIAGE");
			
				Turiage l = new Turiage(
						uriageno,uriagedate,kokyakucode,seihincode,uriagesuryo,uriage
						);
				l.setUriageno(uriageno);
				l.setUriagedate(uriagedate);
				l.setKokyakucode(kokyakucode);
				l.setSeihincode(seihincode);
				l.setUriagesuryo(uriagesuryo);
				l.setUriage(uriage);
				list.add(l);

			}
		} catch (NamingException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			this.disconnect();
		}

		return list;
	}
	
	
	
	public void insertOne(Turiage tsuika) {
		try {
			this.connect();
			
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");		
			String formattedDate = sdFormat.format(tsuika.getUriagedate());
			java.sql.Date uriagedatetoSQL = java.sql.Date.valueOf(formattedDate);
			
			if((tsuika.getUriagesuryo() == 0 && tsuika.getUriage() == 0) || (tsuika.getUriagesuryo() != 0 && tsuika.getUriage() != 0)) {
			
				ps=db.prepareStatement("INSERT INTO T_URIAGE(URIAGE_DATE,KOKYAKU_CODE,SEIHIN_CODE,URIAGE_SURYO,URIAGE) VALUES(?,?,?,?,?)");
				ps.setDate(1,uriagedatetoSQL);
				ps.setString(2,tsuika.getKokyakucode());
				ps.setString(3,tsuika.getSeihincode());
				ps.setInt(4,tsuika.getUriagesuryo());
				ps.setInt(5,tsuika.getUriage());			
				ps.executeUpdate();
				
			}else if(tsuika.getUriagesuryo() != 0 && tsuika.getUriage() == 0) {
				int seihinteika = 0;
				ps=db.prepareStatement("SELECT SEIHIN_TEIKA FROM MST_SEIHIN WHERE SEIHIN_CODE = ?");
				ps.setString(1,tsuika.getSeihincode());
				rs = ps.executeQuery();
				
				while (rs.next()) {
					seihinteika = rs.getInt("SEIHIN_TEIKA");
				}
			
				ps=db.prepareStatement("INSERT INTO T_URIAGE(URIAGE_DATE,KOKYAKU_CODE,SEIHIN_CODE,URIAGE_SURYO,URIAGE)VALUES(?,?,?,?,? * ?)");
				ps.setDate(1,uriagedatetoSQL);
				ps.setString(2,tsuika.getKokyakucode());
				ps.setString(3,tsuika.getSeihincode());
				ps.setInt(4,tsuika.getUriagesuryo());
				ps.setInt(5,seihinteika);
				ps.setInt(6,tsuika.getUriagesuryo());
				ps.executeUpdate();
			}
			
		
			
			ps=db.prepareStatement("UPDATE T_ZAIKO SET ZAIKO_SURYO = ZAIKO_SURYO - ? WHERE SEIHIN_CODE = ?");
			ps.setString(2,tsuika.getSeihincode());
			ps.setInt(1,tsuika.getUriagesuryo());	
			ps.executeUpdate();
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	
	
//	public Mstseihin findOne(String seihincode) {
//		Mstseihin mstseihin=null;
//		try {
//			this.connect();
//			ps=db.prepareStatement("SELECT * FROM MST_SEIHIN WHERE SEIHIN_CODE=?");
//			ps.setString(1, seihincode);
//			rs=ps.executeQuery();
//			if(rs.next()) {
//				seihincode=rs.getString("SEIHIN_CODE");
//				String seihinname=rs.getString("SEIHIN_NAME");
//				int seihingenka=rs.getInt("SEIHIN_GENKA");
//				int seihinteika=rs.getInt("SEIHIN_TEIKA");
//				mstseihin=new Mstseihin(
//						seihincode,seihinname,seihingenka,seihinteika
//						);
//				mstseihin.setSeihincode(seihincode);
//				mstseihin.setSeihinname(seihinname);
//				mstseihin.setSeihingenka(seihingenka);
//				mstseihin.setSeihinteika(seihinteika);
//			}
//		} catch (NamingException | SQLException e) {
//			e.printStackTrace();
//		}finally {
//			this.disconnect();
//		}
//
//		return mstseihin;
//	}
//	public void updateOne(Mstseihin koushin) {
//		try {
//			this.connect();
//			ps=db.prepareStatement("UPDATE MST_SEIHIN SET SEIHIN_NAME=?,SEIHIN_GENKA=?,SEIHIN_TEIKA=? WHERE SEIHIN_CODE=?");
//			ps.setString(1, koushin.getSeihinname());
//			ps.setInt(2, koushin.getSeihingenka());
//			ps.setInt(3, koushin.getSeihinteika());
//			ps.setString(4, koushin.getSeihincode());
//			ps.executeUpdate();
//		} catch (NamingException | SQLException e) {
//			e.printStackTrace();
//		}finally {
//			this.disconnect();
//		}
//	}
//
//	
	public void deleteOne(String uriageno) {
		try {
			this.connect();
			String seihincode = null;
			int uriagesuryo = 0;
			
			int uriageno2=Integer.parseInt(uriageno);
			ps=db.prepareStatement("SELECT * FROM T_URIAGE WHERE URIAGE_NO=?");
			ps.setInt(1, uriageno2);
			rs=ps.executeQuery();			
			if(rs.next()) {
				seihincode=rs.getString("SEIHIN_CODE");
				uriagesuryo=rs.getInt("URIAGE_SURYO");
			}
			
			ps=db.prepareStatement("UPDATE T_ZAIKO SET ZAIKO_SURYO = ZAIKO_SURYO + ? WHERE SEIHIN_CODE = ?");
			ps.setInt(1, uriagesuryo);
			ps.setString(2, seihincode);
			ps.executeUpdate();	
				
			ps=db.prepareStatement("DELETE FROM T_URIAGE WHERE URIAGE_NO = ?");
			ps.setString(1, uriageno);
			ps.executeUpdate();
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}

}