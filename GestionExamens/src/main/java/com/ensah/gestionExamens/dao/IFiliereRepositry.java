package com.ensah.gestionExamens.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.gestionExamens.bo.*;

public interface IFiliereRepositry extends JpaRepository<Filiere, Long>{

	public Filiere findFiliereByName(String name);
}
