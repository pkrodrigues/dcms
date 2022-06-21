package com.api.dcms.service;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Cargo;
import com.api.dcms.model.repository.CargoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private CargoRepository repository;

    public CargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public List<Cargo> getCargo() {
        return repository.findAll();
    }

    public Optional<Cargo> getCargoById(Long id) {
        return repository.findById(id);
    }

    public void Validar(Cargo cargo) {
        if (cargo.getNomeCargo() == null || cargo.getNomeCargo().trim().equals("")) {
            throw new RegraNegocioException("Cargo inválido");
        }
    }

    @Transactional
    public Cargo Salvar(Cargo cargo){
        Validar(cargo);
        return repository.save(cargo);
    }
}
