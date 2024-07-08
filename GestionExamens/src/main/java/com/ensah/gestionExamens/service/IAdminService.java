package com.ensah.gestionExamens.service;

import java.util.ArrayList;

import com.ensah.gestionExamens.bo.Admin;

public interface IAdminService {

	ArrayList<Admin> findAllAdmin();
	Admin findAllAdminByID(long id);
    void addAdmin(Admin e);
    void deleteAllData(Admin e);
	public  ArrayList<Admin> findAdminBynom(String nom);
	public  Admin findAdminByPPR(String matricule);
	public void deleteAdmin(Long id);

}
