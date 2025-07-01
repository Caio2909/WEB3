package com.example.demo;
import org.springframework.stereotype.Service;

@Service
public class FreteServico {

    public float calcularFrete(int distancia, int quantidadeCaixas) {
        float precoPorKm;
        float taxaFixa;
        int capacidade;

        if (quantidadeCaixas <= 250) {
            precoPorKm = 20;
            taxaFixa = 200;
            capacidade = 250;
        } else {
            precoPorKm = 40;
            taxaFixa = 400;
            capacidade = 1500;
        }

        float custoVariavel;
        if (distancia > 100) {
            custoVariavel = (100 * precoPorKm) + (float)((distancia - 100) * precoPorKm * 0.8);
        } else {
            custoVariavel = distancia * precoPorKm;
        }

        return custoVariavel + taxaFixa;
    }
}