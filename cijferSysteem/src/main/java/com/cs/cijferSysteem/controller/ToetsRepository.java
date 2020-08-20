package com.cs.cijferSysteem.controller;

import com.cs.cijferSysteem.domein.Toets;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ToetsRepository extends CrudRepository<Toets, Long> {

	List<Toets> findByCijfersId(Long toetsid);
	List<Toets> findByDocentvakId(Long docentvakid);
	List<Toets> findByKlasId(Long klasid);
	List<Toets> findByDocentvakIdAndKlasId(Long docentvakid, Long klasid);
}
