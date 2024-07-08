package com.ensah.gestionExamens.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.gestionExamens.bo.Admin;

public interface IAdminRepositry extends JpaRepository<Admin,Long>{

	/*@Query("select e from Enseignant e join e.departement d join e.filiere f")
	public List<Enseignant> getALLEnsiengntAndDepartement();*/

	public ArrayList<Admin> findAdminBynom(String nom);
	public  Admin findAdminByPPR(String matricule);
	
}
