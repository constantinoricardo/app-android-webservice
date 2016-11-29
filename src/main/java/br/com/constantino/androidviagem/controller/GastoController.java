package br.com.constantino.androidviagem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.constantino.androidviagem.dao.CategoriaDAO;
import br.com.constantino.androidviagem.dao.GastoDAO;
import br.com.constantino.androidviagem.entities.Categoria;
import br.com.constantino.androidviagem.entities.Gasto;
import br.com.constantino.androidviagem.validator.GastoValidator;

@Controller
public class GastoController {

	@Autowired
	private CategoriaDAO categoriaDAO;

	@Autowired
	private GastoDAO gastoDAO;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new GastoValidator());
	}
	
	public Calendar converterDate(String date) {
		try {
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
			Calendar c = Calendar.getInstance();
			c.setTime(formater.parse(date));

			return c;
		} catch (ParseException ex) {
			ex.getMessage();
		}
		return null;
	}
	
	@RequestMapping(value = "/gasto/incluir", method=RequestMethod.POST)
	@ResponseBody
	public String incluir(
							@RequestParam(value="local") String local,
							@Valid Gasto gasto, 
							BindingResult bindingResult
						) {
		
		
		if (bindingResult.hasErrors()) {
			return "Houve um erro ";			
		}
		
		
		return gasto.getLocal() + " Esse é o local";
	}

	@RequestMapping(value = "/gasto/inserir", method=RequestMethod.POST)
	@ResponseBody
	public String inserir(
			@Valid Gasto gasto,
			BindingResult result,
			@RequestParam(value = "categoria_id") Integer categoria_id,
			@RequestParam(value = "data") String data
			) {
		
		try {
			
			Categoria categoria = categoriaDAO.findCategoryById(categoria_id);
			gasto.setCategoria(categoria);
			
			Calendar calendar = this.converterDate(data);
			gasto.setData(calendar);
			
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
