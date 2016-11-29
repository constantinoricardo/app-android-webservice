package br.com.constantino.androidviagem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="descricao")
	private String descricao;
	
	@OneToMany(mappedBy="categoria")
	private List<Gasto> gasto;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@JsonIgnore
	public List<Gasto> getGasto() {
		return gasto;
	}
	
	public void setGasto(List<Gasto> gasto) {
		this.gasto = gasto;
	}
	
	@Override
	public String toString() {
		return "Descrição " + this.getDescricao() + " ID " + this.getId();
	}
	
}