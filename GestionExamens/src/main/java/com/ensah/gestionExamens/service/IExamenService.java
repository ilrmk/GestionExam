package com.ensah.gestionExamens.service;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import com.ensah.gestionExamens.bo.Element_pedagogique;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Examen;


public interface IExamenService {

    public Examen getExamenById(Long id);
	
	public void ajouterExamen(Examen s);
	
	public List<Examen> getAllExamen();
	public void supprimerExam(Long id);
	
	public List<Long> getAllSalleExam(Examen e);
	
	public Map<String,String> getAllAnneeUni();
	
	public List<Examen> getExamensByAnnee(String annee);
	
	public List<Examen> getExamByIdAncein(Long id);
	
	public List<Examen> getAllAnceinExam();
	
	public List<Examen> getAllNouvExam();
	
	public List<Examen> getExamensByCriteria(Long niveau, String semestre, String annee);
	
	public void confirmerExamen(Long id,String dureeReel,String rapport, MultipartFile pvFile,MultipartFile enonceFile,Examen examen );

	
	public List<Examen> getNonConfirmExam();
	
	
	
}
