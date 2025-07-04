package com.tomate.service;

import com.tomate.dto.PrecoResposta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TomatePrecoService {

    private static final BigDecimal BASE_PRICE_PER_BOX = new BigDecimal("50.00");
    public PrecoResposta CalcularPreco(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de caixas deve ser maior que zero.");
        }
        BigDecimal PorcentagemDesconto = getDiscountPercentage(quantidade);
        BigDecimal TotalPrecoBase = BASE_PRICE_PER_BOX.multiply(new BigDecimal(quantidade));
        BigDecimal MultiplicadorDesconto = BigDecimal.ONE.subtract(PorcentagemDesconto.divide(new BigDecimal(100)));
        BigDecimal PrecoFinal = TotalPrecoBase.multiply(MultiplicadorDesconto);
        PrecoFinal = PrecoFinal.setScale(2, RoundingMode.HALF_UP);

        return new PrecoResposta(
                quantidade,
                BASE_PRICE_PER_BOX,
                PorcentagemDesconto,
                PrecoFinal
        );
    }

    private BigDecimal getDiscountPercentage(int quantidade) {
        if (quantidade >= 30) {
            return new BigDecimal("22");
        } else if (quantidade >= 20) {
            return new BigDecimal("11");
        } else if (quantidade >= 10) {
            return new BigDecimal("5");
        } else {
            return BigDecimal.ZERO;
        }
    }
}