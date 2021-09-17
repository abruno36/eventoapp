package com.bruno.eventoapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.bruno.eventoapp.models.Convidado;
import com.bruno.eventoapp.models.Evento;
import com.bruno.eventoapp.repository.ConvidadoRepository;
import com.bruno.eventoapp.repository.EventoRepository;
import com.bruno.eventoapp.services.EventoService;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;

	@Autowired
	private ConvidadoRepository cr;
	
	@Autowired
	private EventoService eventoService;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public RedirectView form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {

		System.out.println("evento.getData: " + evento.getData());
		String dataformatada = evento.getData().replaceAll("-", "/");
		System.out.println("Data Formatada: " + dataformatada);
		String[] s = dataformatada.split("/");
		String novaData = s[2] + "/" + s[1] + "/" + s[0];
		System.out.println("Nova Data: " + novaData);

		evento.setData(novaData);
		System.out.println("Evento Data: " + evento.getData());

		if (result.hasErrors()) {
			String nomeEven = evento.getNome();
			String local = evento.getLocal();
			String data1 = evento.getData();
			String horario = evento.getHorario();
			attributes.addFlashAttribute("erro", "Verifique os campos digitados! Nome: " + nomeEven + " | Local: "
					+ local + " | Data: " + data1 + " | Horário: " + horario);
			return new RedirectView("/cadastrarEvento");
		}
		er.save(evento);
		String nomeEvent = evento.getNome();
		attributes.addFlashAttribute("sucesso", "Evento " + nomeEvent + "  adicionado com sucesso!");
		return new RedirectView("/cadastrarEvento");
	}

	@RequestMapping("/eventos")
	public String viewHomePage(Model eventos) {
		return viewPage(eventos, 1, "name", "asc");
	}
	
	@RequestMapping("/eventos/{pageNum}")
	public String viewPage(Model eventos, 
			@PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir) {
			
	
	Page<Evento> page = eventoService.listAll(pageNum, sortField, sortDir); 
		
	List<Evento> listEventos = page.getContent();
	
	eventos.addAttribute("currentPage", pageNum);
	eventos.addAttribute("totalPages", page.getTotalPages());
	eventos.addAttribute("totalItems", page.getTotalElements());
	eventos.addAttribute("listEventos", listEventos);
	
	eventos.addAttribute("sortField", sortField);
	eventos.addAttribute("sortDir", sortDir);
	eventos.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	
	eventos.addAttribute("listEventos", listEventos);
	
	return ("listaEventos");
}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") Integer codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);

		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);

		return mv;
	}

	@RequestMapping("/deletarEvento")
	public String deletarEvento(Integer codigo) {
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public RedirectView detalhesEventoPost(@PathVariable("codigo") Integer codigo, @Valid Convidado convidado,
			BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			String nomeConv = convidado.getNomeConvidado();
			String rgConv = convidado.getRg();
			attributes.addFlashAttribute("erro",
					"Verifique os campos digitados! Nome: " + nomeConv + " | RG: " + rgConv);
			return new RedirectView("/{codigo}");
		}
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		String nomeConv = convidado.getNomeConvidado();
		attributes.addFlashAttribute("sucesso", "convidado " + nomeConv + " adicionado com sucesso!");
		return new RedirectView("/{codigo}");
	}

	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);

		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}

}
