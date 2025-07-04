package com.tomate.service;

import com.tomate.dto.PrecoResposta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TomatePrecoService {

    // O valor de R$50,00 foi definido aqui.
    private static final BigDecimal BASE_PRICE_PER_BOX = new BigDecimal("50.00");

    //Calcula o preço final das caixas de tomate com base na quantidade e na tabela de descontos.
   
    
    public PrecoResposta CalcularPreco(int quantidade) {
        if (quantidade <= 0) {
            // Lança um erro se a quantidade for inválida. O Controller irá tratar este erro.
            throw new IllegalArgumentException("A quantidade de caixas deve ser maior que zero.");
        }

        BigDecimal PorcentagemDesconto = getDiscountPercentage(quantidade);
        BigDecimal TotalPrecoBase = BASE_PRICE_PER_BOX.multiply(new BigDecimal(quantidade));
        
        // A fórmula para aplicar o desconto é: PreçoFinal = PreçoTotal * (1 - Desconto)
        // Onde o desconto é um valor entre 0 e 1 (ex: 5% = 0.05)
        BigDecimal MultiplicadorDesconto = BigDecimal.ONE.subtract(PorcentagemDesconto.divide(new BigDecimal(100)));
        BigDecimal PrecoFinal = TotalPrecoBase.multiply(MultiplicadorDesconto);

        // Arredondamos o resultado para 2 casas decimais, o padrão para valores monetários.
        PrecoFinal = PrecoFinal.setScale(2, RoundingMode.HALF_UP);

        return new PrecoResposta(
                quantidade,
                BASE_PRICE_PER_BOX,
                PorcentagemDesconto,
                PrecoFinal
        );
    }

    
    //Retorna o percentual de desconto com base na quantidade de caixas.
    
    private BigDecimal getDiscountPercentage(int quantidade) {
        if (quantidade >= 30) {
            return new BigDecimal("22"); // 22%
        } else if (quantidade >= 20) {
            return new BigDecimal("11"); // 11%
        } else if (quantidade >= 10) {
            return new BigDecimal("5");  // 5%
        } else {
            return BigDecimal.ZERO;      // 0%
        }
    }
}