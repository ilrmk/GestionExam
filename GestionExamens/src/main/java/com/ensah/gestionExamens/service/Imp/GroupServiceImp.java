package com.ensah.gestionExamens.service.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Groupe;
import com.ensah.gestionExamens.dao.IGroupRepository;
import com.ensah.gestionExamens.service.IEnseignantService;
import com.ensah.gestionExamens.service.IGroupService;
@Service
public class GroupServiceImp implements IGroupService {

	@Autowired
	private IGroupRepository groupRepositry;
	@Autowired
	private IEnseignantService enseignantService;
	@Override
	public ArrayList<Groupe> findAllGroupe() {
		return (ArrayList<Groupe>) groupRepositry.findAll();
	}

	@Override
	public Groupe findAllGroupeByID(long id) {
		Optional<Groupe>	group= groupRepositry.findById(id);
		if (group.isPresent())
            return group.get();
        else
            return null;

	}

	@Override
	public void addGroupe(Groupe g) {

		groupRepositry.save(g);
	}

	@Override
	public void deleteAllData(Groupe g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Groupe> findGroupeBynom(String nom) {
		return groupRepositry.findGroupeBynom(nom);
	}

	@Override
	public Groupe findGroupeByPPR(String matricule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGroupe(Long id) {
		groupRepositry.deleteById(id);		
	}
	
	public ArrayList<Groupe> findGroupeByCretaire(String cretaire) {
		
		return groupRepositry.findGroupeBycretaire(cretaire);

	}
	@Override
    public void deleteEnseignant(Long id_e,Long id_g) {
        Enseignant enseignant = enseignantService.findAllEnseignantByID(id_e);
        Groupe group =findAllGroupeByID(id_g);

        if (group != null && enseignant != null) {
            Set<Enseignant> enseignants = group.getEnseignants_group();
            if (enseignants != null && enseignants.contains(enseignant)) {
                enseignants.remove(enseignant);
                group.setEnseignants_group(enseignants);
                groupRepositry.save(group);
            }
        } else {
            // Handle the case where the group or the teacher doesn't exist
            if (group == null) {
                throw new IllegalArgumentException("Group with id " + id_g + " not found");
            }
            if (enseignant == null) {
                throw new IllegalArgumentException("Enseignant with id " + id_e + " not found");
            }
        }
    }

	
	


}
