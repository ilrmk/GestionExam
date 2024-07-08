package com.ensah.gestionExamens.service.Imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Groupe;
import com.ensah.gestionExamens.dao.*;
import com.ensah.gestionExamens.service.IEnseignantService;
@Service
public class EnseingntServiceImp  implements IEnseignantService {
	@Autowired
    private  IEnseignantRepositry enseignantDao;
    
	public EnseingntServiceImp() {
		System.out.print("je suis test exam");
	}

	public ArrayList<Enseignant> findAllEnseignant()
	{
		return(ArrayList<Enseignant>)enseignantDao.findAll();
	}
    @Override

	public Enseignant findAllEnseignantByID(long id)
	{
		Optional<Enseignant>	ensg= enseignantDao.findById(id);
		if (ensg.isPresent())
            return ensg.get();
        else
            return null;

	}
    @Override

	public void addEnseignant(Enseignant e) {
    	
    		enseignantDao.save(e);
    	
    }
    @Override

	public void deleteAllData(Enseignant e) {
    	enseignantDao.delete(e);
    }
	

	@Override
	public  ArrayList<Enseignant> findEnseignantBynom(String nom) {
		return   enseignantDao.findEnseignantBynom(nom);
		
	}

	@Override
	public Enseignant findEnseignantByPPR(String matricule) {
		return enseignantDao.findEnseignantByPPR(matricule);
	}

	@Override
	public void deleteEnseignant(Long id) {
		enseignantDao.deleteById(id);		
	}

	
	@Override
	public Set<Enseignant> findEnseignantsByIds(List<Long> ids) {
	    Set<Enseignant> enseignants = new HashSet<>();
	    for (Long id : ids) {
	        Enseignant enseignant = findAllEnseignantByID(id);
	        if (enseignant != null) {
	            enseignants.add(enseignant);
	        }
	    }
	    return enseignants;
	}

		
		

	
}
