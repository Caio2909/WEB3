package com.example.demo;
import org.springframework.stereotype.Service;

@Service
public class FreteServico {
    public float calcularFrete(int distancia, String tipoVeiculo) {
        float precoPorKm;
        float taxaFixa;

        if ("caminhao".equalsIgnoreCase(tipoVeiculo)) {
            precoPorKm = 20;
            taxaFixa = 200;
        } else {
            precoPorKm = 40;
            taxaFixa = 400;
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