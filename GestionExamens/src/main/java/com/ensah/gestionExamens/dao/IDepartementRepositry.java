package com.ensah.gestionExamens.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.gestionExamens.bo.*;

public interface IDepartementRepositry extends JpaRepository<Departement, Long> {
	public  Departement findDepartementByname(String nom);

}
