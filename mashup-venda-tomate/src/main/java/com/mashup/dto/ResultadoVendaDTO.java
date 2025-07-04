package com.mashup.dto;

import java.math.BigDecimal;

public class ResultadoVendaDTO {
    private BigDecimal precoTomates;
    private BigDecimal custoFrete;
    private String tipoCaminhao;
    private BigDecimal lucro;
    private BigDecimal impostos;
    private BigDecimal valorFinal;

    // Getters and Setters
    public BigDecimal getPrecoTomates() { return precoTomates; }
    public void setPrecoTomates(BigDecimal precoTomates) { this.precoTomates = precoTomates; }
    public BigDecimal getCustoFrete() { return custoFrete; }
    public void setCustoFrete(BigDecimal custoFrete) { this.custoFrete = custoFrete; }
    public String getTipoCaminhao() { return tipoCaminhao; }
    public void setTipoCaminhao(String tipoCaminhao) { this.tipoCaminhao = tipoCaminhao; }
    public BigDecimal getLucro() { return lucro; }
    public void setLucro(BigDecimal lucro) { this.lucro = lucro; }
    public BigDecimal getImpostos() { return impostos; }
    public void setImpostos(BigDecimal impostos) { this.impostos = impostos; }
    public BigDecimal getValorFinal() { return valorFinal; }
    public void setValorFinal(BigDecimal valorFinal) { this.valorFinal = valorFinal; }
}
