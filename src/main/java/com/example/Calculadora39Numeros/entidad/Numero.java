package com.example.Calculadora39Numeros.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "numeros")
public class Numero {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;

    public Numero() {

    }

    public Numero(Double valor) {
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}   
