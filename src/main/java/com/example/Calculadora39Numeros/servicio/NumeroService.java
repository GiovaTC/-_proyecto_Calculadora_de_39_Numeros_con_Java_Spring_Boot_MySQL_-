package com.example.Calculadora39Numeros.servicio;

import com.example.Calculadora39Numeros.entidad.Numero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Calculadora39Numeros.repositorio.NumeroRepository;

import java.util.List;

@Service
public class NumeroService {

    @Autowired
    private NumeroRepository repository;

    public void guardar(List<Numero> lista) {

        repository.deleteAll();

        repository.saveAll(lista);

    }

    public Double sumar() {

        return repository.findAll()
                .stream()
                .mapToDouble(Numero::getValor)
                .sum();

    }
}   
