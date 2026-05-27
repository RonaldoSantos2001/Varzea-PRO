package br.edu.cefsa.faculdade.campeonato.repository;

import br.edu.cefsa.faculdade.campeonato.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// O JpaRepository<QualClasse, TipoDaChavePrimaria> já traz o CRUD inteiro pronto
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // O Spring entende o idioma inglês. Só de criar esse método, 
    // ele já sabe que deve fazer um "SELECT * FROM Usuario WHERE username = ?"
    Optional<Usuario> findByUsername(String username);
}