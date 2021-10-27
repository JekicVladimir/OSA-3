package com.project.osa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Stavka")
@Table(name = "stavka")
public class Stavka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="kolicina")
	private Integer kolicina;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "artikal_id")
	private Artikal artikal;


	public Stavka(int id, Integer kolicina, Artikal artikal, Porudzbina porudzbina) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.artikal = artikal;
	}


	public Stavka() {}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}


	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}


	
}

