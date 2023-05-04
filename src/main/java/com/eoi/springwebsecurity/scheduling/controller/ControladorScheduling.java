package com.eoi.springwebsecurity.scheduling.controller;

import com.eoi.springwebsecurity.scheduling.entities.Cita;
import com.eoi.springwebsecurity.scheduling.service.SchedulingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
public class ControladorScheduling {


    @Autowired
    SchedulingService schedulingService;

    @GetMapping("/cita")
    public String crearCita(Model model)
        {
            model.addAttribute("cita", new Cita());
            return "/scheduling/cita";
        }

    @PostMapping("/cita/programar")
    public String programarCita(@ModelAttribute Cita cita)
    {
        log.error("Se ha recibido la programacion de una cita");
        schedulingService.crearCita(cita);
        return "redirect:/cita";
    }


}
