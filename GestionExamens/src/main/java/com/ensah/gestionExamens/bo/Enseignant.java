package com.ensah.gestionExamens.bo;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Enseignant extends Personne {
	
	   
    private String grade;
    private String specialite;
	
	
	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	@ManyToMany(mappedBy = "enseignants_group", 
			cascade = CascadeType.ALL)
	private Set<Groupe> groupe;

	
	@OneToMany(
			mappedBy = "coordonnateur", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true
			)
	Set<Element_pedagogique> modeles= new HashSet<>();
	
	
	@OneToMany(mappedBy = "coordonnateur", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)	
	private Set<Surveillance> survs_coordonner;
	
	
	
	@ManyToMany(mappedBy = "enseignants_surveillance")
	private Set<Surveillance> survs;
	
	
	@ManyToOne
	@JoinColumn(name="id_departement")
	private Departement departement;
	@ManyToOne
	@JoinColumn(name = "id_Filiere")
    private Filiere filiere;
	

	

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}


	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Set<Groupe> getGroupe() {
		return groupe;
	}

	public void setGroupe(Set<Groupe> groupe) {
		this.groupe = groupe;
	}

	

	

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	public Set<Element_pedagogique> getModeles() {
		return modeles;
	}

	public void setModeles(Set<Element_pedagogique> modeles) {
		this.modeles = modeles;
	}

	public Set<Surveillance> getSurvs() {
		return survs;
	}

	public void setSurvs(Set<Surveillance> survs) {
		this.survs = survs;
	}

	public Set<Surveillance> getSurvs_coordonner() {
		return survs_coordonner;
	}

	public void setSurvs_coordonner(Set<Surveillance> survs_coordonner) {
		this.survs_coordonner = survs_coordonner;
	}
	
	
}