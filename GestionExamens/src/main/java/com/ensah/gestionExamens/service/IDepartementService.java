package com.ensah.gestionExamens.service;

import java.util.ArrayList;

import com.ensah.gestionExamens.bo.*;

public interface IDepartementService {

	ArrayList<Departement> findAllDepartement();
	Departement findAllDepartementByID(long id);
    void addDepartement(Departement e);
    void deleteAllData();
	public  Departement findDepartementByname(String nom);

	
	
	
}
