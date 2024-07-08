package com.ensah.gestionExamens.dao;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.gestionExamens.bo.Groupe;

public interface IGroupRepository extends JpaRepository<Groupe,Long> {
	public ArrayList<Groupe> findGroupeBynom(String nom);
	
	public ArrayList<Groupe> findGroupeBycretaire(String cretaire);

}
