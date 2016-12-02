package br.com.constantino.androidviagem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.constantino.androidviagem.dao.ViagemDAO;
import br.com.constantino.androidviagem.entities.Viagem;

@RestController
public class ViagemController {

	@Autowired
	private ViagemDAO viagemDAO;
	
	@RequestMapping(value = "/viagem/all", method=RequestMethod.POST)
	public Iterable<Viagem> all() {				
		Iterable<Viagem> viagem = viagemDAO.findAll();		
		return viagem;
	}
}
