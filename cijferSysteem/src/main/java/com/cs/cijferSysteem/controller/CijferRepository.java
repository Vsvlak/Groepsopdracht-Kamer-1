package com.cs.cijferSysteem.controller;

import org.springframework.data.repository.CrudRepository;

import com.cs.cijferSysteem.domein.Cijfer;

public interface CijferRepository extends CrudRepository <Cijfer, Long> {

}
