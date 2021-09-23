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

	public void instanciaBaseDeDados()  {
		
		Evento e1 = new Evento(1, "Show Zé Ramalho", "Santos", "03/08/2022", "22:00");
		Evento e2 = new Evento(2, "Show Zeca Pagodinho", "São Paulo", "30/09/2021", "18:00");
		Evento e3 = new Evento(3, "Parque São Cristovão", "Marilia", "15/09/2021", "09:00");
		Evento e4 = new Evento(4, "Dinossaurus REX", "Shooping Eldorado", "23/10/2021", "10:00");
		Evento e5 = new Evento(5, "As Paisagens de Claude Monet", "Shopping Pátio Higienópolis", "23/09/2021", "09:00");
		Evento e6 = new Evento(6, "5º Festival Gastronômico", "Bairro do Limão", "10/11/2021", "22:00");
		Evento e7 = new Evento(7, "100 anos do goleiro Barbosa", "Santos", "05/12/2021", "22:00");
		Evento e8 = new Evento(8, "Obras de Arte", "Masp SP", "21/10/2021", "10:00");
		Evento e9 = new Evento(9, "Pinturas Naturais", "KVT Instituto", "13/11/2021", "09:00");
		Evento e10 = new Evento(10, "Turma da Mônica", "Shooping Plaza", "10/11/2021", "22:00");
		Evento e11 = new Evento(11, "Show Recruta Zero", "Monte Verde", "05/12/2021", "22:00");
		Evento e12 = new Evento(12, "Pinturas Naturais II", "KVT Instituto", "13/12/2021", "09:00");
		Evento e13 = new Evento(13, "Feira Artesanato", "KVT RD", "10/11/2021", "09:00");
		Evento e14 = new Evento(14, "Show Bruno e Marroni", "Monte Verde", "10/12/2021", "22:00");
		
		Convidado c1 = new Convidado("AB-1234", "Marcos Barbosa", e1);
		Convidado c2 = new Convidado("AB-678", "Samuel Santos", e1);
		Convidado c3 = new Convidado("AC-44444", "antonio Bruno", e1);
		Convidado c4 = new Convidado("AD-44445", "Marcos Barbosa", e2);
		Convidado c5 = new Convidado("AD-44446", "Samuel Santos", e2);
		Convidado c6 = new Convidado("AD-44447", "Antonio Bruno", e3);
		Convidado c7 = new Convidado("AD-44448", "Yago Otawary e Tatiana", e8);
		Convidado c8 = new Convidado("AD-44449", "Debora Domingues", e8);
		Convidado c9 = new Convidado("AD-44410", "Antonio Bruno e Yago", e9);
		Convidado c10 = new Convidado("AD-44411", "Marcelo Egidio", e10);
		Convidado c11 = new Convidado("AD-44412", "Marcos Souza", e11);

		e1.getConvidados().addAll(Arrays.asList(c1, c2, c3));

		this.eventoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14));
		this.convidadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11));
	}

}
