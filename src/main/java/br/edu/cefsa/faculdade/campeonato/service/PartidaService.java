package br.edu.cefsa.faculdade.campeonato.service;

import br.edu.cefsa.faculdade.campeonato.model.Partida;
import br.edu.cefsa.faculdade.campeonato.repository.PartidaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;

    public PartidaService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    @Transactional
    public Partida salvar(Partida partida) {
        // Regra de negócio 1: Impede que o time jogue contra ele mesmo
        if (partida.getTimeMandante() != null && partida.getTimeVisitante() != null &&
            partida.getTimeMandante().getId().equals(partida.getTimeVisitante().getId())) {
            throw new IllegalArgumentException("Erro crítico: Uma equipe não pode jogar contra si mesma.");
        }

        // Regra de negócio 3: Impede o agendamento de partidas no passado
        if (partida.getDataHora() != null && partida.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Erro de agendamento: A data da partida não pode ser no passado.");
        }
        
        return partidaRepository.save(partida);
    }

    public Partida buscarPorId(Long id) {
        return partidaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Partida não encontrada no sistema."));
    }

    public List<Partida> listarTodos() {
        return partidaRepository.findAll();
    }

    @Transactional
    public void excluir(Long id) {
        partidaRepository.deleteById(id);
    }
}