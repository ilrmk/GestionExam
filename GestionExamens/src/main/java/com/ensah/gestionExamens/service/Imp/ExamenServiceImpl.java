package com.ensah.gestionExamens.service.Imp;


import java.time.LocalDate;
import java.time.Year;
import java.util.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ensah.gestionExamens.bo.Element_pedagogique;
import com.ensah.gestionExamens.bo.Enseignant;
import com.ensah.gestionExamens.bo.Examen;
import com.ensah.gestionExamens.bo.Salle;
import com.ensah.gestionExamens.bo.Surveillance;
import com.ensah.gestionExamens.dao.IExamenRepesitory;
import com.ensah.gestionExamens.service.IExamenService;



@Service

public class ExamenServiceImpl implements IExamenService  {

	
	@Autowired
	IExamenRepesitory examRepos;
	
	@Override
	public  Examen getExamenById(Long id) {
		// TODO Auto-generated method stub
		return examRepos.getById(id);
	}

	@Override
	public void ajouterExamen(Examen s) {
		
		if(s.getDureePrevue()=="") {
			if(s.getModule().getType().equals("Element")) {
				s.setDureePrevue("1h30");
			}else {
				s.setDureePrevue("2h");
			}
		}
		s.setConfirm(false);
		examRepos.save(s);
		
		
		
	}

	@Override
	public List<Examen> getAllExamen() {
		
		return examRepos.findAll();
	}

	
	public void supprimerExam(Long id) {
		examRepos.delete(examRepos.getById(id));
	}

	@Override
	public List<Long> getAllSalleExam(Examen e) {
		List<Long> salles=new ArrayList<>();
		
		List<Surveillance> survs=examRepos.getById(e.getId()).getSurvs();
		
		for(Surveillance s:survs) {
	        long salleId = s.getSalle().getId();
	        salles.add(salleId);
		}
		return salles;
	}

	@Override
	public Map<String, String> getAllAnneeUni() {
	    Map<String, String> anneeUniMap = new LinkedHashMap<>();
	    int currentYear = Year.now().getValue();
	    for (int year = 2020; year <= currentYear; year++) {
	        String anneeUniversitaire = year + " / " + (year + 1);
	        anneeUniMap.put(String.valueOf(year), anneeUniversitaire);
	    }
	    return anneeUniMap;
	}

	@Override
	public List<Examen> getExamensByAnnee(String annee) {
		List<Examen> exms=new ArrayList<>();
		return exms;
	}

	@Override
	public List<Examen> getExamByIdAncein(Long id) {
	    List<Examen> exams = new ArrayList<>();
	    Examen e = examRepos.getById(id);
	    
	    if (e != null && e.getDate().isBefore(LocalDate.now())) {
	        exams.add(e);
	    }
	    return exams;
	}

	@Override
	public List<Examen> getAllAnceinExam() {
		 LocalDate currentDate = LocalDate.now();
	        return examRepos.getAllAnciensExam(currentDate);
	}

	@Override
	public List<Examen> getAllNouvExam() {

        LocalDate currentDate = LocalDate.now();
        return examRepos.getAllNouveauxExam(currentDate);

	}
	
	 public List<Examen> getExamensByCriteria(Long niveau, String semestre, String annee) {
	        return examRepos.findExamensByCriteria(niveau, semestre, annee);
	    }

	@Override
	public void confirmerExamen(Long id, String dureeReel, String rapport, MultipartFile pvFile,
			MultipartFile enonceFile,Examen examen) {
		
		if (rapport == null || rapport.isEmpty()) {
            examen.setRapportTextuel("Rien à signaler");
        } else {
            examen.setRapportTextuel(rapport);
        }
		
		examen.setDureeReelle(dureeReel);
		// Définir le chemin de stockage des fichiers
        String storageDir = "src/main/resources/static/examenFile/";
        try {
        	
            // Sauvegarder le fichier PV
            if (pvFile != null && !pvFile.isEmpty()) {
                String pvFilename = generateRandomFilename(pvFile.getOriginalFilename());
                Path pvPath = Paths.get(storageDir, pvFilename);
                Files.write(pvPath, pvFile.getBytes());
                examen.setPv(pvFilename); 
            }

            
            // Sauvegarder le fichier énoncé
            if (enonceFile != null && !enonceFile.isEmpty()) {
                String enonceFilename = generateRandomFilename(enonceFile.getOriginalFilename());
                Path enoncePath = Paths.get(storageDir, enonceFilename);
                Files.write(enoncePath, enonceFile.getBytes());
                examen.setEpreuve( enonceFilename); 
            }

            
        } catch (IOException e) {
            e.printStackTrace();
            
        }

       examen.setConfirm(true);
        examRepos.save(examen);
    }

	
	
    // Méthode pour générer un nom de fichier aléatoire
    private String generateRandomFilename(String originalFilename) {
        String extension = "";
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = originalFilename.substring(dotIndex);
        }
        return UUID.randomUUID().toString() + extension;
    }

    
    


	@Override
	public List<Examen> getNonConfirmExam() {
		LocalDate currentDate = LocalDate.now();
		return examRepos.getExamnNonConfirm(currentDate);
	}
	
	
	

		


	
}
