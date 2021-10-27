package com.project.osa.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "Prodavac")
@DiscriminatorValue("prodavac")
public class Prodavac extends Korisnik {

	@Column(name="email")
	private String email;
	
	@Column(name="adresa")
	private String adresa; 
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="poseduje_od")
	private String poslujeOd;

	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="korisnik_id")
	private List<Akcija> akcije = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="korisnik_id")
	private List<Artikal> artikli = new ArrayList<>();

	
	public Prodavac() {}

	public Prodavac(int id, String ime, String prezime, String userName, String password, boolean blokiran,
			String tipKorisnika, String email, String adresa, String naziv, String poslujeOd, List<Akcija> akcije,
			List<Artikal> artikli) {
		super(id, ime, prezime, userName, password, blokiran, tipKorisnika);
		this.email = email;
		this.adresa = adresa;
		this.naziv = naziv;
		this.poslujeOd = poslujeOd;
		this.akcije = akcije;
		this.artikli = artikli;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getPoslujeOd() {
		return poslujeOd;
	}


	public void setPoslujeOd(String poslujeOd) {
		this.poslujeOd = poslujeOd;
	}


	public List<Artikal> getArtikli() {
		return artikli;
	}


	public List<Akcija> getAkcije() {
		return akcije;
	}


	public void setAkcije(List<Akcija> akcije) {
		this.akcije = akcije;
	}


	public void setArtikli(List<Artikal> artikli) {
		this.artikli = artikli;
	}




}
