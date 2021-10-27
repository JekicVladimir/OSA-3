package com.project.osa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("kupac")
public class Kupac extends Korisnik {

	@Column(name="adresa")
	private String adresa;

	//@OneToMany(cascade = CascadeType.MERGE)
	//@JoinColumn(name="korisnik_id")
	//private List<Porudzbina> porudzbine = new ArrayList<>();


	public Kupac() {}

	public Kupac(int id, String ime, String prezime, String userName, String password, boolean blokiran,
			String tipKorisnika, String adresa) {
		super(id, ime, prezime, userName, password, blokiran, tipKorisnika);
		this.adresa = adresa;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
}
