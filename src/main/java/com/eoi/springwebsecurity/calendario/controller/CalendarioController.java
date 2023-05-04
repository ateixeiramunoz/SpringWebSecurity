package com.eoi.springwebsecurity.calendario.controller;

import com.eoi.springwebsecurity.calendario.entities.Calendario;
import com.eoi.springwebsecurity.calendario.entities.Evento;
import com.eoi.springwebsecurity.calendario.repositories.CalendarioRepository;
import com.eoi.springwebsecurity.calendario.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/calendarios")
public class CalendarioController {

    @Autowired
    CalendarioRepository calendarioRepository;

    @Autowired
    EventoRepository eventoRepository;


    @GetMapping("")
    public String listaCalendarios(Model model) {
        List<Calendario> calendarios = calendarioRepository.findAll();
        model.addAttribute("calendarios", calendarios);
        return "calendario/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrearCalendario(Model model) {
        model.addAttribute("calendario", new Calendario());
        return "calendario/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCalendario(@ModelAttribute Calendario calendario) {
        calendarioRepository.save(calendario);
        return "redirect:/calendarios";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarCalendario(@PathVariable Integer id, Model model) {
        Optional<Calendario> calendario = calendarioRepository.findById(id);
        model.addAttribute("calendario", calendario.orElseThrow(() -> new NoSuchElementException("Calendario no encontrado")));
        return "calendario/formulario";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarCalendario(@PathVariable Integer id) {
        calendarioRepository.deleteById(id);
        return "redirect:/calendarios";
    }

    @GetMapping("/{id}/eventos")
    public String listaEventos(@PathVariable Integer id, Model model) {
        Optional<Calendario> calendario = calendarioRepository.findById(id);
        List<Evento> eventos = calendario.map(Calendario::getEventos).orElse(Collections.emptyList());
        model.addAttribute("calendario", calendario.orElseThrow(() -> new NoSuchElementException("Calendario no encontrado")));
        model.addAttribute("eventos", eventos);
        return "calendario/eventos";
    }

    @GetMapping("/{id}/eventos/nuevo")
    public String mostrarFormularioDeCreacionDeEvento(@PathVariable("id") int id, Model model) {
        Calendario calendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de calendario inválido: " + id));
        Evento evento = new Evento();
        List<Calendario> calendarios = new ArrayList<>();
        calendarios.add(calendario);
        evento.setCalendarios(calendarios);
        model.addAttribute("evento", evento);
        model.addAttribute("calendarioId", id);

        return "calendario/eventos/formulario";
    }

    @PostMapping("/{id}/eventos/guardar")
    public String guardarEventoEnCalendario(@PathVariable("id") int id, @ModelAttribute("evento") Evento evento) {
        Calendario calendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de calendario inválido: " + id));
        calendario.getEventos().add(evento);
        calendarioRepository.save(calendario); // Guardar el calendario actualizado
        return "redirect:/calendarios/" + id;
    }



}
