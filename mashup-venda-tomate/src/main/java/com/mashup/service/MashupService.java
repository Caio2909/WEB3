package com.mashup.service;

import com.mashup.dto.PrecoTomateResponseDTO;
import com.mashup.dto.ResultadoVendaDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
public class MashupService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ResultadoVendaDTO calcularVenda(int quantidade, int distancia) {
        String precoTomateUrl = "http://localhost:8080/api/tomate/preco?quantidade=" + quantidade;
        PrecoTomateResponseDTO precoTomateInfo = restTemplate.getForObject(precoTomateUrl, PrecoTomateResponseDTO.class);
        BigDecimal precoTomates = precoTomateInfo.getPrecoFinalComDesconto();

        String tipoCaminhao = (quantidade > 250) ? "carreta" : "caminhao";
        BigDecimal custoFrete = chamarServicoFrete(distancia, tipoCaminhao);

        BigDecimal custoTotal = precoTomates.add(custoFrete);
        BigDecimal lucro = custoTotal.multiply(new BigDecimal("0.55"));
        BigDecimal valorComLucro = custoTotal.add(lucro);

        BigDecimal desconto;
        if (quantidade > 300) {
            desconto = valorComLucro.multiply(new BigDecimal("0.12"));
        } else if (quantidade > 50) {
            desconto = valorComLucro.multiply(new BigDecimal("0.075"));
        } else {
            desconto = BigDecimal.ZERO;
        }

        BigDecimal valorComDesconto = valorComLucro.subtract(desconto);
        BigDecimal impostos = valorComDesconto.multiply(new BigDecimal("0.27"));
        BigDecimal valorFinal = valorComDesconto.add(impostos);

        ResultadoVendaDTO resultado = new ResultadoVendaDTO();
        resultado.setPrecoTomates(precoTomates.setScale(2, RoundingMode.HALF_UP));
        resultado.setCustoFrete(custoFrete.setScale(2, RoundingMode.HALF_UP));
        resultado.setTipoCaminhao(tipoCaminhao);
        resultado.setLucro(lucro.setScale(2, RoundingMode.HALF_UP));
        resultado.setImpostos(impostos.setScale(2, RoundingMode.HALF_UP));
        resultado.setValorFinal(valorFinal.setScale(2, RoundingMode.HALF_UP));

        return resultado;
    }

    private BigDecimal chamarServicoFrete(int distancia, String tipoCaminhao) {
        String freteUrl = "http://localhost:8081/graphql";
        String query = String.format("{\"query\": \"{ calcularFrete(distancia: %d, tipoVeiculo: \\\"%s\\\") }\"}", distancia, tipoCaminhao);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        Map<String, Object> response = restTemplate.postForObject(freteUrl, entity, Map.class);

        if (response.containsKey("errors")) {
            List<Map<String, Object>> errors = (List<Map<String, Object>>) response.get("errors");
            throw new RuntimeException("Erro retornado pelo serviço de frete: " + errors.get(0).get("message"));
        }

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        Object rawFrete = data.get("calcularFrete");

        return new BigDecimal(rawFrete.toString());
    }
}