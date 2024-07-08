package com.ensah.gestionExamens.service;

import java.util.ArrayList;

import com.ensah.gestionExamens.bo.*;

public interface IFiliereService {
	ArrayList<Filiere> findAllFiliere();
	Filiere findAllFiliereByID(long id);
    void addFiliere(Filiere e);
    void deleteAllData();
	public Filiere findFiliereByName(String name);

}
