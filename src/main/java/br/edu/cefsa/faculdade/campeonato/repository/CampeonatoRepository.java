package br.edu.cefsa.faculdade.campeonato.repository;


import br.edu.cefsa.faculdade.campeonato.model.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
}