package com.bruno.eventoapp.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Convidado {

	@Id
	@NotEmpty
	@Valid
	private String rg;
	
	@NotEmpty(message = "Campo NOME CONVIDADO é requerido!")
	@Size(min = 5, max = 100, message = "Campo NOME CONVIDADO deve ter entre 5 e 100 caracteres!")
	@Valid
	private String nomeConvidado;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "evento_rg")
	private Evento evento;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	
	public Convidado() {
		super();
	}

	public Convidado(String rg, String nomeConvidado, Evento evento) {
		super();
		this.rg = rg;
		this.nomeConvidado = nomeConvidado;
		this.evento = evento;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(evento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convidado other = (Convidado) obj;
		return Objects.equals(rg, other.rg);
	}
	
	
}
