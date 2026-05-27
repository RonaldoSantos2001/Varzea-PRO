package br.edu.cefsa.faculdade.campeonato.config;

import br.edu.cefsa.faculdade.campeonato.model.Usuario;
import br.edu.cefsa.faculdade.campeonato.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    // 1. As variáveis precisam ser declaradas aqui
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // 2. O Spring injeta as dependências através do construtor
    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // 3. Agora o Java reconhece o usuarioRepository e o passwordEncoder
        if (usuarioRepository.findByUsername("adm").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("adm");
            admin.setPassword(passwordEncoder.encode("123456")); // Mude se usar outra senha
            admin.setRole("ADMIN");
            admin.setNomeCompleto("ADM"); 
            admin.setEmail("admin@admin.com");
            
            usuarioRepository.save(admin);
        }
    }
}