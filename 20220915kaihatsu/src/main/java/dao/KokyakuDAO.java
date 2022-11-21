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

import model.Mstkokyaku;

public class KokyakuDAO {
	
		private Connection db = null;
		private PreparedStatement ps = null;
		private ResultSet rs = null;

		//接続共通処理
		private void connect() throws NamingException, SQLException {
			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/jsp");
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
		

		public List<Mstkokyaku> findAll() {
			List<Mstkokyaku> list = new ArrayList<>();
			try {
				this.connect();
				ps = db.prepareStatement("SELECT * FROM MST_KOKYAKU");
				rs = ps.executeQuery();
				while (rs.next()) {
					String kokyaku_code = rs.getString("KOKYAKU_CODE");
					String kokyaku_name = rs.getString("KOKYAKU_NAME");
					String kokyaku_address = rs.getString("KOKYAKU_ADDRESS");
					String kokyaku_tel = rs.getString("KOKYAKU_TEL");
					
					if (kokyaku_address == null) {
						kokyaku_address = ("");
					}
					if (kokyaku_tel == null) {
						kokyaku_tel = ("");
					}
					
					Mstkokyaku l = new Mstkokyaku(
							kokyaku_code,kokyaku_name,kokyaku_address,kokyaku_tel
							);
							
					l.setKokyaku_code(kokyaku_code);
					l.setKokyaku_name(kokyaku_name);
					l.setKokyaku_address(kokyaku_address);
					l.setKokyaku_tel(kokyaku_tel);
						
					list.add(l);			
					
				}
			} catch (NamingException | SQLException e) {

				e.printStackTrace();
			}finally {
				this.disconnect();
			}

			return list;
			
		}
		
		
		
		public void insertOne(Mstkokyaku mstkokyaku) {
			try {
				this.connect();
				ps=db.prepareStatement("INSERT INTO mst_kokyaku(kokyaku_code,kokyaku_name,kokyaku_address,kokyaku_tel) VALUES(?,?,?,?)");
				ps.setString(1,mstkokyaku.getKokyaku_code());
				ps.setString(2,mstkokyaku.getKokyaku_name());
				ps.setString(3,mstkokyaku.getKokyaku_address());
				ps.setString(4,mstkokyaku.getKokyaku_tel());
				
				ps.executeUpdate();
				
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}finally {
				this.disconnect();
			}

		}
		
		
		
		public Mstkokyaku findOne(String kokyakucode) {
			Mstkokyaku mstkokyaku=null;
			try {
				this.connect();
				ps=db.prepareStatement("SELECT * FROM MST_KOKYAKU WHERE KOKYAKU_CODE=?");
				ps.setString(1, kokyakucode);
				rs=ps.executeQuery();
				if(rs.next()) {
					kokyakucode=rs.getString("KOKYAKU_CODE");
					String kokyaku_name=rs.getString("KOKYAKU_NAME");				
					String kokyaku_address = rs.getString("KOKYAKU_ADDRESS");
					String kokyaku_tel = rs.getString("KOKYAKU_TEL");
					
					if (kokyaku_address == null) {
						kokyaku_address = ("");
					}
					if (kokyaku_tel == null) {
						kokyaku_tel = ("");
					}
					
					mstkokyaku=new Mstkokyaku(kokyakucode,kokyaku_name,kokyaku_address,kokyaku_tel);
					
					mstkokyaku.setKokyaku_code(kokyakucode);
					mstkokyaku.setKokyaku_name(kokyaku_name);
					mstkokyaku.setKokyaku_address(kokyaku_address);
					mstkokyaku.setKokyaku_tel(kokyaku_tel);		
					
				}
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}finally {
				this.disconnect();
			}

			return mstkokyaku;
		}
		
		
		
		public void updateOne(Mstkokyaku mstkokyaku) {
			try {
				this.connect();
				ps=db.prepareStatement("UPDATE MST_KOKYAKU SET kokyaku_code=?,kokyaku_name=?,kokyaku_address=?,kokyaku_tel=? WHERE kokyaku_code=?");
				
				
				ps.setString(1,mstkokyaku.getKokyaku_code());
				ps.setString(2,mstkokyaku.getKokyaku_name());
				ps.setString(3,mstkokyaku.getKokyaku_address());
				ps.setString(4,mstkokyaku.getKokyaku_tel());
				ps.setString(5,mstkokyaku.getKokyaku_code());
				
				ps.executeUpdate();
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}finally {
				this.disconnect();
			}
		}
		
		
		
		public void deleteOne(String kokyaku_code) {
			try {
				this.connect();
				ps=db.prepareStatement("DELETE FROM MST_KOKYAKU WHERE kokyaku_code=?");
				ps.setString(1, kokyaku_code);
				ps.executeUpdate();
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}finally {
				this.disconnect();
			}
		}

		
		
		public List<Mstkokyaku> searchAll(String kokyaku_code2,String kokyaku_name2) {
			List<Mstkokyaku> list = new ArrayList<>();
			try {
			
				this.connect();
				ps = db.prepareStatement("SELECT * FROM MST_KOKYAKU WHERE KOKYAKU_NAME LIKE ? AND KOKYAKU_CODE LIKE ?");
				
				
				ps.setString(1, "%"+kokyaku_name2+"%");
				ps.setString(2, "%"+kokyaku_code2+"%");
				rs = ps.executeQuery();
				while (rs.next()) {
					String kokyaku_code = rs.getString("kokyaku_code");
					String kokyaku_name = rs.getString("kokyaku_name");
					String kokyaku_address = rs.getString("kokyaku_address");
					String kokyaku_tel = rs.getString("kokyaku_tel");
					
					if (kokyaku_address == null) {
						kokyaku_address = ("");
					}
					if (kokyaku_tel == null) {
						kokyaku_tel = ("");
					}
					
					Mstkokyaku l = new Mstkokyaku(
							kokyaku_code, kokyaku_name, kokyaku_address, kokyaku_tel
							);
			
					l.setKokyaku_code(kokyaku_code);
					l.setKokyaku_name(kokyaku_name);
					l.setKokyaku_address(kokyaku_address);
					l.setKokyaku_tel(kokyaku_tel);
					
					list.add(l);
				}
			} catch (NamingException | SQLException e) {

				e.printStackTrace();
			}finally {
				this.disconnect();
			}

			return list;
		}
		

		public List<Mstkokyaku> listAll() {
			List<Mstkokyaku> list2 = new ArrayList<>();
			try {
				this.connect();
				ps = db.prepareStatement("SELECT * FROM MST_KOKYAKU");
				rs = ps.executeQuery();
				while (rs.next()) {
					String kokyaku_code = rs.getString("KOKYAKU_CODE");
					String kokyaku_name = rs.getString("KOKYAKU_NAME");
					String kokyaku_address = rs.getString("KOKYAKU_ADDRESS");
					String kokyaku_tel = rs.getString("KOKYAKU_TEL");
					Mstkokyaku l2 = new Mstkokyaku(kokyaku_code, kokyaku_name, kokyaku_address, kokyaku_tel);
					
					l2.setKokyaku_code(rs.getString("KOKYAKU_CODE"));
					l2.setKokyaku_name(rs.getString("KOKYAKU_NAME"));
					l2.setKokyaku_address(rs.getString("KOKYAKU_ADDRESS"));
					l2.setKokyaku_tel(rs.getString("KOKYAKU_TEL"));
					
					list2.add(l2);
				}
			} catch (NamingException | SQLException e) {

				e.printStackTrace();
			}finally {
				this.disconnect();
			}

			return list2;
		}
		
}

