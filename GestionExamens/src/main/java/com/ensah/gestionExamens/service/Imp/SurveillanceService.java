package com.ensah.gestionExamens.service.Imp;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.gestionExamens.dao.ISurveillanceRepository;
import com.ensah.gestionExamens.service.IAdminService;
import com.ensah.gestionExamens.service.IEnseignantService;
import com.ensah.gestionExamens.service.IExamenService;
import com.ensah.gestionExamens.service.IGroupService;
import com.ensah.gestionExamens.service.ISalleService;
import com.ensah.gestionExamens.service.ISurveillanceService;


import com.ensah.gestionExamens.bo.*;

@Service
@Transactional
public class SurveillanceService implements ISurveillanceService  {

	
	@Autowired
	ISurveillanceRepository surRepos;
	
	@Autowired
	IEnseignantService ensSrv;
	
	@Autowired
	ISalleService salleSrv;
	
	@Autowired
	IExamenService examSrv;
	
	@Autowired
	IGroupService grpSrv;
	
	@Autowired
	IAdminService admSrvs;
	
	/** Utilisé pour la journalisation */
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	
	@Override
	public Surveillance getSurveillanceById(Long id) {
	        
		return surRepos.getById(id);
	}

	@Override
	public void ajouterSurveillance(Surveillance s) {
		surRepos.save(s);
		
	}

	

	@Override
	public List<Surveillance> getAllSurveillance() {
		
		return surRepos.findAll();
	}
	
	

	@Override
	public List<Surveillance> creerSurveillances(Long idCood, List<Long> salles, List<Integer> nbSurv, Element_pedagogique module, int manierSelection, Examen exam) {
	    Enseignant coordonnateur;
	    if (idCood == null) {
	        coordonnateur = module.getCoordonnateur();
	    } else {
	        coordonnateur = ensSrv.findAllEnseignantByID(idCood);
	    }

	    List<Surveillance> survs = new ArrayList<>();
	    Set<Enseignant> enseignantsDejaAssignes = new HashSet<>();
	    Set<Admin> adminsDejaAssignes = new HashSet<>();

	    if (manierSelection == 1) {
	        for (Long salleId : salles) {
	            Set<Enseignant> enseignants_surveillance = tirerEnseignant(nbSurv.get(salleId.intValue() - 1), exam, salleSrv.getSalleById(salleId), enseignantsDejaAssignes);
	            Admin admin = tirerAdmin(exam, salleSrv.getSalleById(salleId), adminsDejaAssignes);
	            Surveillance surv = new Surveillance(enseignants_surveillance, salleSrv.getSalleById(salleId), coordonnateur, admin, exam);
	            survs.add(surv);
	        }
	    } else {
	        List<Set<Enseignant>> ens_surveillance = tirerEnseigantParGroup(nbSurv, salles, exam, enseignantsDejaAssignes);
	        int c = 0;
	        for (Long salleId : salles) {
	            Admin admin = tirerAdmin(exam, salleSrv.getSalleById(salleId), adminsDejaAssignes);
	            Surveillance surv = new Surveillance(ens_surveillance.get(c), salleSrv.getSalleById(salleId), coordonnateur, admin, exam);
	            survs.add(surv);
	            c++;
	        }
	    }

	    return survs;
	}

