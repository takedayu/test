
package model;

import java.io.Serializable;

public class Mstkokyaku  implements Serializable{
	private String kokyaku_code;
	private String kokyaku_name;
	private String kokyaku_address;
	private String kokyaku_tel;
	
	public Mstkokyaku(String kokyaku_code,String kokyaku_name,String kokyaku_address,String kokyaku_tel) {}

	public String getKokyaku_code() {
		return kokyaku_code;
	}

	public void setKokyaku_code(String kokyaku_code) {
		this.kokyaku_code = kokyaku_code;
	}

	public String getKokyaku_name() {
		return kokyaku_name;
	}

	public void setKokyaku_name(String kokyaku_name) {
		this.kokyaku_name = kokyaku_name;
	}

	public String getKokyaku_address() {
		return kokyaku_address;
	}

	public void setKokyaku_address(String kokyaku_address) {
		this.kokyaku_address = kokyaku_address;
	}

	public String getKokyaku_tel() {
		return kokyaku_tel;
	}

	public void setKokyaku_tel(String kokyaku_tel) {
		this.kokyaku_tel = kokyaku_tel;
	}
	
//	
	
}
