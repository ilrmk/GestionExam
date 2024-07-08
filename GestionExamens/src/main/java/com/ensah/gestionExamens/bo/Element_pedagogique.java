package com.ensah.gestionExamens.bo;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Element_pedagogique {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment")
	private Long id;
	@Column(unique = true)
	private String titre;
	private String type;
	private int volumeHoraire;
    private int nombreHeuresTp;
	private int nombreHeuresTD;
	private int nombreHeuresCours;
	
	@OneToMany(
			mappedBy = "module", 
			cascade = { CascadeType.PERSIST, CascadeType.MERGE },
			orphanRemoval = true
			)
	Set<Examen> exam= new HashSet<>();
	
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="coord_id")
	private Enseignant coordonnateur;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="enseignant_id")
	private Enseignant enseignant;
	
	public Enseignant getEnseignant() {
		return enseignant;
	}



	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}



	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="niveau_id")
	private Niveau niveau;
	
	


	public Niveau getNiveau() {
		return niveau;
	}



	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}



	public Set<Examen> getExam() {
		return exam;
	}



	public void setExam(Set<Examen> exam) {
		this.exam = exam;
	}



	public Enseignant getCoordonnateur() {
		return coordonnateur;
	}



	public void setCoordonnateur(Enseignant coordonnateur) {
		this.coordonnateur = coordonnateur;
	}



	public Element_pedagogique() {}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		
	
}
