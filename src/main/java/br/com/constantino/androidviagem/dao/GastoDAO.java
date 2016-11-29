package br.com.constantino.androidviagem.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.constantino.androidviagem.entities.Gasto;

@Transactional
public interface GastoDAO extends CrudRepository<Gasto, Integer> {

}
