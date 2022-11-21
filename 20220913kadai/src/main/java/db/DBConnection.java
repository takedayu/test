package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class DBConnection {
 
    public static void main(String[] args) throws Exception {
 
        Connection con = null;
        Statement smt = null;
        ResultSet rs = null;
 
        //ホスト名
        String hostname = "163.149.99.38";
        //SID
        String sid = "oracle";
        //ユーザー名
        String username = "kenshu";
        //パスワード
        String passwd = "kenshu";
 
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + hostname + ":1521:" + sid,username,passwd);
 
            smt = con.createStatement();
            rs = smt.executeQuery("SELECT * FROM MST_KOKYAKU");
 
            while(rs.next()){
                System.out.println(
                    "顧客コード" + rs.getString("KOKYAKU_CODE")
                    + "　名前" + rs.getString("KOKYAKU_NAME")
                    + "　住所" + rs.getString("KOKYAKU_ADDRESS")
                    + "　電話番号" + rs.getString("KOKYAKU_TEL")
                );
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        smt.close();
        con.close();
    }
 
}