package br.com.constantino.androidviagem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.constantino.androidviagem.dao.GastoDAO;
import br.com.constantino.androidviagem.entities.Gasto;
import br.com.constantino.androidviagem.validator.GastoValidator;

@RestController
public class GastoController {

	@Autowired
	private GastoDAO gastoDAO;	
	
	@InitBinder
	protected void initGastoBinder(WebDataBinder binder) {								
		binder.setValidator(new GastoValidator());		
	}			

	@RequestMapping(value = "/gasto/inserir", method=RequestMethod.POST)
	public String inserir(@Valid Gasto gasto, BindingResult result) {					
		try {
			if (result.hasErrors()) {
				FieldError field = result.getFieldError();
				return field.getDefaultMessage();
			}
			
			gastoDAO.save(gasto);

		} catch (Exception ex) {
			return "Erro ao gravar Gasto " + ex.getMessage();
		}
		
		return "Gasto inserido com sucesso.";
	}
}
