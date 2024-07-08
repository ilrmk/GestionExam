package com.ensah.gestionExamens.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.ensah.gestionExamens.bo.Admin;
import com.ensah.gestionExamens.bo.Departement;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Filiere;
import com.ensah.gestionExamens.bo.Groupe;
import com.ensah.gestionExamens.dao.IGroupRepository;
import com.ensah.gestionExamens.service.IDepartementService;
import com.ensah.gestionExamens.service.IEnseignantService;
import com.ensah.gestionExamens.service.IFiliereService;
import com.ensah.gestionExamens.service.IGroupService;

import jakarta.validation.Valid;

import com.ensah.gestionExamens.model.*;


@Controller
public class GroupController {

	
	@Autowired
	private  IEnseignantService enseignantService;
	@Autowired
	private  IDepartementService departementService;
	@Autowired
	private  IFiliereService FiliereService;
	@Autowired
	private IGroupService groupService;
@ModelAttribute
	public void ensfil(Model model)
	{
		if(departementService.findAllDepartement()!=null&&FiliereService.findAllFiliere()!=null) {
		model.addAttribute("listeDepartemet", departementService.findAllDepartement());
        model.addAttribute("listeFiliere",FiliereService.findAllFiliere());
		}
	}
	
	
	
	@GetMapping("/groupe/{Cretaire}")
	public String groupView(@PathVariable("Cretaire") String cretaire,Model model)
	{
		
		 ArrayList<Groupe>groups =groupService.findGroupeByCretaire(decoder(cretaire));
		 List<GroupModel> groupModel = new ArrayList<>();
		 if(groups!=null)
		 {
	         
		 BeanUtils.copyProperties(groupModel, groups);

		 for (Groupe group : groups) {
	         System.out.print("t");

			 GroupModel goupM = new GroupModel();
	         System.out.print("hhhh");

		     BeanUtils.copyProperties(group,goupM);

		     groupModel.add(goupM);

		 }		
		 model.addAttribute("groups",groupModel);
		 model.addAttribute("cretaire",encodeUrl(cretaire));
		 
       }
		 else {
			 model.addAttribute("cretaire",encodeUrl(cretaire));

		 	model.addAttribute("groups",groupModel);
	         System.out.print("tfo");

		 }
		 
		return "Gestion/gestionGroup";
	}
	
	
	
	public List<PersonneModel> afficheEnseignant(String decodedCretaire) {
        List<PersonneModel> enseignantModel = new ArrayList<>();
        
        Departement departement = departementService.findDepartementByname(decodedCretaire);
        if (departement != null) {
            Set<Enseignant> enseignants = departement.getEnseignant();
            for (Enseignant enseignant : enseignants) {
                PersonneModel ensMo = new PersonneModel();
                ensMo.setTypePerson("prof");
                BeanUtils.copyProperties(enseignant, ensMo);
                enseignantModel.add(ensMo);
            }
        } else {
            Filiere filiere = FiliereService.findFiliereByName(decodedCretaire);
            if (filiere != null) {
                Set<Enseignant> enseignants = filiere.getEnseignant();
                for (Enseignant enseignant : enseignants) {
                    PersonneModel ensMo = new PersonneModel();
                    ensMo.setTypePerson("prof");
                    BeanUtils.copyProperties(enseignant, ensMo);
                    enseignantModel.add(ensMo);
                }
            } else {
                List<Enseignant> enseignants = enseignantService.findAllEnseignant();
                if (enseignants != null) {
                    for (Enseignant enseignant : enseignants) {
                        PersonneModel ensMo = new PersonneModel();
                        ensMo.setTypePerson("prof");
                        BeanUtils.copyProperties(enseignant, ensMo);
                        enseignantModel.add(ensMo);
                    }
                }
            }
        }
        
        return enseignantModel;
    }

	
	
	@GetMapping("/voirPlus/{Cretaire}/{groupId}")
	public String voirPlus(@PathVariable("groupId") int id,@PathVariable("Cretaire") String cretaire,Model model)
	{   			             

		Groupe group =groupService.findAllGroupeByID(id);
		GroupModel groupModel = new GroupModel();
		if(group!=null)
		{
			Set<Enseignant> enseignants =group.getEnseignants_group();
			
		   model.addAttribute("group", group);
		   model.addAttribute("cretaire",encodeUrl(cretaire));
           model.addAttribute("listeEnseignant", enseignants);
			return "Gestion/InfoGroupPage";
			
		}
          
		else {
			 model.addAttribute("cretaire",encodeUrl(cretaire));
			model.addAttribute("message", "Il y a une erreur technique");
			return "Gestion/erreurPage";

		}
		
		
		
	}
	
	
	@GetMapping("/SupprimerEnsGroup/{Cretaire}/{enseignantId}/{groupId}")
	public String SupprimerEnsGroup(@PathVariable("Cretaire") String cretaire,@PathVariable("enseignantId") int enseignantId,@PathVariable("groupId") int groupId,Model model) {
        cretaire=encodeUrl(cretaire);

		groupService.deleteEnseignant(Long.valueOf(enseignantId), Long.valueOf(groupId));
		
	    return "redirect:/voirPlus/"+cretaire +"/"+ groupId;
	}
	
	
	@GetMapping("/ajouterGroup/{Cretaire}")
	public String ajouterGroup(@PathVariable("Cretaire") String cretaire, Model model) {
	    System.out.print(cretaire);
	    
	    Groupe groupe = new Groupe();
	    String decodedCretaire = decoder(cretaire);
	    groupe.setCretaire(decodedCretaire);
	    model.addAttribute("GroupModel", groupe);
	    model.addAttribute("cretaire", encodeUrl(cretaire));
	    
	    ArrayList<PersonneModel> enseignantModel = new ArrayList<>();
	    System.out.print(decodedCretaire);
	    
	 
	    model.addAttribute("listeEnseignant",afficheEnseignant(decodedCretaire) );
	    return "Gestion/AjouterGroupPage";
	}

	
	
