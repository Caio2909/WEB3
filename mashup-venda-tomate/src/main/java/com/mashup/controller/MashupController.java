package com.mashup.controller;

import com.mashup.dto.ResultadoVendaDTO;
import com.mashup.service.MashupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class MashupController {

    @Autowired
    private MashupService mashupService;

    @GetMapping("/calcular")
    public ResponseEntity<?> calcularVenda(
            @RequestParam int quantidade,
            @RequestParam int distancia) {
        try {
            ResultadoVendaDTO resultado = mashupService.calcularVenda(quantidade, distancia);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao processar a requisicao: " + e.getMessage());
        }
    }
}