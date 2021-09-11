package com.bruno.eventoapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;

	@NotEmpty(message = "Campo NOME é requerido!")
	@Size(min = 5, max = 100, message = "Campo NOME deve ter entre 5 e 100 caracteres!")
	private String nome;

	@NotEmpty(message = "Campo LOCAL é requerido!")
	@Size(min = 5, max = 100, message = "Campo LOCAL deve ter entre 5 e 100 caracteres!")
	private String local;

	@NotEmpty
	private String data;

	@NotEmpty
	private String horario;

	@OneToMany(mappedBy = "evento")
	private List<Convidado> convidados = new ArrayList<>();

	public Integer getCodigo() {
		return codigo;
	}

	public Evento() {
		super();
	}

	public Evento(Integer codigo, String nome, String local, String data, String horario) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.local = local;
		this.data = data;
		this.horario = horario;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public List<Convidado> getConvidados() {
		return convidados;
	}

	public void setConvidados(List<Convidado> convidados) {
		this.convidados = convidados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return codigo == other.codigo;
	}

}
