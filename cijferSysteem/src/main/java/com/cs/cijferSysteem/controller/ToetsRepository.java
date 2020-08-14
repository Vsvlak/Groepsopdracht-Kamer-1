package com.cs.cijferSysteem.controller;

import com.cs.cijferSysteem.domein.Toets;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ToetsRepository extends CrudRepository<Toets, Long> {

	List<Toets> findByDocentIdAndVakId(long docentid, long vakid);
	List<Toets> findByCijfersId(long toetsid);
}