	private List<Set<Enseignant>> tirerEnseigantParGroup(List<Integer> nbSurv, List<Long> salles, Examen exam, Set<Enseignant> enseignantsDejaAssignes) {
	    List<Integer> nb_surv = new ArrayList<>();
	    int total_surv = 0;
	    for (Long salleId : salles) {
	        int a = salleId.intValue();
	        total_surv += nbSurv.get(a - 1);
	        nb_surv.add(nbSurv.get(a - 1));
	    }

	    Set<Enseignant> enseignantsDisponibles = new HashSet<>();
	    Random random = new Random();
	    List<Groupe> groups = grpSrv.findAllGroupe();

	    while (!groups.isEmpty()) {
	        int indexAleatoire = random.nextInt(groups.size());
	        Groupe groupe = groups.get(indexAleatoire);
	        Set<Enseignant> enseignantsGroupe = groupe.getEnseignants_group();

	        enseignantsGroupe.removeAll(enseignantsDejaAssignes);

	        if (enseignantsGroupe.size() >= total_surv) {
	            for (Enseignant e : enseignantsGroupe) {
	                if (estDisponible(e, exam)) {
	                    enseignantsDisponibles.add(e);
	                }
	            }

	            if (enseignantsDisponibles.size() >= total_surv) {
	                break;
	            }
	        }

	        groups.remove(indexAleatoire);
	    }

	    Iterator<Enseignant> enseignantIterator = enseignantsDisponibles.iterator();
	    List<Set<Enseignant>> liste_ens = new ArrayList<>();
	    if (enseignantsDisponibles.size() >= total_surv) {
	        for (int nb : nb_surv) {
	            Set<Enseignant> set = new HashSet<>();
	            for (int i = 0; i < nb && enseignantIterator.hasNext(); i++) {
	                Enseignant enseignant = enseignantIterator.next();
	                set.add(enseignant);
	                enseignantsDejaAssignes.add(enseignant); // Ajout à la liste des déjà assignés
	            }
	            liste_ens.add(set);
	        }
	    }
	    else {
	    	throw new IllegalArgumentException("Nombre d'enseignants disponibles insuffisant pour couvrir le nombre de surveillances requis.");
	    }

	    return liste_ens;
	}

	private Set<Enseignant> tirerEnseignant(int nombreEnseignants, Examen exam, Salle salle, Set<Enseignant> enseignantsDejaAssignes) {
	    Set<Enseignant> enseignantsSelectionnes = new HashSet<>();
	    int compteur = 0;
	    Random random = new Random();
	    Iterable<Surveillance> surv_bd = surRepos.findByExamenDate(exam.getDate());
	    List<Enseignant> ens_bd = new ArrayList<>();
	    List<Enseignant> ens_bd_meme_salle = new ArrayList<>();

	    if (surv_bd != null) {
	        for (Surveillance s : surv_bd) {
	            if (s.getSalle().equals(salle)) {
	                ens_bd_meme_salle.addAll(s.getEnseignants_surveillance());
	            } else {
	                ens_bd.addAll(s.getEnseignants_surveillance());
	            }
	        }
	    }

	    ens_bd_meme_salle.removeAll(enseignantsDejaAssignes);
	    ens_bd.removeAll(enseignantsDejaAssignes);

	    while (enseignantsSelectionnes.size() < nombreEnseignants && compteur < ens_bd_meme_salle.size()) {
	        int indexAleatoire = random.nextInt(ens_bd_meme_salle.size());
	        Enseignant enseignant = ens_bd_meme_salle.get(indexAleatoire);

	        if (estDisponible(enseignant, exam)) {
	            enseignantsSelectionnes.add(enseignant);
	            enseignantsDejaAssignes.add(enseignant); // Ajout à la liste des déjà assignés
	        }

	        compteur++;
	    }

	    compteur = 0;

	    while (enseignantsSelectionnes.size() < nombreEnseignants && compteur < ens_bd.size()) {
	        int indexAleatoire = random.nextInt(ens_bd.size());
	        Enseignant enseignant = ens_bd.get(indexAleatoire);

	        if (estDisponible(enseignant, exam)) {
	            enseignantsSelectionnes.add(enseignant);
	            enseignantsDejaAssignes.add(enseignant); // Ajout à la liste des déjà assignés
	        }

	        compteur++;
	    }

	    compteur = 0;
	    List<Enseignant> allEnseignants = ensSrv.findAllEnseignant();
	    allEnseignants.removeAll(enseignantsDejaAssignes);

	    while (enseignantsSelectionnes.size() < nombreEnseignants && compteur < allEnseignants.size()) {
	        int indexAleatoire = random.nextInt(allEnseignants.size());
	        Enseignant enseignant = allEnseignants.get(indexAleatoire);

	        if (estDisponible(enseignant, exam)) {
	            enseignantsSelectionnes.add(enseignant);
	            enseignantsDejaAssignes.add(enseignant); // Ajout à la liste des déjà assignés
	        }

	        compteur++;
	    }

	    return enseignantsSelectionnes;
	}

