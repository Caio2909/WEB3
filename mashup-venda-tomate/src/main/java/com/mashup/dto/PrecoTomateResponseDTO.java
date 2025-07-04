package com.mashup.dto;

import java.math.BigDecimal;

public class PrecoTomateResponseDTO {
    private int quantidadeDeCaixas;
    private BigDecimal precoBasePorCaixa;
    private BigDecimal percentualDeDesconto;
    private BigDecimal precoFinalComDesconto;

    // Getters e Setters
    public int getQuantidadeDeCaixas() { return quantidadeDeCaixas; }
    public void setQuantidadeDeCaixas(int quantidadeDeCaixas) { this.quantidadeDeCaixas = quantidadeDeCaixas; }
    public BigDecimal getPrecoBasePorCaixa() { return precoBasePorCaixa; }
    public void setPrecoBasePorCaixa(BigDecimal precoBasePorCaixa) { this.precoBasePorCaixa = precoBasePorCaixa; }
    public BigDecimal getPercentualDeDesconto() { return percentualDeDesconto; }
    public void setPercentualDeDesconto(BigDecimal percentualDeDesconto) { this.percentualDeDesconto = percentualDeDesconto; }
    public BigDecimal getPrecoFinalComDesconto() { return precoFinalComDesconto; }
    public void setPrecoFinalComDesconto(BigDecimal precoFinalComDesconto) { this.precoFinalComDesconto = precoFinalComDesconto; }
}