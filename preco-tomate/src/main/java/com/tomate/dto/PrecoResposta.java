package com.tomate.dto;

import java.math.BigDecimal;

public class PrecoResposta {
    private int quantidadeDeCaixas;
    private BigDecimal precoBasePorCaixa;
    private BigDecimal percentualDeDesconto;
    private BigDecimal precoFinalComDesconto;

    // Construtor
    public PrecoResposta(int quantidadeDeCaixas, BigDecimal precoBasePorCaixa, BigDecimal percentualDeDesconto, BigDecimal precoFinalComDesconto) {
        this.quantidadeDeCaixas = quantidadeDeCaixas;
        this.precoBasePorCaixa = precoBasePorCaixa;
        this.percentualDeDesconto = percentualDeDesconto;
        this.precoFinalComDesconto = precoFinalComDesconto;
    }

    // Getters (necessários para o Spring converter o objeto para JSON)
    public int getQuantidadeDeCaixas() { return quantidadeDeCaixas; }
    public BigDecimal getPrecoBasePorCaixa() { return precoBasePorCaixa; }
    public BigDecimal getPercentualDeDesconto() { return percentualDeDesconto; }
    public BigDecimal getPrecoFinalComDesconto() { return precoFinalComDesconto; }
}