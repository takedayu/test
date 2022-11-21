package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Tzaiko;

public class ZaikoDAO {
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
	
	
//	ボックス用のリスト
	public List<Tzaiko> listAll() {
		List<Tzaiko> list2 = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM T_ZAIKO INNER JOIN MST_SEIHIN ON T_ZAIKO.SEIHIN_CODE = MST_SEIHIN.SEIHIN_CODE");
			rs = ps.executeQuery();
			while (rs.next()) {
				int zaikono = rs.getInt("ZAIKO_NO");
				String seihincode = rs.getString("SEIHIN_CODE");
				String seihinname = rs.getString("SEIHIN_NAME");
				int zaikosuryo = rs.getInt("ZAIKO_SURYO");
//				Mstseihin l = new Mstseihin(seihincode,seihinname,seihingenka,seihinteika);
//				list.add(l);			
				Tzaiko l2 = new Tzaiko(
						zaikono,seihincode,seihinname,zaikosuryo
						);
				l2.setZaikono(zaikono);
				l2.setSeihincode(seihincode);
				l2.setSeihinname(seihinname);
				l2.setZaikosuryo(zaikosuryo);
				list2.add(l2);

			}
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		}finally {
			this.disconnect();
		}

		return list2;
	}

	

	public List<Tzaiko> findAll() {
		List<Tzaiko> list = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM T_ZAIKO INNER JOIN MST_SEIHIN ON T_ZAIKO.SEIHIN_CODE = MST_SEIHIN.SEIHIN_CODE");
			rs = ps.executeQuery();
			while (rs.next()) {
				int zaikono = rs.getInt("ZAIKO_NO");
				String seihincode = rs.getString("SEIHIN_CODE");
				String seihinname = rs.getString("SEIHIN_NAME");
				int zaikosuryo = rs.getInt("ZAIKO_SURYO");
//				Mstseihin l = new Mstseihin(seihincode,seihinname,seihingenka,seihinteika);
//				list.add(l);			
				Tzaiko l = new Tzaiko(
						zaikono,seihincode,seihinname,zaikosuryo
						);
				l.setZaikono(zaikono);
				l.setSeihincode(seihincode);
				l.setSeihinname(seihinname);
				l.setZaikosuryo(zaikosuryo);
				list.add(l);

			}
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		}finally {
			this.disconnect();
		}

		return list;
	}
	
	
	
	public List<Tzaiko> searchAll(String searchname,String searchcode) {
		List<Tzaiko> list = new ArrayList<>();
		try {
			this.connect();
//			if (searchcode!="") {
				ps = db.prepareStatement("SELECT * FROM T_ZAIKO INNER JOIN MST_SEIHIN ON T_ZAIKO.SEIHIN_CODE = MST_SEIHIN.SEIHIN_CODE "
						+ "WHERE MST_SEIHIN.SEIHIN_CODE LIKE ? AND MST_SEIHIN.SEIHIN_NAME LIKE ? ");				
				ps.setString(1,"%"+searchcode+"%");
				ps.setString(2,"%"+searchname+"%");
//			}
//			else{
//				ps = db.prepareStatement("SELECT * FROM MST_SEIHIN WHERE SEIHIN_NAME LIKE ? ");		
//				ps.setString(1,"%"+searchname+"%");
//            }
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int zaikono = rs.getInt("ZAIKO_NO");
				String seihincode = rs.getString("SEIHIN_CODE");
				String seihinname = rs.getString("SEIHIN_NAME");
				int zaikosuryo = rs.getInt("ZAIKO_SURYO");
//				Mstseihin l = new Mstseihin(seihincode,seihinname,seihingenka,seihinteika);
//				list.add(l);			
				Tzaiko l = new Tzaiko(
						zaikono,seihincode,seihinname,zaikosuryo
						);
				l.setZaikono(zaikono);
				l.setSeihincode(seihincode);
				l.setSeihinname(seihinname);
				l.setZaikosuryo(zaikosuryo);
				list.add(l);

			}
		} catch (NamingException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			this.disconnect();
		}

		return list;
	}
	
	
//	
//	public void insertOne(Mstseihin tsuika) {
//		try {
//			this.connect();
//			ps=db.prepareStatement("INSERT INTO MST_SEIHIN(SEIHIN_CODE,SEIHIN_NAME,SEIHIN_GENKA,SEIHIN_TEIKA) VALUES(?,?,?,?)");
//			ps.setString(1,tsuika.getSeihincode());
//			ps.setString(2,tsuika.getSeihinname());
//			ps.setInt(3,tsuika.getSeihingenka());
//			ps.setInt(4,tsuika.getSeihinteika());
//			ps.executeUpdate();
//		} catch (NamingException | SQLException e) {
//			e.printStackTrace();
//		}finally {
//			this.disconnect();
//		}
//	}
//	
//	
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
//	public void deleteOne(String seihincode) {
//		try {
//			this.connect();
//			ps=db.prepareStatement("DELETE FROM MST_SEIHIN WHERE SEIHIN_CODE=?");
//			ps.setString(1, seihincode);
//			ps.executeUpdate();
//		} catch (NamingException | SQLException e) {
//			e.printStackTrace();
//		}finally {
//			this.disconnect();
//		}
//	}

}