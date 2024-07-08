package com.ensah.gestionExamens.web.controller;


import com.ensah.gestionExamens.model.ExamenModel;
import com.ensah.gestionExamens.model.ExamenModifierModel;
import com.ensah.gestionExamens.service.IElement_pedagogiqueService;
import com.ensah.gestionExamens.service.IEnseignantService;
import com.ensah.gestionExamens.service.IExamenService;
import com.ensah.gestionExamens.service.INiveauService;
import com.ensah.gestionExamens.service.ISalleService;
import com.ensah.gestionExamens.service.ISurveillanceService;
import com.ensah.gestionExamens.bo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@Controller
public class ExamenController {

	@Autowired
	ISalleService sallesrv;
	
	@Autowired
	INiveauService niveausrv;
	
	@Autowired
	IElement_pedagogiqueService elmpedagsrv;
	
	@Autowired
	IEnseignantService enseignsrv;
	
	@Autowired
	IExamenService examsrv;
	
	@Autowired 
	ISurveillanceService survSrv;
	
	
	@ModelAttribute
	public void initialisation(Model model) {
		
        model.addAttribute("examenMdl", new ExamenModel());
		
		// Obtenir la date système
        LocalDate currentDate = LocalDate.now();
        // Obtenir le mois
        int currentMonth = currentDate.getMonthValue();
         
     
        // Déterminer le semestre par défaut en fonction du mois
        String defaultSemestre = (currentMonth >= 3 && currentMonth <= 8) ? "Printemps" : "Automne";

        // Ajouter le semestre par défaut au modèle
        model.addAttribute("defaultSemestre", defaultSemestre);
		
		model.addAttribute("ListNiveau", niveausrv.getAllNiveau());
		model.addAttribute("ListSalle", sallesrv.getAllSalle());
		model.addAttribute("ListModule", elmpedagsrv.getAllElement_pedagogique());
		model.addAttribute("ListEnseignant", enseignsrv.findAllEnseignant());
        model.addAttribute("anneeUniMap", examsrv.getAllAnneeUni());
		String defaulttype;
		
		if(currentMonth >= 3 && currentMonth <=4) {
		 defaulttype="DS1";
		}else if(currentMonth > 4 && currentMonth <=5){
			 defaulttype="DS2";
		}
		else {
			defaulttype="Examen final";
		}
		model.addAttribute("defaulttype", defaulttype);
		
		
	}
	
	@GetMapping("/formExamen")
	public String form(Model model) {
	
		return "GestionExamen/CreerExamen";
	}
	
	
	
	@PostMapping("/formExamen")
	public String traiteForme(@Valid @ModelAttribute("examenMdl") ExamenModel examenMdl ,BindingResult bindingResult,
			Model model ,HttpServletRequest rq) {
		
		// En cas d'erreur de validation
		if (bindingResult.hasErrors()) {
					rq.setAttribute("messageErreur", "vous reste des champ non remplit ou non sataistfait ");
					return "GestionExamen/CreerExamen";
				}
		System.out.println("Lors de creation "+examenMdl);
		
		
		Examen exam = new Examen();
		BeanUtils.copyProperties(examenMdl, exam);
		
		exam.setModule(elmpedagsrv.getElement_pedagogiqueById(examenMdl.getModuleId()));

		System.out.println("Lors de création "+exam);
		exam.setSurvs(survSrv.creerSurveillances(examenMdl.getCoordinnateur(), examenMdl.getSelectedSalleIds(),examenMdl.getNbsurveillantsParSalle() ,elmpedagsrv.getElement_pedagogiqueById(examenMdl.getModuleId()) , examenMdl.getSelectedOption(), exam));
		examsrv.ajouterExamen(exam);
		
		return "redirect:/GererExamen";
	}
	
	@GetMapping("/GererExamen")
	public String listerExamen(Model model) {
		
		model.addAttribute("ListExamen", examsrv.getAllNouvExam());
		
		return "GestionExamen/GererExamen";
	}
	
	
	@GetMapping("/ModifierExamForm")
	public String modifierForm(@RequestParam  Long id,Model model ) {
		
		
		model.addAttribute("examenMoMdl", new ExamenModifierModel());
		

		model.addAttribute("ListNiveau", niveausrv.getAllNiveau());
		model.addAttribute("ListSalle", sallesrv.getAllSalle());
		model.addAttribute("ListModule", elmpedagsrv.getAllElement_pedagogique());
		model.addAttribute("ListEnseignant", enseignsrv.findAllEnseignant());
		
		
		
		model.addAttribute("ExamenAmodifier",examsrv.getExamenById(id));
		
		System.out.println("Affichage de form de mod avec :"+examsrv.getExamenById(id));
		return "GestionExamen/ModifierExam";
	}
	
