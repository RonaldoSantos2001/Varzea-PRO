package br.edu.cefsa.faculdade.campeonato.service;

import br.edu.cefsa.faculdade.campeonato.model.Usuario;
import br.edu.cefsa.faculdade.campeonato.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // O PasswordEncoder configurado lá no SecurityConfig é injetado aqui
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrarUsuario(Usuario usuario, String confirmaSenha) {
        // 1. Validação de Senha
        if (!usuario.getSenha().equals(confirmaSenha)) {
            throw new IllegalArgumentException("As senhas não coincidem. Tente novamente.");
        }

        // 2. Validação de Usuário Duplicado (Opcional, mas muito recomendado)
        // Se você não tiver esse método no Repository, pode remover este bloco if
        /*
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Este nome de usuário já está em uso.");
        }
        */

        // 3. CRUCIAL: Criptografar a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        // 4. Atribuir a Role padrão para que o Spring Security libere o acesso
        usuario.setRole("ROLE_ADMIN");

        // 5. Salvar no banco
        usuarioRepository.save(usuario);
    }
}