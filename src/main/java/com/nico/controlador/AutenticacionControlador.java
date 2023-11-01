package com.nico.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AutenticacionControlador {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
