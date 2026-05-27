package br.edu.cefsa.faculdade.campeonato.security;

import br.edu.cefsa.faculdade.campeonato.model.Usuario;
import br.edu.cefsa.faculdade.campeonato.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha inválidos."));

        // Retorna a nossa classe customizada que leva o primeiro nome para a tela
        return new CustomUserDetails(usuario);
    }

    // --- CLASSE INTERNA CUSTOMIZADA ---
    // Extende o User padrão do Spring Security para adicionar a variável do primeiro nome
    public static class CustomUserDetails extends User {
        private final String primeiroNome;

        public CustomUserDetails(Usuario usuario) {
            super(
                    usuario.getUsername(),
                    usuario.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(usuario.getRole()))
            );
            
            // Lógica para pegar apenas o primeiro nome (Ex: "Ronaldo de Oliveira" -> "Ronaldo")
            String nome = usuario.getNomeCompleto();
            if (nome != null && nome.contains(" ")) {
                this.primeiroNome = nome.split(" ")[0];
            } else {
                this.primeiroNome = nome;
            }
        }

        public String getPrimeiroNome() {
            return primeiroNome;
        }
    }
}