package com.example.Calculadora39Numeros.controlador;

import com.example.Calculadora39Numeros.entidad.Numero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Calculadora39Numeros.servicio.NumeroService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private NumeroService service;

    @GetMapping("/")
    public String inicio() {

        return "index";

    }

    @PostMapping("/sumar")
    public String sumar(@RequestParam List<Double> numero,
                        Model model) {

        List<Numero> lista = new ArrayList<>();

        for (Double n : numero) {

            lista.add(new Numero(n));

        }

        service.guardar(lista);

        Double suma = service.sumar();

        model.addAttribute("resultado", suma);

        return "index";
    }   
}
