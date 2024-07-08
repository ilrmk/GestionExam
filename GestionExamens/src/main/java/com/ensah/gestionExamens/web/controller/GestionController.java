package com.ensah.gestionExamens.web.controller;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ensah.gestionExamens.bo.Admin;
import com.ensah.gestionExamens.bo.Departement;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Filiere;
import com.ensah.gestionExamens.model.PersonneModel;
import com.ensah.gestionExamens.service.IAdminService;
import com.ensah.gestionExamens.service.IDepartementService;
import com.ensah.gestionExamens.service.IEnseignantService;
import com.ensah.gestionExamens.service.IFiliereService;

import jakarta.validation.Valid;


@Controller
public class GestionController {
	@Autowired
	private  IEnseignantService enseignantService;
	@Autowired
	private  IDepartementService departementService;
	@Autowired
	private  IFiliereService FiliereService;
	@Autowired
	private  IAdminService adminService;
	
	@RequestMapping("/homeView")
	 public String GestionhomView()

	 {
		 return "home";
	 }
	
	
	
	 @RequestMapping("/GestionAdminView")
	 public String GestionAdminView(Model model)
	 {
		 ArrayList<PersonneModel> eadminModel = new ArrayList<>();

		 ArrayList<Admin> admins = adminService.findAllAdmin();

		 for (Admin admin :admins) {
		     PersonneModel ensMo = new PersonneModel();

		     BeanUtils.copyProperties(admin, ensMo);		    
		     eadminModel.add(ensMo);
		 }

			 model.addAttribute("listeEnseignant",eadminModel);
			 
		 return "Gestion/gestionAdmin";
	 }
	 
	 
	 
	 @RequestMapping("/GestionEnsView")
	 public String GestionEnsView(Model model)

	 {
		 ArrayList<PersonneModel> enseignantModel = new ArrayList<>();

		 ArrayList<Enseignant> enseignants = enseignantService.findAllEnseignant();

		 for (Enseignant enseignant : enseignants) {
		     PersonneModel ensMo = new PersonneModel();

		     BeanUtils.copyProperties(enseignant, ensMo);
		    
		     enseignantModel.add(ensMo);
		 }

			 model.addAttribute("listeEnseignant",enseignantModel);
			 if(departementService.findAllDepartement()!=null&&FiliereService.findAllFiliere()!=null) {
					model.addAttribute("listeDepartemet", departementService.findAllDepartement());
			        model.addAttribute("listeFiliere",FiliereService.findAllFiliere());
					}

		 return "Gestion/gestionEnseignant";
	 }
	 
	 
	 
	 
	
}
