package com.alice.locadora.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carro")
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;

    @Column(name = "valor_diario")
    private double valorDiario;
    private int ano;

    public CarroEntity() {
    }

    public CarroEntity(String modelo, double valorDiario, int ano) {
        this.modelo = modelo;
        this.valorDiario = valorDiario;
        this.ano = ano;
    }

    public CarroEntity(Long id, String modelo, double valorDiario, int ano) {
        this.id = id;
        this.modelo = modelo;
        this.valorDiario = valorDiario;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
