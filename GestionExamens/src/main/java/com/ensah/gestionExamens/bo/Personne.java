package com.ensah.gestionExamens.bo;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Personne {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment")
    private int  id ;
	//validation
	private String nom;
	
	
	private String prenom;

	   private String sexe;
		@Column(unique = true)
	    private String PPR;
		@Column(unique = true)
	    private String CIN;
	    private LocalDate dateNaissance;
	    private String lieuNaissance;
	    private String situationAdmin;
	    private String ancAdministratif;
	    private LocalDate dateRecrutement;
	    private LocalDate dateAffectation;
		@Column(unique = true)
	    private String email;
	    private String telephone;
	    private String situationFamiliale;
	    public String getSexe() {
			return sexe;
		}

		public void setSexe(String sexe) {
			this.sexe = sexe;
		}

		public String getPPR() {
			return PPR;
		}

		public void setPPR(String pPR) {
			PPR = pPR;
		}

		public String getCIN() {
			return CIN;
		}

		public void setCIN(String cIN) {
			CIN = cIN;
		}

		public LocalDate getDateNaissance() {
			return dateNaissance;
		}

		public void setDateNaissance(LocalDate dateNaissance) {
			this.dateNaissance = dateNaissance;
		}

		public String getLieuNaissance() {
			return lieuNaissance;
		}

		public void setLieuNaissance(String lieuNaissance) {
			this.lieuNaissance = lieuNaissance;
		}

		public String getSituationAdmin() {
			return situationAdmin;
		}

		public void setSituationAdmin(String situationAdmin) {
			this.situationAdmin = situationAdmin;
		}

		public String getAncAdministratif() {
			return ancAdministratif;
		}

		public void setAncAdministratif(String ancAdministratif) {
			this.ancAdministratif = ancAdministratif;
		}

		public LocalDate getDateRecrutement() {
			return dateRecrutement;
		}

		public void setDateRecrutement(LocalDate dateRecrutement) {
			this.dateRecrutement = dateRecrutement;
		}

		public LocalDate getDateAffectation() {
			return dateAffectation;
		}

		public void setDateAffectation(LocalDate dateAffectation) {
			this.dateAffectation = dateAffectation;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getSituationFamiliale() {
			return situationFamiliale;
		}

		public void setSituationFamiliale(String situationFamiliale) {
			this.situationFamiliale = situationFamiliale;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		private String adresse;

	
	public Personne() {}
	
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	



}
