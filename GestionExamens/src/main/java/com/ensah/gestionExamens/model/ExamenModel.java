package com.ensah.gestionExamens.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ExamenModel {
	
	
	 @NotNull(message = "La date ne peut pas être vide")
    private LocalDate date;
	
	@Pattern(regexp = "^(\\d+h)?(\\d{1,2}min)?$", message= "Utilisez le format \"1h\" ou \"1h30min\"")
    private String dureePrevue;
    
    @NotNull(message = "Le champ ne peut pas être vide")
    private Long niveauId;
    
    @NotNull(message = "Le champ ne peut pas être vide")
    private Long moduleId;
    
    private String session;
    
    private String semestre;
    
    private String type;
    
    private Long coordinnateur;
    
    @NotNull(message = "L'heure ne peut pas être vide")
    private LocalTime heureDebut;
    
    @NotBlank(message = "This field is required")
    private String anneeUniversitaire;
    
    @NotNull(message = "This field is required")
    private List<Long> selectedSalleIds;
    
    @NotNull(message = "Le champ ne peut pas être vide")
    private Integer selectedOption;
    
    private List<Integer> NbsurveillantsParSalle;

    // Ajoutez ici les getters et les setters pour chaque champ

    
    
    
    // Assurez-vous d'avoir un constructeur par défaut sans arguments
    public ExamenModel() {
    }

	public Long getNiveauId() {
		return niveauId;
	}

	public void setNiveauId(Long niveauId) {
		this.niveauId = niveauId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCoordinnateurId() {
		return coordinnateur;
	}

	public void setCoordinnateurId(Long coordinnateurId) {
		this.coordinnateur = coordinnateurId;
	}

	

	public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	



	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	

	public String getDureePrevue() {
		return dureePrevue;
	}

	public void setDureePrevue(String dureePrevue) {
		this.dureePrevue = dureePrevue;
	}

	public Long getCoordinnateur() {
		return coordinnateur;
	}

	public void setCoordinnateur(Long coordinnateur) {
		this.coordinnateur = coordinnateur;
	}

	public String getAnneeUniversitaire() {
		return anneeUniversitaire;
	}

	public void setAnneeUniversitaire(String anneeUniversitaire) {
		this.anneeUniversitaire = anneeUniversitaire;
	}

	public List<Long> getSelectedSalleIds() {
		return selectedSalleIds;
	}

	public void setSelectedSalleIds(List<Long> selectedSalleIds) {
		this.selectedSalleIds = selectedSalleIds;
	}

	public Integer getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(Integer selectedOption) {
		this.selectedOption = selectedOption;
	}

	public List<Integer> getNbsurveillantsParSalle() {
		return NbsurveillantsParSalle;
	}

	public void setNbsurveillantsParSalle(List<Integer> nbsurveillantsParSalle) {
		NbsurveillantsParSalle = nbsurveillantsParSalle;
	}

	@Override
	public String toString() {
		return "ExamenModel [date=" + date + ", dureePrevue=" + dureePrevue + ", niveauId=" + niveauId + ", moduleId="
				+ moduleId + ", session=" + session + ", semestre=" + semestre + ", type=" + type + ", coordinnateurId="
				+ coordinnateur + ", heureDebut=" + heureDebut + ", anneeUniversitaire=" + anneeUniversitaire
				+ ", selectedSalleIds=" + selectedSalleIds + ", selectedOption=" + selectedOption
				+ ", NbsurveillantsParSalle=" + NbsurveillantsParSalle + "]";
	}

	
	

	
}


