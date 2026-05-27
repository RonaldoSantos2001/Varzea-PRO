package br.edu.cefsa.faculdade.campeonato.repository;


import br.edu.cefsa.faculdade.campeonato.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
}