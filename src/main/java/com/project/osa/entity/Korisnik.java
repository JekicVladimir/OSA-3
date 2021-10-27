package com.project.osa.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", 
discriminatorType = DiscriminatorType.STRING)
public abstract class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ime")
	private String ime;
	
	@Column(name="prezime")
	private String prezime;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="blokiran")
	private boolean blokiran;
	
	@Column(name="user_type",  insertable = false, updatable = false)
	private String tipKorisnika;

	
	public Korisnik(int id, String ime, String prezime, String userName, String password, boolean blokiran,
			String tipKorisnika) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.userName = userName;
		this.password = password;
		this.blokiran = blokiran;
		this.tipKorisnika = tipKorisnika;
	}

	
	public Korisnik() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

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

	public boolean isBlokiran() {
		return blokiran;
	}

	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}


	public String getTipKorisnika() {
		return tipKorisnika;
	}


	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}


	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", userName=" + userName + ", password="
				+ password + ", blokiran=" + blokiran + ", tipKorisnika=" + tipKorisnika + "]";
	}


}
