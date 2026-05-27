package br.edu.cefsa.faculdade.campeonato.service;

import br.edu.cefsa.faculdade.campeonato.model.Time;
import br.edu.cefsa.faculdade.campeonato.repository.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Transactional
    public Time salvar(Time time) {
        return timeRepository.save(time);
    }

    public Time buscarPorId(Long id) {
        return timeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Time não encontrado no sistema."));
    }

    public List<Time> listarTodos() {
        return timeRepository.findAll();
    }

    @Transactional
    public void excluir(Long id) {
        timeRepository.deleteById(id);
    }
}