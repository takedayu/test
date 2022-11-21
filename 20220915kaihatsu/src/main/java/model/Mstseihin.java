package model;

import java.io.Serializable;

public class Mstseihin implements Serializable{
	private String seihincode;
	private String seihinname;
	private int seihingenka;
	private int seihinteika;
	public Mstseihin(
			String seihincode,String seihinname,int seihingenka,int seihinteika
			){

	}
	
	public String getSeihincode() {
		return seihincode;
	}
	public void setSeihincode(String seihincode) {
		this.seihincode = seihincode;
	}
	public String getSeihinname() {
		return seihinname;
	}
	public void setSeihinname(String seihinname) {
		this.seihinname = seihinname;
	}
	public int getSeihingenka() {
		return seihingenka;
	}
	public void setSeihingenka(int seihingenka) {
		this.seihingenka = seihingenka;
	}
	public int getSeihinteika() {
		return seihinteika;
	}
	public void setSeihinteika(int seihinteika) {
		this.seihinteika = seihinteika;
	}	
	
}