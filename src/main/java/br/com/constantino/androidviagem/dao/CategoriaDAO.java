package br.com.constantino.androidviagem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.constantino.androidviagem.entities.Categoria;

@Transactional
public interface CategoriaDAO extends CrudRepository<Categoria, Long> {

	@Query("select c from Categoria c")
	public List<Categoria> findCategoryAll();
	
	@Query("select c from Categoria c where c.id = ?")
	public Categoria findCategoryById(Integer id);
	
}
