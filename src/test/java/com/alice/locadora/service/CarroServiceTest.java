package com.alice.locadora.service;

import com.alice.locadora.entity.CarroEntity;
import com.alice.locadora.model.exeption.EntityNotFoundException;
import com.alice.locadora.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    CarroService service;

    @Mock
    CarroRepository repository;

    @Test
    void deveSalvarUmCarro() {
        CarroEntity carroParaSalvar = new CarroEntity("Sedan", 10.0, 2027);

        CarroEntity carroParaRetornar = new CarroEntity("Sedan", 100.0, 2027);
        carroParaRetornar.setId(1L);

        Mockito.when(repository.save(Mockito.any())).thenReturn(carroParaRetornar);

        var carroSalvo = service.salvar(carroParaSalvar);

        assertNotNull(carroSalvo);
        assertEquals("Sedan", carroSalvo.getModelo());

        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void deveDarErroAoTentarSalvarCarroDiariaNegativa(){
        CarroEntity carro = new CarroEntity("Sedan",0, 2027);

        var  erro = catchThrowable(() -> service.salvar(carro));

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveAtualizarUmCarro() {

        Long id = 1L;

        var carroExistente = new CarroEntity("Gol", 80.0, 2026);
        carroExistente.setId(id);

        var carroAtualizacao = new CarroEntity("Sedan", 100.0, 2027);

        Mockito.when(repository.findById(id))
                .thenReturn(Optional.of(carroExistente));

        Mockito.when(repository.save(Mockito.any(CarroEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var resultado = service.atualizar(id, carroAtualizacao);

        assertNotNull(resultado);
        assertEquals("Sedan", resultado.getModelo());

        Mockito.verify(repository).save(Mockito.any(CarroEntity.class));
    }

    @Test
    void deveDarErroAoTentarAtualizarCarroExistente() {
        Long id = 1L;
        var carro = new CarroEntity("Gol", 80.0, 2026);

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.atualizar(id, carro));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveDarErroAoTentarDeletarCarroExistente() {
        Long id = 1L;

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.deletar(id));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(repository, Mockito.never()).delete(Mockito.any());
    }

    @Test
    void deveDeletarUmCarro() {
        Long id = 1L;
        var carro = new CarroEntity("Gol", 80.0, 2026);

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        service.deletar(id);


        Mockito.verify(repository, Mockito.never()).delete(carro);
    }

    @Test
    void deveBuscarCarroPorId() {
        Long id = 1L;
        var carro = new CarroEntity("Gol", 80.0, 2026);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        var carroEncontrado = service.buscarPorId(id);

        assertThat(carroEncontrado.getModelo()).isEqualTo("Gol");
        assertThat(carroEncontrado.getValorDiario()).isEqualTo(80);
        assertThat(carroEncontrado.getAno()).isEqualTo(2026);

    }

    @Test
    void deveListarTodos() {
        var carro1 = new CarroEntity(1L,"Gol", 80.0, 2026);
        var carro2 = new CarroEntity(1L,"Sedan", 100.0, 2027);

        var lista = List.of(carro1, carro2);
        Mockito.when(repository.findAll()).thenReturn(lista);

        List<CarroEntity> resultado = service.listarTodos();

        assertThat(resultado).hasSize(2);
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }

}