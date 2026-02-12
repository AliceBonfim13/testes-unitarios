package com.alice.locadora.model;

import java.lang.module.ResolutionException;

public class Reserva {
    private Cliente cliente;
    private Carro carro;
    private int dias;

    public Reserva(Cliente cliente, Carro carro, int dias) {
        if (dias < 1) {
            throw new ReservaInvalidException("Invalido");
        }
        this.cliente = cliente;
        this.carro = carro;
        this.dias = dias;
    }

    public double calcularTotalReserva()   {

        return this.carro.calcularValorAAlugue(this.dias);
    }
}
