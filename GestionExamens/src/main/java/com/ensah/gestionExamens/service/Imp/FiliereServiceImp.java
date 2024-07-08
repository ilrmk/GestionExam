package com.ensah.gestionExamens.service.Imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.gestionExamens.bo.Departement;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Filiere;
import com.ensah.gestionExamens.dao.IFiliereRepositry;
import com.ensah.gestionExamens.service.IFiliereService;
@Service

public class FiliereServiceImp implements IFiliereService  {

	@Autowired
	private IFiliereRepositry filier;
	
	@Override
	public ArrayList<Filiere> findAllFiliere() {
 		return(ArrayList<Filiere>)filier.findAll();
	}

	@Override
	public Filiere findAllFiliereByID(long id) {
		Optional<Filiere>	filr= filier.findById(id);
		if (filr.isPresent())
            return filr.get();
        else
            return null;
	}

	@Override
	public void addFiliere(Filiere e) {
         filier.save(e);
	}

	@Override
	public void deleteAllData() {
		// TODO Auto-generated method stub
		
	}
	@Override

	public Filiere findFiliereByName(String name)
	{
		return filier.findFiliereByName(name);
	}
}
