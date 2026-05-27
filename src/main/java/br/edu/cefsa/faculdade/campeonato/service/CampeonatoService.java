package br.edu.cefsa.faculdade.campeonato.service;

import br.edu.cefsa.faculdade.campeonato.model.Campeonato;
import br.edu.cefsa.faculdade.campeonato.repository.CampeonatoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository) {
        this.campeonatoRepository = campeonatoRepository;
    }

    @Transactional
    public Campeonato salvar(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    public Campeonato buscarPorId(Long id) {
        return campeonatoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Torneio não encontrado no sistema."));
    }

    public List<Campeonato> listarTodos() {
        return campeonatoRepository.findAll();
    }
    
    @Transactional
    public void excluir(Long id) {
        // Verifica se o torneio existe antes de tentar excluir
        if (!campeonatoRepository.existsById(id)) {
            throw new IllegalArgumentException("Torneio não encontrado para exclusão.");
        }
        campeonatoRepository.deleteById(id);
    }
}