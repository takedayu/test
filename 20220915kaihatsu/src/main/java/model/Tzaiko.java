package model;

import java.io.Serializable;

public class Tzaiko implements Serializable{
	private int zaikono;
	private String seihincode;
	private String seihinname;
	private int zaikosuryo;
	public Tzaiko(
			int zaikono,String seihincode,String seihinname,int zaikosuryo
			){


	}
	public int getZaikono() {
		return zaikono;
	}
	public void setZaikono(int zaikono) {
		this.zaikono = zaikono;
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
	public int getZaikosuryo() {
		return zaikosuryo;
	}
	public void setZaikosuryo(int zaikosuryo) {
		this.zaikosuryo = zaikosuryo;
	}
}