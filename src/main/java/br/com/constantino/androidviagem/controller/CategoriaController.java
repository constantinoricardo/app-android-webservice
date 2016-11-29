package br.com.constantino.androidviagem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.constantino.androidviagem.dao.CategoriaDAO;
import br.com.constantino.androidviagem.entities.Categoria;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaDAO categoriaDAO;

	@RequestMapping(value="/categoria/inserir", method=RequestMethod.POST)
	@ResponseBody
	public String inserir(@RequestParam(value="descricao") String descricao) {
		
		try {

			Categoria categoria = new Categoria();
			categoria.setDescricao(descricao);
			categoriaDAO.save(categoria);
			
		} catch (Exception ex) {
			return "Error: -> " + ex.getMessage();
		}
		
		return "Categoria cadastrada com sucesso";
	}
	
	@RequestMapping(value="/categoria/all")
	@ResponseBody
	public List<Categoria> all() {
 		List<Categoria> categorias = categoriaDAO.findCategoryAll();
		return categorias;
	}
}
