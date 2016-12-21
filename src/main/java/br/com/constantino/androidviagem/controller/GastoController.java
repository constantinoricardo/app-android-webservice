package br.com.constantino.androidviagem.controller;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.constantino.androidviagem.dao.CategoriaDAO;
import br.com.constantino.androidviagem.dao.GastoDAO;
import br.com.constantino.androidviagem.entities.Categoria;
import br.com.constantino.androidviagem.entities.Gasto;
import br.com.constantino.androidviagem.validator.GastoValidator;

@RestController
public class GastoController {

	@Autowired
	private GastoDAO gastoDAO;	
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@InitBinder
	protected void initGastoBinder(WebDataBinder binder) {								
		binder.setValidator(new GastoValidator());		
	}	
	
	@RequestMapping(value = "/gasto/all")
	public Iterable<Gasto> all() {
		Iterable<Gasto> gasto = gastoDAO.findAll();		
		return gasto;
	}
	
	@RequestMapping(value = "/gasto/selectOne", method=RequestMethod.POST)
	public Gasto selectOne(@RequestParam(value="id") Long id) {
		Gasto gasto = gastoDAO.findOne(id);
		return gasto;
	}
	
	@RequestMapping(value = "/gasto/alterar", method=RequestMethod.POST)
	public String alterar(@Valid Gasto gasto, BindingResult result) {
		try {
			if (result.hasErrors()) {
				FieldError field = result.getFieldError();
				return field.getDefaultMessage();
			}
			
			Long id = gasto.getId();
			Long categoria_id = gasto.getCategoria().getId();

			Categoria categoria = categoriaDAO.findOne(categoria_id);
			Gasto entity = gastoDAO.findOne(id);
			
			entity.setCategoria(categoria);
			entity.setData(gasto.getData());
			entity.setDescricao(gasto.getDescricao());
			entity.setLocal(gasto.getLocal());
			entity.setValor(gasto.getValor());
			
			gastoDAO.save(entity);
			
		} catch (Exception e) {
			return "Erro ao alterar gasto " + e.getMessage();
		}
		
		return "Gasto alterado com sucesso.";
	}
	
	@RequestMapping(value = "/gasto/deletar", method=RequestMethod.POST)
	public String deletar(@RequestParam(value="id") Long id) {
		gastoDAO.delete(id);
		return "Gasto removido com sucesso.";
	}

	@RequestMapping(value = "/gasto/inserir", method=RequestMethod.POST)	
	public String inserir(@Valid Gasto gasto, BindingResult result) {
		JSONObject resposta = new JSONObject();		
		
		try {
			if (result.hasErrors()) {
				FieldError field = result.getFieldError();				
				resposta.append("codigo", 0);
				resposta.append("mensagem", field.getDefaultMessage());
				return resposta.toString();
			}
			
			gastoDAO.save(gasto);

		} catch (Exception ex) {			
			resposta.append("codigo", 0);
			resposta.append("mensagem", "Erro ao gravar Gasto " + ex.getMessage());
			return resposta.toString();
		}
				
		resposta.append("codigo", 1);
		resposta.append("mensagem", "Gasto inserido com sucesso.");
		return resposta.toString();		
	}
}
