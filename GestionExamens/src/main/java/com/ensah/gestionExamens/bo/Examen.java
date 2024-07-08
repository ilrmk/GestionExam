package com.ensah.gestionExamens.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.*;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


// IL RESTE LA CONFIGURATION ASSOICER A BASE DE DONNE 



@Entity
public class Examen {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment")
    private Long id_examen; 
	
	@OneToMany(mappedBy = "examen",cascade = CascadeType.ALL)	
	private List<Surveillance> survs=new ArrayList<>();

	String semestre;
	String session;
	String type;
	
    private LocalDate date;
    private LocalTime heureDebut;
    private String dureePrevue;
    private String dureeReelle;
   
    private boolean confirm;
    private String anneeUniversitaire;
    private String epreuve; 
    private String pv; 
    private String rapportTextuel; 

    @ManyToOne(cascade = {CascadeType.PERSIST,	CascadeType.MERGE})
    @JoinColumn(name="module_id")
    private Element_pedagogique module;

    
    public void removeSurveillance(Surveillance s) {
		survs.remove(s);
		s.setExamen(null);
	}
    
    public void removeAllSurveillance() {
		System.out.println("dans la fonction desupp des surveillance de l'examne");
		for(Surveillance s:survs) {
			s.setExamen(null);
		}
		
		survs.clear();
	}
    
    public Element_pedagogique getModule() {
		return module;
	}



	public void setModule(Element_pedagogique module) {
		this.module = module;
	}



	public boolean isConfirm() {
		return confirm;
	}


	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}


	public Examen() {
    }

   

    // Getters et setters
    public Long getId() {
        return id_examen;
    }

    public void setId(Long id) {
        this.id_examen = id;
    }

    


    public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public LocalTime getHeureDebut() {
		return heureDebut;
	}



	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}




   

   



	public String getDureePrevue() {
		return dureePrevue;
	}


	public void setDureePrevue(String dureePrevue) {
		this.dureePrevue = dureePrevue;
	}


	public String getDureeReelle() {
		return dureeReelle;
	}


	public void setDureeReelle(String dureeReelle) {
		this.dureeReelle = dureeReelle;
	}


	public String getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public String getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(String epreuve) {
        this.epreuve = epreuve;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getRapportTextuel() {
        return rapportTextuel;
    }

    public void setRapportTextuel(String rapportTextuel) {
        this.rapportTextuel = rapportTextuel;
    }

	public Long getId_examen() {
		return id_examen;
	}

	public void setId_examen(Long id_examen) {
		this.id_examen = id_examen;
	}

	public List<Surveillance> getSurvs() {
		return survs;
	}

	public void setSurvs(List<Surveillance> survs) {
		this.survs = survs;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Examen [id_examen=" + id_examen + ", survs=" + survs + ", semestre=" + semestre + ", session=" + session
				+ ", type=" + type + ", date=" + date + ", heureDebut=" + heureDebut + ", dureePrevue=" + dureePrevue
				+ ", dureeReelle=" + dureeReelle + ", anneeUniversitaire=" + anneeUniversitaire + ", epreuve=" + epreuve
				+ ", pv=" + pv + ", rapportTextuel=" + rapportTextuel + "]";
	}
    
    
    
    
    
    
}