	@PostMapping("/ModifierExam")
	public String modfier(Model model,@Valid @ModelAttribute("examenMoMdl") ExamenModifierModel examenMdl ,BindingResult bindingResult,
			HttpServletRequest rq) {
		// En cas d'erreur de validation
		System.out.println(examenMdl);
				if (bindingResult.hasErrors()) {
							rq.setAttribute("messageErreur", "vous reste des champ non remplit ou non sataistfait ");
							model.addAttribute("examenMoMdl", new ExamenModifierModel());
							
							model.addAttribute("ListNiveau", niveausrv.getAllNiveau());
							model.addAttribute("ListSalle", sallesrv.getAllSalle());
							model.addAttribute("ListModule", elmpedagsrv.getAllElement_pedagogique());
							model.addAttribute("ListEnseignant", enseignsrv.findAllEnseignant());						
							model.addAttribute("ExamenAmodifier",examsrv.getExamenById(examenMdl.getId()));
							return "GestionExamen/ModifierExam";
				}else {
					System.out.println("Lors de la modification "+examenMdl);
					
					Examen exam = new Examen();
					BeanUtils.copyProperties(examenMdl, exam);
					
					exam.setModule(elmpedagsrv.getElement_pedagogiqueById(examenMdl.getModuleId()));
					System.out.println("Lors de modification d'examen "+exam);
					
					List<Long> salles =examsrv.getAllSalleExam(examsrv.getExamenById(exam.getId()));
					
					if(examenMdl.getSelectedSalleIds()!=null)
					salles.addAll( examenMdl.getSelectedSalleIds());
					System.out.println(salles);
					survSrv.supprimerAllSurveillanceOfExam(examsrv.getExamenById(exam.getId()));
					int so = (examenMdl.getSelectedOption() != null) ? examenMdl.getSelectedOption().intValue() : 1;

					exam.setSurvs(survSrv.creerSurveillances(examenMdl.getCoordinnateur(),salles,examenMdl.getNbsurveillantsParSalle() ,elmpedagsrv.getElement_pedagogiqueById(examenMdl.getModuleId()) , so, exam));
					
					System.out.println(exam.getSurvs().size());
					examsrv.ajouterExamen(exam);
				}
		
		return "redirect:/GererExamen";
	}
	
	
	@GetMapping("/SupprimerExamen")
	public String supprimerExam(@RequestParam  Long id){
		
		examsrv.supprimerExam(id);
		return "redirect:/GererExamen";
		
	}
	
	 @GetMapping("/SupprimerSurveillance/{examId}/{survId}")
	    public String supprimerSurveillance(@PathVariable("examId") Long examId, @PathVariable("survId") Long survId, RedirectAttributes redirectAttributes) {
	        System.out.println("Surveillance ID: " + survId);
	        System.out.println("Examen ID: " + examId);

	        boolean sup = survSrv.supprimer(survId);
	        if (sup==false) {
	        	 redirectAttributes.addFlashAttribute("messageErreur", "Impossible de supprimer la surveillance car c'est la seule qui reste pour cet examen.");
	        }
	        return "redirect:/ModifierExamForm?id=" + examId;
	    }
	
	
	
	@GetMapping("/AnnulerMod")
	public String annuler(Model model) {
		model.addAttribute("ListExamen", examsrv.getAllNouvExam());
		return "GestionExamen/GererExamen";
	}
	
	
	
	@GetMapping("/chercherExam")
	public String chercher(@RequestParam(value = "id", required = false) Long id,
	                       @RequestParam(value = "niveau", required = false) Long niveau,
	                       @RequestParam(value = "semestre", required = false) String semestre,
	                       @RequestParam(value = "annee", required = false) String annee,
	                       Model model) {
		System.out.println(niveau);

	    List<Examen> examens = new ArrayList<>();

	   
	    if (id != null) {
	        examens = examsrv.getExamByIdAncein(id);
	    } 
	    
	    else if (niveau ==null && semestre == "" && annee == "") {
	        System.out.println("tout les champs null");
	        examens = examsrv.getAllAnceinExam();
	    } 
	    // Sinon, ajouter votre logique pour traiter les autres combinaisons de critères ici
	    else {
	    	System.out.println("Dans les critique "+examsrv.getExamensByCriteria(niveau, semestre, annee));
	    	examens = examsrv.getExamensByCriteria(niveau, semestre, annee);
	    }

	    // Ajouter la liste des examens au modèle
	    model.addAttribute("examens", examens);

		return "GestionExamen/Consulter";
	}
	
	@GetMapping("/ConsulterExamen")
	public String consulter(Model model) {
	    return "GestionExamen/Consulter";
	}

	@GetMapping("/ConsulterDetaille/{examId}")
	public String detaille(@PathVariable("examId") Long examId,Model model) {
		model.addAttribute("examen", examsrv.getExamenById(examId));

      model.addAttribute("anneeUniversitaire",examsrv.getExamenById(examId).getAnneeUniversitaire());
      model.addAttribute("anneeSuivante",Integer.parseInt(examsrv.getExamenById(examId).getAnneeUniversitaire())+1);
		
		return "GestionExamen/ConsulterDetaille";
	}
	
	
	
	@GetMapping("/ConfirmerExam")
	public String confirmer(Model model) {
		model.addAttribute("ListExamConfirm", examsrv.getNonConfirmExam());
	    return "GestionExamen/ConfirmerExam";
	}
	
	@GetMapping("/examAconfirmer")
	public String confirmerexame(@RequestParam(value = "id",required = false) Long examId,Model model) {
		model.addAttribute("examen", examsrv.getExamenById(examId));
		System.out.println(examsrv.getExamenById(examId));
		model.addAttribute("ListExamConfirm", examsrv.getNonConfirmExam());
	    return "GestionExamen/ConfirmerExam";
	}
	
	
	
	 @PostMapping("/confirm")
	    public String confirmExam(@RequestParam("id") Long id,
	                              @RequestParam("dureeReel") String dureeReel,
	                              @RequestParam("rapport") String rapport,
	                              @RequestParam("pv") MultipartFile pvFile,
	                              @RequestParam("enonce") MultipartFile enonceFile,
	                              Model model) {
		 model.addAttribute("ListExamConfirm", examsrv.getNonConfirmExam());
		   System.out.println(id);
		   System.out.println(dureeReel);
		   examsrv.confirmerExamen(id,dureeReel,rapport,pvFile,enonceFile,examsrv.getExamenById(id));
	        // Ajouter des attributs au modèle pour confirmation ou redirection
	        model.addAttribute("message", "Examen confirmé avec succès");
	        return "GestionExamen/ConfirmerExam"; 
	    }

}
