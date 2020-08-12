package com.cs.cijferSysteem.controller;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cs.cijferSysteem.domein.Docent;

public interface DocentRepository extends CrudRepository<Docent, Long>{

	//List<Docent> findByAchternaam(String achternaam);
	
}

