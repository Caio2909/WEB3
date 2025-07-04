package com.example.demo;
import org.springframework.stereotype.Service;

@Service
public class FreteServico {
    public float calcularFrete(int distancia, String tipoVeiculo) {
        float precoPorKm;
        float taxaFixa;

        if ("caminhao".equalsIgnoreCase(tipoVeiculo)) {
            precoPorKm = 20; // [cite: 18]
            taxaFixa = 200; // [cite: 20]
        } else if ("carreta".equalsIgnoreCase(tipoVeiculo)) {
            precoPorKm = 40; // [cite: 21]
            taxaFixa = 400; // [cite: 22]
        } else {
            // Lançar uma exceção ou definir um padrão
            throw new IllegalArgumentException("Tipo de veículo inválido: " + tipoVeiculo);
        }

        float custoVariavel;
        if (distancia > 100) {
            // Aplica desconto de 20% no valor do km após 100km [cite: 23]
            custoVariavel = (100 * precoPorKm) + (float)((distancia - 100) * precoPorKm * 0.8);
        } else {
            custoVariavel = distancia * precoPorKm;
        }

        return custoVariavel + taxaFixa;
    }
}