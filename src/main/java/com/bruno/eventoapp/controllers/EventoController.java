package com.bruno.eventoapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);

		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);

		return mv;
	}

	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		String nomeConvidado = convidado.getNomeConvidado();
		attributes.addFlashAttribute("sucesso", "Convidado " + nomeConvidado + "  adicionado com sucesso!");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarEvento")
	public String deletarEvento(Long codigo) {
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
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

	@GetMapping("/eventos")
	public String viewHomePage(Model eventos) {
		return findPaginated(0, "nome", "asc", eventos);
	}
	
	@GetMapping("/page/{pagenum}")
	public String findPaginated(@PathVariable int pagenum, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		
		int pageSize = 5;
		
		Page<Evento> eventlist  = eventoService.getEvenByPaginate(pagenum, pageSize, sortField, sortDir);
		
		model.addAttribute("eventlist", eventlist);
		model.addAttribute("currentPage", pagenum);
		model.addAttribute("totalPages", eventlist.getTotalPages());
		model.addAttribute("totalItems", eventlist.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		//System.out.println("findPaginated");
		//System.out.println("eventlist: " + eventlist);
		//System.out.println("currentPage: " + pagenum);
		//System.out.println("totalPages: " + eventlist.getTotalPages());
		//System.out.println("totalItems: " + eventlist.getTotalElements());
		//System.out.println("sortField: " + sortField);
		//System.out.println("sortDir: " + sortDir);
		
		return "listaEventos";
	}
	
}
