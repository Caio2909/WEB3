package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FreteController {

    @Autowired
    private FreteServico freteServico;

    @QueryMapping
    public float calcularFrete(@Argument int distancia, @Argument int quantidadeCaixas) {
        return freteServico.calcularFrete(distancia, quantidadeCaixas);
    }
}