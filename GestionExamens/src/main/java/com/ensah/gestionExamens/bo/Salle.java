package com.ensah.gestionExamens.bo;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Salle {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment")
	private Long id_salle;
	
	private String nom;
	
	private int  capacite;
	
	
	
	@OneToMany(
			mappedBy = "salle", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true
			)
	private Set<Surveillance> survs;

	public Salle() {}
	public Long getId() {
		return this.id_salle;
	}

	public void setId(Long id) {
		this.id_salle = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	
	
}
