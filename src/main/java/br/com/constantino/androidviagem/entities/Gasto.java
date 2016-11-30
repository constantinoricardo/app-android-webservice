package br.com.constantino.androidviagem.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="gasto")
public class Gasto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="valor")
	private Double valor;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="data")
	private Calendar data;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="local")
	@NotNull
	private String local;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_categoria", referencedColumnName="id")
	})
	@JsonIgnore
	private Categoria categoria;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "{ Data :" + getData() + ", Descricao :" + getDescricao() + ", Categoria : " + getCategoria().getId() + "}";
	}		
}
