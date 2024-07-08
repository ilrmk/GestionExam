package com.ensah.gestionExamens.service;

import java.util.List;
import java.util.Optional;

import com.ensah.gestionExamens.bo.Niveau;
import com.ensah.gestionExamens.bo.Salle;

public interface INiveauService {
	
	
public Optional<Niveau> getNiveauById(Long id);
	
	public void createNiveau(Niveau s);
	
	public List<Niveau> getAllNiveau();
	

}
