package com.bruno.eventoapp.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bruno.eventoapp.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>, PagingAndSortingRepository<Evento, String>{
	Evento findByCodigo(Integer codigo);
	
	Collection<Evento> findAll();

}
