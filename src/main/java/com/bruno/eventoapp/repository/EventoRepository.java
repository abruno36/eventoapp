package com.bruno.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.bruno.eventoapp.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{
	Evento findByCodigo(Integer codigo);


}
