package br.com.constantino.androidviagem.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.constantino.androidviagem.entities.Viagem;

public interface ViagemDAO extends CrudRepository<Viagem, Long> {

}
