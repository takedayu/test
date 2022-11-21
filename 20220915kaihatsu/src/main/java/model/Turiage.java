package model;

import java.io.Serializable;
import java.util.Date;

public class Turiage implements Serializable{
	private int uriageno;
	private Date uriagedate;
	private String kokyakucode;
	private String seihincode;
	private int uriagesuryo;
	private int uriage;
	public Turiage(
			int uriageno,Date uriagedate,String kokyakucode,String seihincode,int uriagesuryo,int uriage
			){

	}
	public int getUriageno() {
		return uriageno;
	}
	public void setUriageno(int uriageno) {
		this.uriageno = uriageno;
	}
	public Date getUriagedate() {
		return uriagedate;
	}
	public void setUriagedate(Date uriagedate) {
		this.uriagedate = uriagedate;
	}
	public String getKokyakucode() {
		return kokyakucode;
	}
	public void setKokyakucode(String kokyakucode) {
		this.kokyakucode = kokyakucode;
	}
	public String getSeihincode() {
		return seihincode;
	}
	public void setSeihincode(String seihincode) {
		this.seihincode = seihincode;
	}
	public int getUriagesuryo() {
		return uriagesuryo;
	}
	public void setUriagesuryo(int uriagesuryo) {
		this.uriagesuryo = uriagesuryo;
	}
	public int getUriage() {
		return uriage;
	}
	public void setUriage(int uriage) {
		this.uriage = uriage;
	}

}