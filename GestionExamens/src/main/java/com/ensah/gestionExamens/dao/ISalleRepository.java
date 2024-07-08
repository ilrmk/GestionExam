package com.ensah.gestionExamens.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.gestionExamens.bo.Salle;



public interface ISalleRepository extends JpaRepository<Salle, Long> {
	
	 Salle findSalleByNom(String nom);
	

}
