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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="viagem")
public class Viagem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="destino")
	private String destino;
	
	@Column(name="data_chegada")
	private Calendar data_chegada;
	
	@Column(name="data_saida")
	private Calendar data_saida;
	
	@Column(name="orcamento")
	private Double orcamento;
	
	@Column(name="qtde_pessoas")
	private int qtde_pessoas;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_tipoviagem", referencedColumnName="id")
	})
	@JsonIgnore
	private TipoViagem tipoviagem;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Calendar getData_chegada() {
		return data_chegada;
	}

	public void setData_chegada(Calendar data_chegada) {
		this.data_chegada = data_chegada;
	}

	public Calendar getData_saida() {
		return data_saida;
	}

	public void setData_saida(Calendar data_saida) {
		this.data_saida = data_saida;
	}

	public Double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Double orcamento) {
		this.orcamento = orcamento;
	}

	public int getQtde_pessoas() {
		return qtde_pessoas;
	}

	public void setQtde_pessoas(int qtde_pessoas) {
		this.qtde_pessoas = qtde_pessoas;
	}

	public TipoViagem getTipoViagem() {
		return tipoviagem;
	}

	public void setTipoViagem(TipoViagem tipoviagem) {
		this.tipoviagem = tipoviagem;
	}
	
}
