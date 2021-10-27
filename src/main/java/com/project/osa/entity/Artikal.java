package com.project.osa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "Artikal")
@Table(name = "artikal")
public class Artikal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="opis")
	private String opis;
	
	@Column(name="cena")
	private Double cena;
	
	@Column(name="putanja_slike")
	private String putanjaSlike;
	

    @ManyToMany
    @JoinTable(name = "akcija_has_artikal", 
    		   joinColumns = @JoinColumn(name = "artikal_id"),
    		   inverseJoinColumns = @JoinColumn(name = "akcija_id"))
    @JsonIgnore
    List<Akcija> akcijeArtikla;
	
	public Artikal() {}

	public Artikal(int id, String naziv, String opis, Double cena, String putanjaSlike,
			List<Akcija> akcijeArtikla) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.putanjaSlike = putanjaSlike;
		this.akcijeArtikla = akcijeArtikla;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}


	public Double getCena() {
		return cena;
	}


	public void setCena(Double cena) {
		this.cena = cena;
	}


	public String getPutanjaSlike() {
		return putanjaSlike;
	}


	public void setPutanjaSlike(String putanjaSlike) {
		this.putanjaSlike = putanjaSlike;
	}


	public List<Akcija> getAkcijeArtikla() {
		return akcijeArtikla;
	}


	public void setAkcijeArtikla(List<Akcija> akcijeArtikla) {
		this.akcijeArtikla = akcijeArtikla;
	}
	
}
