package com.ensah.gestionExamens.service.Imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.gestionExamens.bo.Admin;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.dao.IAdminRepositry;
import com.ensah.gestionExamens.service.IAdminService;
@Service
public class AdminServiceImp implements IAdminService {

	
	@Autowired
    private  IAdminRepositry adminDao;
	@Override
	public ArrayList<Admin> findAllAdmin() {
		return (ArrayList<Admin>) adminDao.findAll();
	}

	@Override
	public Admin findAllAdminByID(long id) {
		Optional<Admin>	ensg= adminDao.findById(id);
		if (ensg.isPresent())
            return ensg.get();
        else
            return null;
	}

	@Override
	public void addAdmin(Admin e) {
		adminDao.save(e);

	}

	@Override
	public void deleteAllData(Admin e) {
    	adminDao.delete(e);
		
	}

	@Override
	public ArrayList<Admin> findAdminBynom(String nom) {
		return adminDao.findAdminBynom(nom);
	}

	@Override
	public Admin findAdminByPPR(String matricule) {
		return adminDao.findAdminByPPR(matricule);
	}

	@Override
	public void deleteAdmin(Long id) {
        adminDao.deleteById(id);		
	}

}
