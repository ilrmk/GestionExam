package com.ensah.gestionExamens.model;

import java.time.LocalDate;
import java.util.Set;

import com.ensah.gestionExamens.bo.Departement;
import com.ensah.gestionExamens.bo.Filiere;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PersonneModel {
	
	@NotBlank(message = "Ce champ est obligatoire")
        private String nom;
	@NotBlank(message = "Ce champ est obligatoire")
        private String prenom;
	@NotBlank(message = "Ce champ est obligatoire")
	    private String sexe;
	@NotBlank(message = "Ce champ est obligatoire")
	    private String PPR;
	@NotBlank(message = "Ce champ est obligatoire")
	    private String CIN;
	    private LocalDate dateNaissance;
	    private String lieuNaissance;
		@NotBlank(message = "Ce champ est obligatoire")
	    private String situationAdmin;		
	    private String ancAdministratif;
	    private LocalDate dateRecrutement;
	    private LocalDate dateAffectation;
		@NotBlank(message = "Ce champ est obligatoire")
		@Email(message = "Enter a valid email")
	    private String email;
	    
	    private String telephone;
	    private String situationFamiliale;
	    private String grade;
		@NotBlank(message = "Ce champ est obligatoire")
	    private String adresse;
		private  Filiere filiere;
		private Departement departement;

		
	    private int id;
		private String typePerson;
  private String specialite;
		
		public String getSpecialite() {
	return specialite;
}
public void setSpecialite(String specialite) {
	this.specialite = specialite;
}
		public String getTypePerson() {
			return typePerson;
		}
		public void setTypePerson(String typePerson) {
			this.typePerson = typePerson;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
	    public PersonneModel()
	    {
	    	
	    	
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
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}
		public PersonneModel(@NotBlank(message = "Ce champ est obligatoire") String nom,
				@NotBlank(message = "Ce champ est obligatoire") String prenom,
				@NotBlank(message = "Ce champ est obligatoire") String sexe,
				@NotBlank(message = "Ce champ est obligatoire") String pPR,
				@NotBlank(message = "Ce champ est obligatoire") String cIN, LocalDate dateNaissance,
				String lieuNaissance, @NotBlank(message = "Ce champ est obligatoire") String situationAdmin,
				@NotBlank(message = "Ce champ est obligatoire") String ancAdministratif,
				@NotNull(message = "La date de recrutement ne peut pas être nulle") LocalDate dateRecrutement,
				@NotNull(message = "La date d'affectation ne peut pas être nulle") LocalDate dateAffectation,
				@NotBlank(message = "Ce champ est obligatoire") String email, String telephone,
				String situationFamiliale, @NotBlank(message = "Ce champ est obligatoire") String grade,
				@NotBlank(message = "Ce champ est obligatoire") String adresse,
				@NotNull(message = "Choisi la filiere") Long filiere,
				@NotNull(message = "Choisi le departement") Long departement, String departement_nam,
				String filiere_nam, int id, String typePerson) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.sexe = sexe;
			PPR = pPR;
			CIN = cIN;
			this.dateNaissance = dateNaissance;
			this.lieuNaissance = lieuNaissance;
			this.situationAdmin = situationAdmin;
			this.ancAdministratif = ancAdministratif;
			this.dateRecrutement = dateRecrutement;
			this.dateAffectation = dateAffectation;
			this.email = email;
			this.telephone = telephone;
			this.situationFamiliale = situationFamiliale;
			this.grade = grade;
			this.adresse = adresse;
			
			this.id = id;
			this.typePerson = typePerson;
		}
		public Filiere getFiliere() {
			return filiere;
		}
		public void setFiliere(Filiere filiere) {
			this.filiere = filiere;
		}
		public Departement getDepartement() {
			return departement;
		}
		public void setDepartement(Departement departement) {
			this.departement = departement;
		}
		
}