	private Admin tirerAdmin(Examen exam, Salle salle, Set<Admin> adminsDejaAssignes) {
	    Random random = new Random();
	    Iterable<Surveillance> surv_bd = surRepos.findByExamenDate(exam.getDate());

	    List<Admin> adm_salle = new ArrayList<>();
	    List<Admin> adm_date = new ArrayList<>();
	    Admin adm = null;

	    if (surv_bd != null) {
	        for (Surveillance s : surv_bd) {
	            if (s.getSalle().equals(salle)) {
	                adm_salle.add(s.getAdmin());
	            } else {
	                adm_date.add(s.getAdmin());
	            }
	        }
	    }

	    adm_salle.removeAll(adminsDejaAssignes);
	    adm_date.removeAll(adminsDejaAssignes);

	    if (!adm_salle.isEmpty()) {
	        int indexAleatoire = random.nextInt(adm_salle.size());
	        Admin potentielAdm = adm_salle.get(indexAleatoire);
	        if (estDisponibleAdmin(potentielAdm, exam)) {
	            adm = potentielAdm;
	            adminsDejaAssignes.add(adm); // Ajout à la liste des déjà assignés
	        }
	    }

	    if (adm == null && !adm_date.isEmpty()) {
	        int indexAleatoire = random.nextInt(adm_date.size());
	        Admin potentielAdm = adm_date.get(indexAleatoire);
	        if (estDisponibleAdmin(potentielAdm, exam)) {
	            adm = potentielAdm;
	            adminsDejaAssignes.add(adm); // Ajout à la liste des déjà assignés
	        }
	    }

	    if (adm == null) {
	        List<Admin> allAdmins = admSrvs.findAllAdmin();
	        allAdmins.removeAll(adminsDejaAssignes);
	       
	                int size = allAdmins.size();
	        if (size > 0) {
	            int indexAleatoire = random.nextInt(size);
	            Admin potentielAdm = allAdmins.get(indexAleatoire);
	            if (estDisponibleAdmin(potentielAdm, exam)) {
	                adm = potentielAdm;
	                adminsDejaAssignes.add(adm); // Ajout à la liste des déjà assignés
	            }
	        }
	    }

	    return adm;
	}

	
	
