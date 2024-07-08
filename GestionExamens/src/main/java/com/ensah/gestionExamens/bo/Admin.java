package com.ensah.gestionExamens.bo;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Admin extends Personne{


	@OneToMany(mappedBy = "admin", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)	
	private Set<Surveillance> survs;

	public Set<Surveillance> getSurvs() {
		return survs;
	}

	public void setSurvs(Set<Surveillance> survs) {
		this.survs = survs;
	}
	
	
}
