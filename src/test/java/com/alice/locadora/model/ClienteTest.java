package com.alice.locadora.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    void deveCriarClienteComNome() {
        var cliente = new Cliente("Alice");

        String nome = cliente.getNome();

        assertNotNull(nome);

        assertThat(nome).isEqualTo("Alice");
        assertThat(nome).isLessThan("Alice5");

        assertTrue(nome.startsWith("A"));
        assertFalse(nome.length() == 100);
    }

    @Test
    void deveCriarClienteSemNome() {
        var cliente = new Cliente(null);

        String nome = cliente.getNome();

        assertNull(nome);

    }
}
