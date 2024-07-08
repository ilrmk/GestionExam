package com.ensah.gestionExamens.bo;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Surveillance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_surv;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "enseignants_surveillance",
	joinColumns = @JoinColumn(name = "id_survaillance"),
	inverseJoinColumns = @JoinColumn(name = "id_Enseignant"))
	private Set<Enseignant> enseignants_surveillance=new HashSet<>() ;
	
	 @ManyToOne
	 @JoinColumn(name="salle_id")
	 private Salle salle;
	
	 
	 @ManyToOne
	 @JoinColumn(name="exam_id")
	 private Examen examen;
	 
	 
	 @ManyToOne
	 @JoinColumn(name="coordonnateur_id")
	 private Enseignant coordonnateur;
	
	 
	 @ManyToOne
	 @JoinColumn(name="controleur_absense_id")
	 private Admin admin;


	 
	 
	 
	public Surveillance() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Surveillance( Set<Enseignant> enseignants_surveillance, Salle salle,Enseignant coordonnateur, Admin admin, Examen examen) {
	
		this.enseignants_surveillance = enseignants_surveillance;
		this.salle = salle;
		this.examen = examen;
		this.coordonnateur = coordonnateur;
		this.admin = admin;
	}


	public Long getId_surv() {
		return id_surv;
	}


	public void setId_surv(Long id_surv) {
		this.id_surv = id_surv;
	}


	public Set<Enseignant> getEnseignants_surveillance() {
		return enseignants_surveillance;
	}


	public void setEnseignants_surveillance(Set<Enseignant> enseignants_surveillance) {
		this.enseignants_surveillance = enseignants_surveillance;
	}


	public Salle getSalle() {
		return salle;
	}


	public void setSalle(Salle salle) {
		this.salle = salle;
	}


	public Examen getExamen() {
		return examen;
	}


	public void setExamen(Examen examen) {
		this.examen = examen;
	}


	public Enseignant getCoordonnateur() {
		return coordonnateur;
	}


	public void setCoordonnateur(Enseignant coordonnateur) {
		this.coordonnateur = coordonnateur;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	 
	 
	 
	
}
