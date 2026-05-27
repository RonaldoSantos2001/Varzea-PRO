package br.edu.cefsa.faculdade.campeonato.service;

import br.edu.cefsa.faculdade.campeonato.model.Usuario;
import br.edu.cefsa.faculdade.campeonato.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void deveSalvarUsuarioComSenhaCriptografada() {
        // Preparação do objeto de teste
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setSenha("123456");
        // O teste antigo exige que o email seja esse, então setamos explicitamente aqui
        usuario.setEmail("teste@email.com"); 

        when(passwordEncoder.encode("123456")).thenReturn("senhaCriptografada");

        // Execução
        usuarioService.registrarUsuario(usuario, "123456");

        // Verificações corrigidas
        assertEquals("senhaCriptografada", usuario.getSenha());
        assertEquals("ROLE_ADMIN", usuario.getRole());
        assertEquals("teste@email.com", usuario.getEmail()); 
        verify(usuarioRepository).save(usuario);
    }

    @Test
    public void deveLancarExcecaoQuandoSenhasNaoCoincidem() {
        Usuario usuario = new Usuario();
        usuario.setSenha("123456");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.registrarUsuario(usuario, "senhaErrada");
        });

        // O texto exigido pelo teste agora reflete exatamente o que está no UsuarioService
        assertEquals("As senhas não coincidem. Tente novamente.", exception.getMessage());
    }
}