package com.ensah.gestionExamens.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.gestionExamens.bo.Element_pedagogique;
import com.ensah.gestionExamens.dao.IElement_pedagogiqueRepository;
import com.ensah.gestionExamens.service.IElement_pedagogiqueService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class Element_pedagogiqueServiceImpl implements IElement_pedagogiqueService {

	@Autowired
	IElement_pedagogiqueRepository elm_pedag;
	
	@Override
	public Element_pedagogique getElement_pedagogiqueById(Long id) {
		// TODO Auto-generated method stub
		return   elm_pedag.getById(id);
	}

	@Override
	public void createElement_pedagogique(Element_pedagogique s) {
      int sum=s.getNombreHeuresCours()+s.getNombreHeuresTD()+s.getNombreHeuresTp();
      if(sum==s.getVolumeHoraire())
      {
  		elm_pedag.save(s);
      }
	}

	@Override
	public List<Element_pedagogique> getAllElement_pedagogique() {
		// TODO Auto-generated method stub
		return elm_pedag.findAll();
	}

	@Override
	public void deletElement_pedagogique(Element_pedagogique module) {
		elm_pedag.delete(module);		
	}

	@Override
	public List<Element_pedagogique> getAllElement_pedagogiqueByTitre(String titre) {

		return (List<Element_pedagogique>) elm_pedag.findElement_pedagogiqueByTitre(titre);
	}

	@Override
	public List<Element_pedagogique> getAllElement_pedagogiqueByType(String type) {
		return (List<Element_pedagogique>) elm_pedag.findElement_pedagogiqueByType(type);

	}

}
