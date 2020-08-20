package com.cs.cijferSysteem.controller;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cs.cijferSysteem.domein.Docentvak;

public interface DocentVakRepository extends CrudRepository<Docentvak, Long> {
	Docentvak findByDocentIdAndVakId(Long docentid, Long vakid);
	Iterable<Docentvak> findByDocentId(Long docentid);
	Iterable<Docentvak> findByVakId(Long vakid);
	Optional<Docentvak> findById(Long docentvakid);
}
