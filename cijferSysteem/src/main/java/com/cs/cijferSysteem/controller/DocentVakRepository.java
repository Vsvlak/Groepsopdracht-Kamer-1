package com.cs.cijferSysteem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.cijferSysteem.domein.Docentvak;

public interface DocentVakRepository extends JpaRepository<Docentvak, Long> {
	Docentvak findByDocentIdAndVakId(Long docentid, Long vakid);
	List<Docentvak> findByDocentId(Long docentid);
	List<Docentvak> findByVakId(Long vakid);
	Optional<Docentvak> findById(Long docentvakid);
}
