package com.ensah.gestionExamens.model;

import java.time.LocalDate;
import java.util.Set;

import com.ensah.gestionExamens.bo.Enseignant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class GroupModel {
	private int id;
	@NotBlank(message = "Ce champ est obligatoire")
	private String nom;
    @NotNull(message = "Ce champ est obligatoire")

    private LocalDate dateCreation;
	@NotBlank(message = "Ce champ est obligatoire")

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
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	

	
	public Set<Enseignant> getEnseignants_group() {
		return enseignants_group;
	}
	public void setEnseignants_group(Set<Enseignant> enseignants_group) {
		this.enseignants_group = enseignants_group;
	}
	public long getIdEnseignant() {
		return idEnseignant;
	}
	public void setIdEnseignant(long idEnseignant) {
		this.idEnseignant = idEnseignant;
	}


	private Set<Enseignant> enseignants_group ;
	private long idEnseignant;
}
