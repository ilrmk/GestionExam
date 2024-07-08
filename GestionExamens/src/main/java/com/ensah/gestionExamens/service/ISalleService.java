package com.ensah.gestionExamens.service;

import java.util.List;
import java.util.Optional;

import com.ensah.gestionExamens.bo.Salle;

public interface ISalleService {
	
	
	public Salle getSalleById(Long id);
	
	public void createSalle(Salle s);
	
	public List<Salle> getAllSalle();
	

}
