package com.project.osa.dto;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class KorisnikDTO {

	@Id
	private int id;
	private String userName;
	private String password;
	private String token;
	private String tipKorisnika;
	

	public KorisnikDTO(int id, String userName, String password, String token, String tipKorisnika) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.token = token;
		this.tipKorisnika = tipKorisnika;
	}

	
	public KorisnikDTO() { }

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "KorisnikDTO [id=" + id + ", userName=" + userName + ", password=" + password + ", token=" + token
				+ ", tipKorisnika=" + tipKorisnika + "]";
	}


	public String getTipKorisnika() {
		return tipKorisnika;
	}


	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}


	
}
