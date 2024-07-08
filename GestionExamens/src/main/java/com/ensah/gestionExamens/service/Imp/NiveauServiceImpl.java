package com.ensah.gestionExamens.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.gestionExamens.bo.Niveau;
import com.ensah.gestionExamens.dao.INiveauRepository;
import com.ensah.gestionExamens.service.INiveauService;




@Service
@Transactional
public class NiveauServiceImpl implements INiveauService {

	@Autowired
	INiveauRepository nvrep;
	
	@Override
	public Optional<Niveau> getNiveauById(Long id) {
		
		return nvrep.findById(id);
	}

	@Override
	public void createNiveau(Niveau s) {
		nvrep.save(s);
		
	}

	@Override
	public List<Niveau> getAllNiveau() {
		
		return nvrep.findAll();
	}

	
	
}
