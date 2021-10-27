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


@Entity(name = "Akcija")
@Table(name = "akcija")
public class Akcija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="tekst")
	private String tekst;
	
	@Column(name="od_kad")
	private String odKad;

	@Column(name="do_kad")
	private String doKad;

	@Column(name="procenat")
	private Integer procenat;

	

    @ManyToMany
    @JoinTable(name = "akcija_has_artikal", 
    		   joinColumns = @JoinColumn(name = "akcija_id"),
    		   inverseJoinColumns = @JoinColumn(name = "artikal_id"))
    List<Artikal> artikliNaAkciji;
	
	
	public Akcija() {}


	public Akcija(int id, String tekst, String odKad, String doKad, Integer procenat,
			List<Artikal> artikliNaAkciji) {
		super();
		this.id = id;
		this.tekst = tekst;
		this.odKad = odKad;
		this.doKad = doKad;
		this.procenat = procenat;
		this.artikliNaAkciji = artikliNaAkciji;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public Integer getProcenat() {
		return procenat;
	}


	public void setProcenat(Integer procenat) {
		this.procenat = procenat;
	}


	public List<Artikal> getArtikliNaAkciji() {
		return artikliNaAkciji;
	}


	public void setArtikliNaAkciji(List<Artikal> artikliNaAkciji) {
		this.artikliNaAkciji = artikliNaAkciji;
	}


	public String getOdKad() {
		return odKad;
	}


	public void setOdKad(String odKad) {
		this.odKad = odKad;
	}


	public String getDoKad() {
		return doKad;
	}


	public void setDoKad(String doKad) {
		this.doKad = doKad;
	}


	public String getTekst() {
		return tekst;
	}


	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

}