	private boolean estDisponibleAdmin(Admin admin, Examen exam) {
	    LocalDate dateExam = exam.getDate();
	    LocalTime heureDebutExam = exam.getHeureDebut();
        Duration dureePrevueExam = parseDuration(exam.getDureePrevue());
        LocalTime heureFinExam = heureDebutExam.plus(dureePrevueExam);
	    
	    // Vérifier si l'admin est déjà assigné à un examen à la même date et heure
	    for (Surveillance surveillance : admin.getSurvs()) {
	        Examen examenSurveillance = surveillance.getExamen();
	        
	        // Vérification si examenSurveillance est null
	        if (examenSurveillance != null) {
	        	 LocalDate dateSurveillance = examenSurveillance.getDate();
	                LocalTime heureDebutSurveillance = examenSurveillance.getHeureDebut();
	                Duration dureeSurveillance = parseDuration(examenSurveillance.getDureePrevue());
	                LocalTime heureFinSurveillance = heureDebutSurveillance.plus(dureeSurveillance);

	                // Vérifie si l'enseignant est déjà assigné à un examen à la même date et heure
	                if (dateSurveillance.equals(dateExam) &&
	                    (heureDebutExam.isBefore(heureFinSurveillance) && heureFinExam.isAfter(heureDebutSurveillance))) {
	                    return false; // L'enseignant est déjà assigné à un examen à ce moment-là
	                }
	        }
	    }
	    
	    // Vérifier si l'admin dépasse le nombre maximal de sessions par jour
	    int nbSessions = 0;
	    for (Surveillance surveillance : admin.getSurvs()) {
	        Examen examenSurveillance = surveillance.getExamen();
	        
	        // Vérification si examenSurveillance est null
	        if (examenSurveillance != null && examenSurveillance.getDate().equals(dateExam)) {
	            nbSessions++;
	        }
	    }
	    if (nbSessions > 2) {
	        return false;
	    }
	    
	    return true;
	}


	
	    private boolean estDisponible(Enseignant enseignant, Examen exam) {
	        LocalDate dateExam = exam.getDate();
	        LocalTime heureDebutExam = exam.getHeureDebut();

	        Duration dureePrevueExam = parseDuration(exam.getDureePrevue());
	        LocalTime heureFinExam = heureDebutExam.plus(dureePrevueExam);
		    
	        int nb = 0;
	        
	        // Vérifie les surveillances de l'enseignant
	        for (Surveillance surveillance : enseignant.getSurvs()) {
	            Examen examenSurveillance = surveillance.getExamen();
	            
	            if (examenSurveillance != null) {
	            	 LocalDate dateSurveillance = examenSurveillance.getDate();
		                LocalTime heureDebutSurveillance = examenSurveillance.getHeureDebut();
		                Duration dureeSurveillance = parseDuration(examenSurveillance.getDureePrevue());
		                LocalTime heureFinSurveillance = heureDebutSurveillance.plus(dureeSurveillance);

		                // Vérifie si l'enseignant est déjà assigné à un examen à la même date et heure
		                if (dateSurveillance.equals(dateExam) &&
		                    (heureDebutExam.isBefore(heureFinSurveillance) && heureFinExam.isAfter(heureDebutSurveillance))) {
		                    return false; // L'enseignant est déjà assigné à un examen à ce moment-là
		                }
	             
	                // Compte le nombre de surveillances pour la même date
	                if (dateSurveillance.equals(dateExam)) {
	                    nb++;
	                }
	            }
	        }
	        
	        // Vérifie si l'enseignant a déjà deux surveillances pour cette date
	        if (nb >= 2) {
	            return false;
	        }
	        
	        // Vérifie si l'enseignant est coordonnateur pour un examen à la même date
	        for (Examen e : examSrv.getAllNouvExam()) {
	            if (e.getDate().equals(dateExam)) {
	                List<Surveillance> surveillances = e.getSurvs();
	                if (surveillances != null && !surveillances.isEmpty()) {
	                    if (surveillances.get(0).getCoordonnateur().equals(enseignant)) {
	                        return false;
	                    }
	                }
	            }
	        }
	        
	        return true; // L'enseignant est disponible
	    }

	    
	    public static Duration parseDuration(String durationStr) {
	        Pattern pattern = Pattern.compile("(\\d+)h((\\d+)min)?");
	        Matcher matcher = pattern.matcher(durationStr);
	        if (matcher.matches()) {
	            int hours = Integer.parseInt(matcher.group(1));
	            int minutes = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
	            return Duration.ofHours(hours).plusMinutes(minutes);
	        }
	        throw new IllegalArgumentException("Invalid duration format: " + durationStr);
	    }

	    
	    
	@Override
    public boolean supprimer(Long id) {
        Optional<Surveillance> optionalSurveillance = surRepos.findById(id);
        if (optionalSurveillance.isPresent()) {
            Surveillance surveillance = optionalSurveillance.get();
            Examen examen = surveillance.getExamen();

            if (examen.getSurvs().size() <= 1) {
                return false; // Ne pas supprimer si c'est la seule surveillance associée à l'examen
            }

            // Mettre à jour les relations bidirectionnelles
            examen.removeSurveillance(surveillance);
            surRepos.delete(surveillance);
            return true;
        }
        return false;
    }
	
	
	@Override
	public void supprimerAllSurveillanceOfExam(Examen e) {
	    List<Surveillance> survs = e.getSurvs();
	    if (survs != null) {
	        for (Surveillance s : survs) {
	            s.setExamen(null);
	            surRepos.delete(s);
	        }
	        e.getSurvs().clear();
	        surRepos.flush();  // Assurez-vous de vider les changements
	    }
	    System.out.println("dans la fonction de supprission de tout les surviellance");
	}



}
