package br.com.constantino.androidviagem.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.constantino.androidviagem.dao.ViagemDAO;
import br.com.constantino.androidviagem.entities.Viagem;
import br.com.constantino.androidviagem.validator.ViagemValidator;

@RestController
public class ViagemController {

	@Autowired
	private ViagemDAO viagemDAO;
	
	@InitBinder
	protected void initViagemBinder(WebDataBinder binder) {
		binder.setValidator(new ViagemValidator());
	}
	
	@RequestMapping(value = "/viagem/all")
	public Iterable<Viagem> all() {				
		Iterable<Viagem> viagem = viagemDAO.findAll();		
		return viagem;
	}
	
	@RequestMapping(value="/viagem/deletar", method=RequestMethod.POST)
	public String deletar(@RequestParam(value="id") Long id) {		
		viagemDAO.delete(id);
		return "Viagem removida com sucesso.";
	}
	
	@RequestMapping(value="/viagem/selectOne", method=RequestMethod.POST)
	public Viagem selectOne(@RequestParam(value="id") Long id) {
		Viagem viagem = viagemDAO.findOne(id);
		return viagem;
	}
	
	@RequestMapping(value="/viagem/alterar", method=RequestMethod.POST)
	public String alterar(@Valid Viagem viagem, BindingResult result) {
		
		try {
			
			if (result.hasErrors()) {
				FieldError field = result.getFieldError();
				return field.getDefaultMessage();
			}
			
			Long id = viagem.getId();
			Viagem v = viagemDAO.findOne(id);
			
			v.setDestino(viagem.getDestino());
			v.setTipoViagem(viagem.getTipoViagem());
			v.setData_chegada(viagem.getData_chegada());
			v.setData_saida(viagem.getData_saida());
			v.setOrcamento(viagem.getOrcamento());
			v.setQtde_pessoas(viagem.getQtde_pessoas());
			
			viagemDAO.save(v);
			
		} catch (Exception ex) {
			return "Erro ao alterar a viagem " + ex.getMessage();
		}
		
		return "Viagem alterada com sucesso";
	}
	
	@RequestMapping(value="/viagem/inserir", method=RequestMethod.POST)
	public String inserir(@Valid Viagem viagem, BindingResult result) {
		
		try {
			
			if (result.hasErrors()) {
				FieldError field = result.getFieldError();
				return field.getDefaultMessage();
			}
			
			viagemDAO.save(viagem);
			
		} catch (Exception ex) {
			return "Erro ao gravar viagem " + ex.getMessage();
		}
		
		return "Viagem inserida com sucesso.";
	}
}
