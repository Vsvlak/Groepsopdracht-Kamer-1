package com.cs.cijferSysteem.controller;

import org.springframework.data.repository.CrudRepository;

import com.cs.cijferSysteem.domein.Cijfer;

public interface ToetsCijferRepository extends CrudRepository <Cijfer, Long> {

}
