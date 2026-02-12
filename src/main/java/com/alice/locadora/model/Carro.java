package com.alice.locadora.model;

public class Carro {

    private String modelo;
    private double valorDiario;

    public Carro(String modelo, double valorDiario) {
        this.modelo = modelo;
        this.valorDiario = valorDiario;
    }

    public double calcularValorAAlugue(int dias) {
        double desconto = 0;
        if(dias >= 5) {
            desconto = 50.0;
        }
        return (dias * valorDiario) - desconto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(double valorDiario) {
        this.valorDiario = valorDiario;
    }
}
