package com.api.dcms.service;

import com.api.dcms.model.entity.ReceitaMedica;
import com.api.dcms.model.entity.Paciente;
import com.api.dcms.service.PacienteService;
import com.api.dcms.service.MedicoService;
import com.api.dcms.model.repository.ReceitaMedicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class ReceitaMedicaService {
    private TurmaRepository repository;

    public ReceitaMedica(ReceitaMedicaRepository repository) {
        this.repository = repository;
    }

    public List<ReceitaMedica> getReceitaMedica() {
        return repository.findAll();
    }

    public Optional<ReceitaMedica> getReceitaMedicaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Exame salvar(ReceitaMedica receitaMedica) {
        validar(receitaMedica);
        return repository.save(receitaMedica);
    }

    @Transactional
    public void excluir(ReceitaMedica receitaMedica) {
        Objects.requireNonNull(receitaMedica.getIdReceitaMedica());
        repository.delete(receitaMedica);

        public void validar(ReceitaMedica receitaMedica) {

            if (receitaMedica.getDtEmissaoReceita()== null || receitaMedica.getDtEmissaoReceita() == 0) {
                throw new RegraNegocioException("A Data de emissão é inválida");
            }
            if (receitaMedica.getPresquicao() == null || receitaMedica.getPresquicao() == 0) {
                throw new RegraNegocioException("o preenchimento da prescrição Médica não pode estar vazio");
            }
            if (receitaMedica.getPaciente().getNome() == null || receitaMedica.getPaciente().getNome().trim().equals("")|| receitaMedica.getPaciente().getIdPaciente() ==0) {
                throw new RegraNegocioException("Paciênte não encontrado");
            }
            if (receitaMedica.getMedico().getNome() == null || receitaMedica.getMedico().getNome().trim().equals("")|| receitaMedica.getMedico().getIdMedico() == 0) {
                throw new RegraNegocioException("Médico não encontrado");
            }
        }
    }


