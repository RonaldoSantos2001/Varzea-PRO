package br.edu.cefsa.faculdade.campeonato.repository;


import br.edu.cefsa.faculdade.campeonato.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
}