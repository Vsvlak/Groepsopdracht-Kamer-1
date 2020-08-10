package com.cs.cijferSysteem.controller;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cs.cijferSysteem.domein.Leerling;

public interface LeerlingRepository extends CrudRepository <Leerling, Long>{

	List <Leerling> findByLeerlingNummerBetween(int start, int end);
	
	
	
}
