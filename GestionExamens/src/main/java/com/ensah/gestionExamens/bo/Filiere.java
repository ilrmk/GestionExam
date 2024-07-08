package com.ensah.gestionExamens.bo;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;


import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Filiere {
	
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment")
   private int id_Filiere;
   private  String name;
 
  
   @OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name="id_Filiere")
	private Set<Enseignant> enseignant ;

     public Filiere(){}
   
   
	public int getId_Filiere() {
		return id_Filiere;
	}
	
	public void setId_Filiere(int id_Filiere) {
		this.id_Filiere = id_Filiere;
	}
	
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Enseignant> getEnseignant() {
		return enseignant;
	}


	public void setEnseignant(Set<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}


	
	
    
    
    
}



