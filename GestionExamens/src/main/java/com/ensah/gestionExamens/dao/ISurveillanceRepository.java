package com.ensah.gestionExamens.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.gestionExamens.bo.Surveillance;

public interface ISurveillanceRepository extends JpaRepository<Surveillance,Long> {
	
	public Iterable<Surveillance> findByExamenDate(LocalDate date);

}
