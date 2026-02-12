package com.alice.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

     @Test
     @DisplayName("Deve calcular o valor correto do aluguel")
     void deveCalcularValorAluguel() {
         // 1. Cenário
         Carro carro = new Carro("Sedan", 100.0);

         // 2. Execução
         double total = carro.calcularValorAAlugue(3);

         // 3. Verificação
         Assertions.assertEquals(300.0, total);
     }

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel")
    void deveCalcularValorAluguelComDesconto() {
        // 1. Cenário
        Carro carro = new Carro("Sedan", 100.0);
        int quantidade = 5;

        // 2. Execução
        double total = carro.calcularValorAAlugue(quantidade);

        // 3. Verificação
        Assertions.assertEquals(450.0, total);
    }
}
