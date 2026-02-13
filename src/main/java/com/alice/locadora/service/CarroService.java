package com.alice.locadora.service;

import com.alice.locadora.entity.CarroEntity;
import com.alice.locadora.model.exeption.EntityNotFoundException;
import com.alice.locadora.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public CarroEntity salvar(CarroEntity carro) {
        if (carro.getValorDiario() <= 0) {
            throw new IllegalArgumentException("Preço da diaria não pode ser negativo");
        }

        return repository.save(carro);
    }

    public CarroEntity atualizar(Long id, CarroEntity carroAtualizado) {
        var carroExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));

        carroExistente.setAno(carroAtualizado.getAno());
        carroExistente.setModelo(carroAtualizado.getModelo());
        carroExistente.setValorDiario(carroAtualizado.getValorDiario());

        return repository.save(carroExistente);
    }

    public void deletar(Long id) {
        var carroExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não entrado"));
        repository.deleteById(id);
    }

    public CarroEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não entrado"));
    }

    public List<CarroEntity> listarTodos() {
        return repository.findAll();
    }

}
