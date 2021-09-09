package com.bruno.eventoapp.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.eventoapp.models.Convidado;
import com.bruno.eventoapp.models.Evento;
import com.bruno.eventoapp.repository.ConvidadoRepository;
import com.bruno.eventoapp.repository.EventoRepository;

@Service
public class DBService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ConvidadoRepository convidadoRepository;

	public void instanciaBaseDeDados() {

		Evento e1 = new Evento(null, "Show Zé Ramalho", "Santos", "2021-08-09", "22:00");
		Evento e2 = new Evento(null, "Show Zeca Pagodinho", "São Paulo", "2021-09-30", "18:00");
		Evento e3 = new Evento(null, "Parque São Cristovão", "Marilia", "2021-09-15", "09:00");
		Evento e4 = new Evento(null, "Dinossaurus REX", "Shooping Eldorado", "2021-10-23", "10:00");
		Evento e5 = new Evento(null, "As Paisagens de Claude Monet", "Shopping Pátio Higienópolis", "2021-09-13", "09:00");
		Evento e6 = new Evento(null, "5º Festival Gastronômico", "Bairro do Limão", "2021-11-10", "22:00");
		Evento e7 = new Evento(null, "100 anos do goleiro Barbosa", "Santos", "2021-12-05", "22:00");
		Evento e8 = new Evento(null, "Obras de Arte", "Masp SP", "2021-10-21", "10:00");
		Evento e9 = new Evento(null, "Pinturas Naturais", "KVT Instituto", "2021-11-13", "09:00");
		Evento e10 = new Evento(null, "Turma da Mônica", "Shooping Plaza", "2021-11-10", "22:00");
		Evento e11 = new Evento(null, "Show Recruta Zero", "Monte Verde", "2021-12-05", "22:00");

		Convidado c1 = new Convidado(null, "Marcos Barbosa", e1);
		Convidado c2 = new Convidado(null, "Samuel Santos", e1);
		Convidado c3 = new Convidado(null, "antonio Bruno", e1);
		Convidado c4 = new Convidado(null, "Marcos Barbosa", e2);
		Convidado c5 = new Convidado(null, "Samuel Santos", e2);
		Convidado c6 = new Convidado(null, "Antonio Bruno", e3);
		Convidado c7 = new Convidado(null, "Yago Otawary e Tatiana", e8);
		Convidado c8 = new Convidado(null, "Debora Domingues", e8);
		Convidado c9 = new Convidado(null, "Antonio Bruno e Yago", e9);
		Convidado c10 = new Convidado(null, "Marcelo Egidio", e10);
		Convidado c11 = new Convidado(null, "Marcos Souza", e11);

		e1.getConvidados().addAll(Arrays.asList(c1, c2, c3));

		this.eventoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11));
		this.convidadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11));
	}

}