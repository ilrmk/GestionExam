package com.ensah.gestionExamens.bo;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Groupe {

	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment")
	private int id;
	@Column(unique = true)
	private String nom;
    private LocalDate dateCreation;
	private String descrp_group;
	private String cretaire;
	
	public String getDescrp_group() {
		return descrp_group;
	}
	public void setDescrp_group(String descrp_group) {
		this.descrp_group = descrp_group;
	}
	public String getCretaire() {
		return cretaire;
	}
	public void setCretaire(String cretaire) {
		this.cretaire = cretaire;
	}

	@ManyToMany
	@JoinTable(name = "Group_Enseignant",
	    joinColumns = @JoinColumn(name = "id_Group"),
	    inverseJoinColumns = @JoinColumn(name = "id_Enseignant"))
	private Set<Enseignant> enseignants_group;

	
	public Groupe() {
    }
	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Enseignant> getEnseignants_group() {
		return enseignants_group;
	}

	public void setEnseignants_group(Set<Enseignant> enseignants_group) {
		this.enseignants_group = enseignants_group;
	}
	
	
	    
	
}

