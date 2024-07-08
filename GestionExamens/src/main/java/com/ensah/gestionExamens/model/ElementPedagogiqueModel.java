package com.ensah.gestionExamens.model;

import java.util.HashSet;
import java.util.Set;

import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Examen;
import com.ensah.gestionExamens.bo.Niveau;
import com.ensah.gestionExamens.bo.Filiere;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ElementPedagogiqueModel {

    private long id;
    @NotBlank(message = "Ce champ est obligatoire")
    private String titre;
    @NotBlank(message = "Ce champ est obligatoire")
    private String type;
    @NotNull(message = "Ce champ est obligatoire")
    @Min(value = 0, message = "Volume Horaire doit être supérieur à 0")
    private int volumeHoraire;
    @NotNull(message = "Ce champ est obligatoire")
    @Min(value = 0, message = "Nombre Heures de  Tp doit être supérieur à 0")

    private int nombreHeuresTp;
    @NotNull(message = "Ce champ est obligatoire")
    @Min(value = 0, message = "Nombre Heures de  TD doit être supérieur à 0")

    private int nombreHeuresTD;
    @NotNull(message = "Ce champ est obligatoire")
    @Min(value =0, message = "Nombre Heures de  TD doit être supérieur à 0")
    private int nombreHeuresCours;
    
    private Enseignant coordonnateur; 
    @NotNull(message = "Ce champ est obligatoire")
    private Enseignant enseignant;
    private Niveau niveau;
	
	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	

    

    

	

	

	public ElementPedagogiqueModel() {
        
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVolumeHoraire() {
		return volumeHoraire;
	}

	public void setVolumeHoraire(int volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}

	

	public int getNombreHeuresTp() {
		return nombreHeuresTp;
	}

	public void setNombreHeuresTp(int nombreHeuresTp) {
		this.nombreHeuresTp = nombreHeuresTp;
	}

	public int getNombreHeuresTD() {
		return nombreHeuresTD;
	}

	public void setNombreHeuresTD(int nombreHeuresTD) {
		this.nombreHeuresTD = nombreHeuresTD;
	}

	public int getNombreHeuresCours() {
		return nombreHeuresCours;
	}

	public void setNombreHeuresCours(int nombreHeuresCours) {
		this.nombreHeuresCours = nombreHeuresCours;
	}

	public Enseignant getCoordonnateur() {
		return coordonnateur;
	}

	public void setCoordonnateur(Enseignant coordonnateur) {
		this.coordonnateur = coordonnateur;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

}
