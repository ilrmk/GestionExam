package com.ensah.gestionExamens.service.Imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.gestionExamens.bo.Departement;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.dao.IDepartementRepositry;
import com.ensah.gestionExamens.service.IDepartementService;
@Service

public class DepartementServiceImp implements IDepartementService{
	@Autowired
	private IDepartementRepositry rep;
	@Override
	public ArrayList<Departement> findAllDepartement() {
 		return(ArrayList<Departement>)rep.findAll();

	}

	@Override
	public Departement findAllDepartementByID(long id) {
		Optional<Departement>	dep= rep.findById(id);
		if (dep.isPresent())
            return dep.get();
        else
            return null;
	}

	@Override
	public void addDepartement(Departement e) {
		rep.save(e);
	}

	@Override
	public void deleteAllData() {
		// TODO Auto-generated method stub
		
	}
	public  Departement findDepartementByname(String nom)
	{
		return rep.findDepartementByname(nom);
	}
	
		
		
	

}
