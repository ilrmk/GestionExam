package com.ensah.gestionExamens.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Groupe;


public interface IEnseignantService {
	ArrayList<Enseignant> findAllEnseignant();
	Enseignant findAllEnseignantByID(long id);
    void addEnseignant(Enseignant e);
    void deleteAllData(Enseignant e);
	public  ArrayList<Enseignant> findEnseignantBynom(String nom);
	public  Enseignant findEnseignantByPPR(String matricule);
	public void deleteEnseignant(Long id);
    public Set<Enseignant> findEnseignantsByIds(List<Long> ids);

}
