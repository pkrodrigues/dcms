package com.api.dcms.service;

import com.api.dcms.model.entity.Agenda;
import com.api.dcms.model.repository.AgendaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AgendaService {
    private AgendaRepository repository;

    public AgendaService(AgendaRepository repository) {
        this.repository = repository;
    }

    public List<Agenda> getAgenda() {
        return repository.findAll();
    }

    public Optional<Agenda> getAgendaById(Long idAgenda) {
        return repository.findById(idAgenda);
    }

    @Transactional
    public Agenda salvar(Agenda agenda){
        return repository.save(agenda);
    }

    @Transactional
    public void excluir(Agenda agenda){
        Objects.requireNonNull(agenda.getIdAgenda());
        repository.delete(agenda);
    }
}
