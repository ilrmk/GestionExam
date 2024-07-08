package com.ensah.gestionExamens.web.controller;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ensah.gestionExamens.bo.Admin;
import com.ensah.gestionExamens.bo.Departement;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Filiere;
import com.ensah.gestionExamens.model.PersonneModel;
import com.ensah.gestionExamens.service.IAdminService;
import com.ensah.gestionExamens.service.IDepartementService;
import com.ensah.gestionExamens.service.IEnseignantService;
import com.ensah.gestionExamens.service.IFiliereService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class EnseignantController {

	@Autowired
	private  IEnseignantService enseignantService;
	@Autowired
	private  IAdminService adminService;
	@Autowired
	private  IDepartementService departementService;
	@Autowired
	private  IFiliereService FiliereService;
	@ModelAttribute
	public void ensfil(Model model)
	{
		if(departementService.findAllDepartement()!=null&&FiliereService.findAllFiliere()!=null) {
		model.addAttribute("listeDepartemet", departementService.findAllDepartement());
        model.addAttribute("listeFiliere",FiliereService.findAllFiliere());
		}
	}
	@PostMapping("/addPersonne")
    public String ajouterEn(@Valid @ModelAttribute("PersonneModel") PersonneModel personne, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        System.out.print("toII");

        if (bindingResult.hasErrors()) {
            System.out.print("to");
            model.addAttribute("messageErreur", "Il faut saisir tous les champs....");
            model.addAttribute("PersonneModel", personne);

            if (personne.getTypePerson().equals("prof")) {
                ensfil(model);
                return "Gestion/ajouterEnsView";
            } else {
                return "Gestion/ajouterAdminPage";
            }
        } else {
            if (personne.getTypePerson().equals("prof")) {
                Enseignant ensg;

                if (personne.getId() != 0) {
                    ensg = enseignantService.findAllEnseignantByID(personne.getId());
                } else {
                    ensg = new Enseignant();
                }

                BeanUtils.copyProperties(personne, ensg);
                enseignantService.addEnseignant(ensg);
                redirectAttributes.addFlashAttribute("message", "Bien enregistré");
                System.out.print("VOILAEn");

                return "redirect:/ajouterEns";
            } else {
                Admin admin;

                if (personne.getId() != 0) {
                    admin = adminService.findAllAdminByID(personne.getId());
                } else {
                    admin = new Admin();
                }

                BeanUtils.copyProperties(personne, admin);
                adminService.addAdmin(admin);
                redirectAttributes.addFlashAttribute("message", "Bien enregistré");
                System.out.print("VOILAadmin");

                return "redirect:/ajouterAdmin";
            }
        }
	}

	 @RequestMapping("/ajouterEns")
	 public String ajouterEnsPage(Model model) {
	    
	     PersonneModel enseignantModel= new PersonneModel();
	     enseignantModel.setTypePerson("prof");
	     model.addAttribute("PersonneModel", enseignantModel);

	     return "Gestion/ajouterEnsView";
	 }

	 
	 @GetMapping("/modifier/{personeType}/{personeId}")
	 public String modifierEnseignantView(Model model, @PathVariable("personeId") int id,@PathVariable("personeType") String type,HttpServletRequest Request) {
	    
         if(type.equals("prof"))
         {
		 Enseignant enseignant = enseignantService.findAllEnseignantByID(id);
	     PersonneModel ensMo = new PersonneModel();
	     BeanUtils.copyProperties(enseignant, ensMo);
	     ensMo.setId(id);
	     ensMo.setTypePerson(type);
	     
	     model.addAttribute("PersonneModel",ensMo);
	     return "Gestion/ajouterEnsView";
	 }
         else {
        	 
        	 Admin admin = adminService.findAllAdminByID(id);
    	     PersonneModel ensMo = new PersonneModel();
    	     BeanUtils.copyProperties(admin, ensMo);
    	     ensMo.setId(id);
    	     ensMo.setTypePerson(type);

    	     model.addAttribute("PersonneModel",ensMo);
        	 
        	 
        	 
        	 
        	 return "Gestion/AjouterAdminPage";
         }
         
	 }

	
	 @RequestMapping(value="/supprimer/{personeType}/{personeId}", method = RequestMethod.GET)
	 public String supprimerEns( @PathVariable("personeId") int id,@PathVariable("personeType") String type) {
         if(type.equals("prof"))
         {
		 enseignantService.deleteEnseignant(Long.valueOf(id));
	     return "redirect:/gereEns";
         }
         else {
        	 adminService.deleteAdmin(Long.valueOf(id));
    	     return "redirect:/manageAdmin";
         }
	    
	     
	 }
	 


	 
	 @RequestMapping("/gereEns")
	 public String géréEnsPage(Model model)
	 {
		 ArrayList<PersonneModel> enseignantModel = new ArrayList<>();

		 ArrayList<Enseignant> enseignants = enseignantService.findAllEnseignant();

		 for (Enseignant enseignant : enseignants) {
			 PersonneModel ensMo = new PersonneModel();
			 ensMo.setTypePerson("prof");

		     BeanUtils.copyProperties(enseignant, ensMo);
		    
		     enseignantModel.add(ensMo);
		 }

			 model.addAttribute("listeEnseignant",enseignantModel);
			 

		 return "Gestion/géréEnsView";
	 }
	
	@PostMapping("/chercherEnseignant")
	public String chercherParNom(@RequestParam("matricule") String matricule,@RequestParam("type") String type,Model model)
	{
		if(type.equals("prof"))
		{
		
		ArrayList<PersonneModel> enseignantModel = new ArrayList<>();

		 ArrayList<Enseignant> enseignants = enseignantService.findEnseignantBynom(matricule);
		 for (Enseignant enseignant : enseignants) {
			 PersonneModel ensMo = new PersonneModel();

		     BeanUtils.copyProperties(enseignant, ensMo);
		    
		     enseignantModel.add(ensMo);
		 }

			 model.addAttribute("listeEnseignant",enseignantModel);
			 

		 return "Gestion/gestionEnseignant";
		 }
		else{
			ArrayList<PersonneModel> enseignantModel = new ArrayList<>();

			 ArrayList<Admin> admins = adminService.findAdminBynom(matricule);
			 for (Admin admin : admins) {
				 PersonneModel ensMo = new PersonneModel();

			     BeanUtils.copyProperties(admin, ensMo);
			     ensMo.setTypePerson(type);
			     enseignantModel.add(ensMo);
			 }

				 model.addAttribute("listeEnseignant",enseignantModel);
				 

			 return "Gestion/gestionAdmin";
			
		}
		
	}
	
	
	
	
	@PostMapping("/chercherPourGére")
	public String chercherParNomPourGer(@RequestParam("matricule") String matricule,@RequestParam("type") String type,Model model)
	{
		if(type.equals("prof"))
		{
			
		
		ArrayList<PersonneModel> enseignantModel = new ArrayList<>();

		 ArrayList<Enseignant> enseignants = enseignantService.findEnseignantBynom(matricule);
		 for (Enseignant enseignant : enseignants) {
			 PersonneModel ensMo = new PersonneModel();

		     BeanUtils.copyProperties(enseignant, ensMo);
		     enseignantModel.add(ensMo);
		 }

			 model.addAttribute("listeEnseignant",enseignantModel);
			 

		 return "Gestion/géréEnsView";
		}
		else{
			ArrayList<PersonneModel> enseignantModel = new ArrayList<>();

			 ArrayList<Admin> admins = adminService.findAdminBynom(matricule);
			 for (Admin admin : admins) {
				 PersonneModel ensMo = new PersonneModel();

			     BeanUtils.copyProperties(admin, ensMo);
			     ensMo.setTypePerson(type);
			     enseignantModel.add(ensMo);
			 }

				 model.addAttribute("listeEnseignant",enseignantModel);
				 

			 return "Gestion/mangAdmin";
			
		}
	}
	
	
	
	
	
	@RequestMapping("/ajouterAdmin")
	 public String ajouterAdminPage(Model model) {
	     
		PersonneModel adminModel= new PersonneModel();
	     adminModel.setTypePerson("admin");
	     model.addAttribute("PersonneModel",adminModel);

	     return "Gestion/AjouterAdminPage";
	 }
	
	
	
	 @RequestMapping("/manageAdmin")
	 public String géréAdminPage(Model model)
	 {
		 ArrayList<PersonneModel> adminModel = new ArrayList<>();

		 ArrayList<Admin> admins = adminService.findAllAdmin();

		 for (Admin admin : admins) {
			 PersonneModel ensMo = new PersonneModel();
			 ensMo.setTypePerson("admin");
		     BeanUtils.copyProperties(admin, ensMo);
		     
		     adminModel.add(ensMo);

		 }

			 model.addAttribute("listeEnseignant",adminModel);
			 

		 return "Gestion/mangAdmin";
	 }
	
	
}
