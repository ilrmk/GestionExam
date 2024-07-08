package com.ensah.gestionExamens.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.gestionExamens.bo.Salle;
import com.ensah.gestionExamens.dao.ISalleRepository;
import com.ensah.gestionExamens.service.ISalleService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class SalleServiceImpl implements ISalleService{

	@Autowired
	ISalleRepository salleRep;
	
	@Override
	public Salle getSalleById(Long id) {
		// TODO Auto-generated method stub
		return salleRep.getById(id);
	}

	@Override
	public void createSalle(Salle s) {
		salleRep.save(s);
		
	}

	@Override
	public List<Salle> getAllSalle() {
		// TODO Auto-generated method stub
		return salleRep.findAll();
	}

}
