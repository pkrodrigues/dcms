package com.api.dcms.service;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Cargo;
import com.api.dcms.model.repository.CargoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    public Optional<Cargo> getCargoById(Long idCargo) {
        return repository.findById(idCargo);
    }

    public void validar(Cargo cargo) {
        if (cargo.getNomeCargo() == null || cargo.getNomeCargo().trim().equals("")) {
            throw new RegraNegocioException("Cargo inválido");
        }
    }

    @Transactional
    public Cargo salvar(Cargo cargo){
        validar(cargo);
        return repository.save(cargo);
    }
    @Transactional
    public void excluir(Cargo cargo) {
        Objects.requireNonNull(cargo.getIdCargo());
        repository.delete(cargo);
    }
}
