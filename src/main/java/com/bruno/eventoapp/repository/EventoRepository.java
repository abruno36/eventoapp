package com.bruno.eventoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.eventoapp.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
	Evento findByCodigo(Integer codigo);
	
		
}
