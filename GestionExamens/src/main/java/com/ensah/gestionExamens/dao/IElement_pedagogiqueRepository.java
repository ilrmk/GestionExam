package com.ensah.gestionExamens.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.gestionExamens.bo.Element_pedagogique;

public interface IElement_pedagogiqueRepository extends JpaRepository<Element_pedagogique,Long> {
	
	
	
	public ArrayList <Element_pedagogique> findElement_pedagogiqueByTitre(String titre);
	
	public ArrayList <Element_pedagogique> findElement_pedagogiqueByType(String type);
	
	
	

}
