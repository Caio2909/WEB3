package com.tomate.controlador;

import com.tomate.dto.PrecoResposta;
import com.tomate.service.TomatePrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tomate") // Define um prefixo para todos os endpoints deste controller
public class TomatoController {

    private final TomatePrecoService tomatePrecoService;

    @Autowired
    public TomatoController(TomatePrecoService tomatePrecoService) {
        this.tomatePrecoService = tomatePrecoService;
    }

    // Mapeia requisições GET para /api/tomate/preco
    @GetMapping("/preco")
    public ResponseEntity<Object> getTomatoPrice(@RequestParam int quantidade) {
        try {
            PrecoResposta response = tomatePrecoService.CalcularPreco(quantidade);
            // Retorna uma resposta de sucesso (código 200 OK) com o JSON no corpo.
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Se o serviço lançar o erro de argumento inválido, retorna uma resposta de erro (código 400).
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}