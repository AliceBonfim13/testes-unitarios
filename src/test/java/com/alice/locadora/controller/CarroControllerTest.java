package com.alice.locadora.controller;

import com.alice.locadora.entity.CarroEntity;
import com.alice.locadora.model.exeption.EntityNotFoundException;
import com.alice.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CarroService carroService;

    @Test
    void deveSalvarUmCarro() throws Exception{
        CarroEntity carro = new CarroEntity(
                1L, "Honda Civic", 150, 2027);

        Mockito.when(carroService.salvar(Mockito.any())).thenReturn(carro);

        String json = """
                {
                    "modelo": "Honda Civic",
                    "valorDiario": 150,
                    "ano": 2027
                }
                """;

        ResultActions result = mvc.perform(post("/carros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        );

        result.andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.modelo").value("Honda Civic"));

    }

    @Test
    void deveRetornarNotFoundAoObterDetalhesCarroInexistente() throws Exception{
        Mockito.when(carroService.buscarPorId(Mockito.any())).thenThrow(EntityNotFoundException.class);


        mvc.perform(MockMvcRequestBuilders.get("/carros/1")).andExpect(status().isNotFound());

    }

    @Test
    void deveListarCarros() throws Exception {
        var listagem = List.of(
                new CarroEntity(1L, "Argo", 150, 2025),
                new CarroEntity(1L, "Celta", 150, 2025)

        );

        Mockito.when(carroService.listarTodos()).thenReturn(listagem);

        mvc.perform(MockMvcRequestBuilders.get("/carros"))
                .andExpect(jsonPath("$[0].modelo").value("Argo"))
                .andExpect(jsonPath("$[1].modelo").value("Celta"));
    }

    @Test
    void deveAtualizarCarro() throws Exception {
        Mockito.when(carroService.atualizar(Mockito.any(), Mockito.any()))
                .thenReturn(new CarroEntity(1L, "Celta", 100, 2025));
        String json = """
                {
                    "modelo": "Celta",
                    "valorDiario": 100,
                    "ano": 2025
                }
                """;

        mvc.perform(
                MockMvcRequestBuilders.put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isNoContent());
    }

    @Test
    void deveRetornarNotFoundAoTentarAtualizarCarroInexistente() throws Exception {
        Mockito.when(carroService.atualizar(Mockito.any(), Mockito.any())).thenThrow(EntityNotFoundException.class);

        String json = """
                {
                    "modelo": "Celta",
                    "valorDiario": 100,
                    "ano": 2025
                }
                """;

        mvc.perform(MockMvcRequestBuilders.put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());

    }

    @Test
    void deveDeltarUmCarro() throws Exception {
        Mockito.doNothing().when(carroService).deletar(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornarNotFoundAoDeltarUmCarroInexistente() throws Exception {
        Mockito.doThrow(EntityNotFoundException.class).when(carroService).deletar(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(status().isNotFound());
    }

}
