package com.cs.cijferSysteem.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cs.cijferSysteem.domein.Docent;
import com.cs.cijferSysteem.domein.Toets;

public interface DocentRepository extends JpaRepository<Docent, Long>{

}

