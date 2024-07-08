package com.ensah.gestionExamens.service;

import java.util.List;
import java.util.Optional;

import com.ensah.gestionExamens.bo.Element_pedagogique;
import com.ensah.gestionExamens.bo.Examen;
import com.ensah.gestionExamens.bo.Salle;
import com.ensah.gestionExamens.bo.Surveillance;



public interface ISurveillanceService {

    public Surveillance getSurveillanceById(Long id);
	
	public void ajouterSurveillance(Surveillance s);
	
	public List<Surveillance> creerSurveillances(Long idCood,List<Long> salles,List<Integer> nbSurv,Element_pedagogique module,int manierSelection,Examen exam);
	
	public List<Surveillance> getAllSurveillance();
	
	public boolean supprimer(Long id);
	
	
	public void supprimerAllSurveillanceOfExam(Examen e);

	
}
