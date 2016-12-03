package br.com.constantino.androidviagem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.constantino.androidviagem.entities.TipoViagem;

public interface TipoViagemDAO extends CrudRepository<TipoViagem, Long> {

	@Query("select tv from TipoViagem tv")
	public List<TipoViagem> findTipoViagemAll();
}
