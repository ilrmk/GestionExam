package com.ensah.gestionExamens.web.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ensah.gestionExamens.bo.Element_pedagogique;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Filiere;
import com.ensah.gestionExamens.bo.Niveau;
import com.ensah.gestionExamens.model.ElementPedagogiqueModel;
import com.ensah.gestionExamens.model.PersonneModel;
import com.ensah.gestionExamens.service.IElement_pedagogiqueService;
import com.ensah.gestionExamens.service.IEnseignantService;
import com.ensah.gestionExamens.service.IFiliereService;
import com.ensah.gestionExamens.service.INiveauService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/module")
public class ModuleController {
	@Autowired
	private  IFiliereService FiliereService;
	@Autowired
	private INiveauService  niveauService;
	@Autowired
	private  IEnseignantService enseignantService;
	@Autowired
	private IElement_pedagogiqueService moduleService;
	
	@ModelAttribute
	  public  void modelView(Model model)
	{    
		model.addAttribute("module", new ElementPedagogiqueModel());
		if(niveauService.getAllNiveau()!=null)
		{
			model.addAttribute("listeNiveau",niveauService.getAllNiveau());
		}
	    else
	    {
			model.addAttribute("listeNiveau",new Niveau());
		}
		
		if(FiliereService.findAllFiliere()!=null)
		{
			model.addAttribute("listeFiliere",FiliereService.findAllFiliere());
		}
	    else
	    {
			model.addAttribute("listeFiliere",new Filiere());
		}
		if(enseignantService.findAllEnseignant()!=null)
		{
			model.addAttribute("ListEnseignant",enseignantService.findAllEnseignant());
		}
	    else
	    {
			model.addAttribute("ListEnseignant",new PersonneModel());
		}
		
	}
	@RequestMapping("/addModuleForm")
	public  String afficheModuleForm(Model model)
	{					
		return "Gestion/AjouterModulePage";
	}
	@RequestMapping("/consulterModule")
	public  String consulterModule(Model model,HttpServletRequest resp)
	{	
		ArrayList<Element_pedagogique>listElement=(ArrayList<Element_pedagogique>) resp.getAttribute("listElement");

       if(listElement!=null)
       {

 		  ArrayList<ElementPedagogiqueModel> listeModules = new ArrayList<>();

 		  for (Element_pedagogique elem_ped : listElement) {
 			  ElementPedagogiqueModel ensMo = new ElementPedagogiqueModel();

 			     BeanUtils.copyProperties(elem_ped, ensMo);
 			     
 			     listeModules.add(ensMo);
 			 }
 		  model.addAttribute("listeModules", listeModules);
 		  
 		  return "Gestion/ConsulterModulePage";
       }
		
       else {
		ArrayList<Element_pedagogique> elementPedagogiques = (ArrayList<Element_pedagogique>) moduleService.getAllElement_pedagogique();
		if(elementPedagogiques!=null)
		{
		  ArrayList<ElementPedagogiqueModel> listeModules = new ArrayList<>();

		  for (Element_pedagogique elem_ped : elementPedagogiques) {
			  ElementPedagogiqueModel ensMo = new ElementPedagogiqueModel();

			     BeanUtils.copyProperties(elem_ped, ensMo);
			     
			     listeModules.add(ensMo);
			 }
		  model.addAttribute("listeModules", listeModules);
		  
		  return "Gestion/ConsulterModulePage";
			
		}
		else {
			return "Gestion/ConsulterModulePage";

		}}
	}
	
	
	
	
	
	
	 @PostMapping("/addModule")
	    public String AjouterModule(@Valid @ModelAttribute("module") ElementPedagogiqueModel module, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("module", module);
	            model.addAttribute("messageErreur", "Il faut vérifier les champs...");

	            return "Gestion/AjouterModulePage";
	        } else {
	            Long idModule = module.getId();
	            if (idModule != 0) {
	                Element_pedagogique elementModule = moduleService.getElement_pedagogiqueById(module.getId());
	                BeanUtils.copyProperties(module, elementModule);
	                moduleService.createElement_pedagogique(elementModule);
	                redirectAttributes.addFlashAttribute("message", "Bien enregistré");
	            } else {
	                Element_pedagogique elementPed = new Element_pedagogique();
	                BeanUtils.copyProperties(module, elementPed);
	                moduleService.createElement_pedagogique(elementPed);
	                redirectAttributes.addFlashAttribute("message", "Bien enregistré");
	            }
	            return "redirect:/module/addModuleForm";
	        }
	    }

	
	
	@GetMapping("/modifier/{ModuleId}")
	public String modifierModule(@PathVariable("ModuleId") int idModule,Model  model)
	{     
		Element_pedagogique  element_peda =moduleService.getElement_pedagogiqueById(Long.valueOf(idModule));

		if(element_peda!=null)
		{
		     ElementPedagogiqueModel element = new ElementPedagogiqueModel();
		     BeanUtils.copyProperties(element_peda, element);
			model.addAttribute("module", element);
			return "Gestion/AjouterModulePage";
		}
		else {
			return "redirect:/module/consulterModule";

		}
		
	}

	@GetMapping("/supprimer/{ModuleId}")
	public String supprimerModule(@PathVariable("ModuleId") int idModule,Model  model)
	{
		Element_pedagogique  element_peda =moduleService.getElement_pedagogiqueById(Long.valueOf(idModule));
		if(element_peda!=null)
		{
			moduleService.deletElement_pedagogique(element_peda);
			return "redirect:/module/consulterModule";

		}
		else {
			model.addAttribute("message","l'opération n'est pas effectuer avec succe ....");
			return "redirect:/module/consulterModule";

		}
	}
	
	@PostMapping("/chercher")
	public String chercherParNom(@RequestParam("nom") String nom,HttpServletRequest resp)
	{
		ArrayList<Element_pedagogique> listModuleType=(ArrayList<Element_pedagogique>) moduleService.getAllElement_pedagogiqueByType(nom);
		ArrayList<Element_pedagogique> listModuleNam=(ArrayList<Element_pedagogique>) moduleService.getAllElement_pedagogiqueByTitre(nom);
		if(listModuleType==null && listModuleNam==null )
		{
			
			return "Gestion/ConsulterModulePage";
		}
		else
		{
			if(listModuleType!=null) {
				resp.setAttribute("listElement", listModuleType);
			}
			else
			{
				if(listModuleNam!=null)
				{
					resp.setAttribute("listElement", listModuleNam);

				}
			   }
			

		    return "forward:/module/consulterModule";

		}
		
	}
	
	
	
	
}
