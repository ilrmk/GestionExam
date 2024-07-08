package com.ensah.gestionExamens.bo;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Niveau {
	
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment")
	private Long id;
	
	private String titre;
	
	

	@OneToMany(
			mappedBy = "niveau", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true
			)
	Set<Element_pedagogique> modeles= new HashSet<>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Set<Element_pedagogique> getModeles() {
		return modeles;
	}

	public void setModeles(Set<Element_pedagogique> modeles) {
		this.modeles = modeles;
	}


}
