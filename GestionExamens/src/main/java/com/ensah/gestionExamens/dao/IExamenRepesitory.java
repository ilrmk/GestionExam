package com.ensah.gestionExamens.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensah.gestionExamens.bo.Examen;

public interface IExamenRepesitory extends JpaRepository<Examen,Long>{

	@Query("SELECT e FROM Examen e WHERE e.date < :currentDate ORDER BY e.date")
	List<Examen> getAllAnciensExam(@Param("currentDate") LocalDate currentDate);

	
	@Query("SELECT e FROM Examen e WHERE e.date >= :currentDate ORDER BY e.date")
	List<Examen> getAllNouveauxExam(@Param("currentDate") LocalDate currentDate);
	
	 @Query("SELECT e FROM Examen e WHERE "
	            + "( :niveau IS NULL OR e.module.niveau.id = :niveau ) AND "
	            + "( :semestre='' OR e.semestre = :semestre ) AND "
	            + "( :annee='' OR e.anneeUniversitaire = :annee ) AND "
	            + "e.date <= CURRENT_DATE")
	    List<Examen> findExamensByCriteria(@Param("niveau") Long niveau, 
	                                       @Param("semestre") String semestre, 
	                                       @Param("annee") String annee);

	
	 @Query("SELECT e FROM Examen e WHERE e.date < :currentDate AND e.confirm=false  ORDER BY e.date")
		List<Examen> getExamnNonConfirm(@Param("currentDate") LocalDate currentDate);
	 
}
