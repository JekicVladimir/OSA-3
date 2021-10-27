package com.project.osa.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Porudzbina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="satnica")
	private Date satnica;
	
	@Column(name="dostavljeno")
	private Boolean dostavljeno;
	
	@Column(name="ocena")
	private Integer ocena;

	@Column(name="anoniman_komentar")
	private Boolean anonimnKomentar;
	
	@Column(name="arhiviran_komentar")
	private Boolean arhiviranKomentar;
	
	@Column(name="komentar")
	private String komentar;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="korisnik_id")
	private Kupac kupac;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="porudzbina_id")
	private List<Stavka> stavke = new ArrayList<>();


	public Porudzbina(int id, Date satnica, Boolean dostavljeno, Integer ocena, Boolean anonimnKomentar,
			Boolean arhiviranKomentar, String komentar, Kupac kupac, List<Stavka> stavke) {
		super();
		this.id = id;
		this.satnica = satnica;
		this.dostavljeno = dostavljeno;
		this.ocena = ocena;
		this.anonimnKomentar = anonimnKomentar;
		this.arhiviranKomentar = arhiviranKomentar;
		this.komentar = komentar;
		this.kupac = kupac;
		this.stavke = stavke;
	}


	public Porudzbina() {}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSatnica() {
		return satnica;
	}

	public void setSatnica(Date satnica) {
		this.satnica = satnica;
	}

	public Boolean getDostavljeno() {
		return dostavljeno;
	}

	public void setDostavljeno(Boolean dostavljeno) {
		this.dostavljeno = dostavljeno;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public Boolean getAnonimnKomentar() {
		return anonimnKomentar;
	}

	public void setAnonimnKomentar(Boolean anonimnKomentar) {
		this.anonimnKomentar = anonimnKomentar;
	}

	public Boolean getArhiviranKomentar() {
		return arhiviranKomentar;
	}

	public void setArhiviranKomentar(Boolean arhiviranKomentar) {
		this.arhiviranKomentar = arhiviranKomentar;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}


	public List<Stavka> getStavke() {
		return stavke;
	}


	public void setStavke(List<Stavka> stavke) {
		this.stavke = stavke;
	}


	public Kupac getKupac() {
		return kupac;
	}


	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

}
