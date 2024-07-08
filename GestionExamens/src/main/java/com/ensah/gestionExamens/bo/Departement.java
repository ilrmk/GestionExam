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
public class Departement {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment")
	private Long id;
	
	
	private String name;
	
	@OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name="id_departement")
	private Set<Enseignant> Enseignant;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Enseignant> getEnseignant() {
		return Enseignant;
	}

	public void setEnseignant(Set<Enseignant> enseignant) {
		Enseignant = enseignant;
	}
	 
	
}
