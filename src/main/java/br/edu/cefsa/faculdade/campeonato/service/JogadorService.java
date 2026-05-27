package br.edu.cefsa.faculdade.campeonato.service;


import br.edu.cefsa.faculdade.campeonato.model.Jogador;
import br.edu.cefsa.faculdade.campeonato.repository.JogadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    @Transactional
    public Jogador salvar(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }
    
    public java.util.List<Jogador> listarTodos() {
        return jogadorRepository.findAll();
    }
    
    @Transactional
    public void excluir(Long id) {
        jogadorRepository.deleteById(id);
    }
    
    public Jogador buscarPorId(Long id) {
        return jogadorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Jogador não encontrado no sistema."));
    }
}