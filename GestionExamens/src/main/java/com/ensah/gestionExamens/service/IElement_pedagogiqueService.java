package com.ensah.gestionExamens.service;

import java.util.List;
import java.util.Optional;

import com.ensah.gestionExamens.bo.Element_pedagogique;

public interface IElement_pedagogiqueService {

	public Element_pedagogique getElement_pedagogiqueById(Long id);
	
	public void createElement_pedagogique(Element_pedagogique s);
	
	public List<Element_pedagogique> getAllElement_pedagogique();
	public void deletElement_pedagogique(Element_pedagogique module);
	
	public List<Element_pedagogique> getAllElement_pedagogiqueByTitre(String titre);
	public List<Element_pedagogique> getAllElement_pedagogiqueByType(String type);

}
