package com.ensah.gestionExamens.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.gestionExamens.bo.Niveau;

public interface INiveauRepository extends JpaRepository<Niveau,Long> {
	
	
	public Niveau findNiveauByTitre(String titre);

}
