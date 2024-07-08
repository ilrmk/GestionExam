package com.ensah.gestionExamens.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ensah.gestionExamens.bo.Enseignant;


public interface IEnseignantRepositry extends JpaRepository<Enseignant, Long>{

	/*@Query("select e from Enseignant e join e.departement d join e.filiere f")
	public List<Enseignant> getALLEnsiengntAndDepartement();*/

	public ArrayList<Enseignant> findEnseignantBynom(String nom);
	public  Enseignant findEnseignantByPPR(String matricule);
	
}
