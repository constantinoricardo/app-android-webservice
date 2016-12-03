package br.com.constantino.androidviagem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.constantino.androidviagem.dao.TipoViagemDAO;
import br.com.constantino.androidviagem.entities.TipoViagem;

@RestController
public class TipoViagemController {

	@Autowired
	private TipoViagemDAO tipoViagemDAO;
	
	
	@RequestMapping(value = "/tipoViagem/all", method=RequestMethod.POST)
	public List<TipoViagem> all() {
		
		List<TipoViagem> tipoViagens = tipoViagemDAO.findTipoViagemAll();
		return tipoViagens;
	}
	
	@RequestMapping(value = "/tipoViagem/inserir", method=RequestMethod.POST)
	public String inserir(@RequestParam(value="descricao") String descricao) {
		
		try {
			
			if (descricao == null)
				return "Por favor, informe a descrição.";
	
			TipoViagem tipoViagem = new TipoViagem();
			tipoViagem.setDescricao(descricao);
			tipoViagemDAO.save(tipoViagem);
			
			
		} catch (Exception ex) {
			return "Erro " + ex.getMessage();
		}
		
		return "Tipo de Viagem incluído com sucesso";
	}
}
