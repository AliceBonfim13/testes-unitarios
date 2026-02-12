package com.alice.locadora.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservaTest {

    Cliente cliente;
    Carro carro;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Alice");
        carro = new Carro("SUV", 50.0);
    }

    @Test
    void deveCriarUmaReserva() {
        var dias = 5;
        var reserva = new Reserva(cliente, carro, dias);

        assertThat(reserva).isNotNull();
    }

    @Test
    void deveDarErroAoCriarReserva(){
        //JUnit
        assertThrows(ReservaInvalidException.class, () -> new Reserva(cliente, carro, 0));

        //AssertJ
        var erro = org.assertj.core.api.Assertions.catchThrowable(() -> new Reserva(cliente, carro, 0));
        org.assertj.core.api.Assertions.assertThat(erro).isInstanceOf(ReservaInvalidException.class);
    }

    @Test
    void deveCalcularTotalDoAluquel() {
        var reserva = new Reserva(cliente, carro, 3);

        var total = reserva.calcularTotalReserva();

        org.assertj.core.api.Assertions.assertThat(total).isEqualTo(150);
    }
}