	@GetMapping("/manageGroup/{Cretaire}")
	public String manageGroup(@PathVariable("Cretaire") String cretaire,Model model) {

        

		ArrayList<Groupe>groups =groupService.findGroupeByCretaire(decoder(cretaire));
		 List<GroupModel> groupModel = new ArrayList<>();
		 if(groups!=null)
		 {
	         
		 BeanUtils.copyProperties(groupModel, groups);

		 for (Groupe group : groups) {
	         System.out.print("t");

			 GroupModel goupM = new GroupModel();
	         System.out.print("hhhh");

		     BeanUtils.copyProperties(group,goupM);

		     groupModel.add(goupM);

		 }		model.addAttribute("groups",groupModel);
		        model.addAttribute("cretaire",encodeUrl(cretaire));
		 
       }
		 else {
		 	model.addAttribute("groups",groupModel);
			 model.addAttribute("cretaire",encodeUrl(cretaire));
	         System.out.print("tfo");

		 }
		 
		

	    return "Gestion/manageGroupPage";

	}

	

	@GetMapping("/supprimerGroup/{Cretaire}/{groupId}")
	public String supprimerGroup(@PathVariable("groupId")int id,@PathVariable("Cretaire")String cretaire,Model model)
	{      cretaire=encodeUrl(cretaire);

		groupService.deleteGroupe(Long.valueOf(id));
		
		return"redirect:/manageGroup/"+cretaire;
	}

	@GetMapping("/modifierGroup/{Cretaire}/{groupId}")
	public String modifierGroup(@PathVariable("groupId")int id,@PathVariable("Cretaire")String cretaire,Model model)
	{      cretaire=encodeUrl(cretaire);
	  Groupe group=groupService.findAllGroupeByID(id);
	  GroupModel groupModel=new GroupModel();
	  if(group!=null)
	  {			Set<Enseignant> enseignants =group.getEnseignants_group();

	        BeanUtils.copyProperties(group,groupModel);
	        model.addAttribute("GroupModel", groupModel);
	        model.addAttribute("cretaire", cretaire);
		    model.addAttribute("listeEnseignant",afficheEnseignant(decoder(cretaire)) );

		     return "Gestion/UpdatGroup";
	  }
        model.addAttribute("groupModel", groupModel);
	     return "Gestion/UpdatGroup";

	}
	
	@PostMapping("/UpdateGroup")
	public String UpdateGroup(@Valid @ModelAttribute("GroupModel") GroupModel group,BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, @RequestParam(value="selectedEnseignants", required=false) List<Integer> Ids )
	{

	    if(bindingResult.hasErrors()) {
	        redirectAttributes.addFlashAttribute("messageErreur", "Il faut saisir tous les champs....");
	        return "redirect:/modifierGroup/" + encodeUrl(group.getCretaire()) + "/" + group.getId();
	    }

	    else {		
	    	Groupe groupEntity = groupService.findAllGroupeByID(group.getId());

	    if (Ids != null) {
	        List<Long> selectedEnseignantsIds = Ids.stream().map(Long::valueOf).collect(Collectors.toList());
	        Set<Enseignant> selectedEnseignants = enseignantService.findEnseignantsByIds(selectedEnseignantsIds);
	        
		        group.setEnseignants_group(selectedEnseignants);

	        } 
	     
	        
	    

	    BeanUtils.copyProperties(group,groupEntity);

	    groupService.addGroupe(groupEntity);

	    redirectAttributes.addFlashAttribute("message", "Bien enregistré");
	    return "redirect:/manageGroup/" + encodeUrl(group.getCretaire());
	}
	}
	
	

	@PostMapping("/addGroup")
	public String addGroup(@Valid @ModelAttribute("GroupModel") GroupModel groupModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, @RequestParam(value="selectedEnseignants", required=false) List<Integer> Ids) {
	    if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("messageErreur", "Il faut saisir tous les champs....");

	        return "redirect:/ajouterGroup/" + encodeUrl(groupModel.getCretaire());
	    } else {
	        Groupe groupe;
	        if (groupModel.getId() == 0) {
	            groupe = new Groupe(); 
	        } else {
	            groupe = groupService.findAllGroupeByID(groupModel.getId());
	            if (groupe == null) {
	                groupe = new Groupe(); 
	            }
	        }
	        
	        BeanUtils.copyProperties(groupModel, groupe);
	        if (Ids != null) {
	            List<Long> selectedEnseignantsIds = Ids.stream().map(Long::valueOf).collect(Collectors.toList());
	            Set<Enseignant> selectedEnseignants = enseignantService.findEnseignantsByIds(selectedEnseignantsIds);
	            groupe.setEnseignants_group(selectedEnseignants);
	        }
	        groupService.addGroupe(groupe);
            redirectAttributes.addFlashAttribute("message", "Bien enregistré");

	        return "redirect:/ajouterGroup/" + encodeUrl(groupModel.getCretaire());
	    }
	}

	
	
	private String encodeUrl(String cretaire) {
        try {
            return URLEncoder.encode(cretaire, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return cretaire;

        }

    }

private String decoder(String cretaire)
{
	   String decodedCretaire = "";
       try {
    	   return URLDecoder.decode(cretaire, StandardCharsets.UTF_8.toString()).replace("+"," ");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
           return decodedCretaire;

       }

}






}
