package com.project.osa.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("administrator")
public class Administrator extends Korisnik {

	public Administrator() {}

	
	@Override
	public String toString() {
		return "Administrator []";
	}

	
	
}
