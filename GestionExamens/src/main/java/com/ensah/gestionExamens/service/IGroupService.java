package com.ensah.gestionExamens.service;

import java.util.ArrayList;
import java.util.List;

import com.ensah.gestionExamens.bo.Groupe;

public interface IGroupService  {

	ArrayList<Groupe> findAllGroupe();
		Groupe findAllGroupeByID(long id);
	    void addGroupe(Groupe g);
	    void deleteAllData(Groupe g);
		public  ArrayList<Groupe> findGroupeBynom(String nom);
		public  Groupe findGroupeByPPR(String matricule);
		public ArrayList<Groupe> findGroupeByCretaire(String cretaire);
		public void deleteGroupe(Long id);
		 public void deleteEnseignant(Long id_e, Long id_g);

}