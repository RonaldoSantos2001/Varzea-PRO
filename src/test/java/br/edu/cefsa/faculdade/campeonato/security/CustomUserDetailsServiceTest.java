package br.edu.cefsa.faculdade.campeonato.security;


import br.edu.cefsa.faculdade.campeonato.model.Usuario;
import br.edu.cefsa.faculdade.campeonato.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Habilita o uso do Mockito junto com o JUnit 5
@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    // 1. Uso de @Mock para simular dependências (O Repository "falso") [cite: 267]
    @Mock
    private UsuarioRepository repository;

    // 2. Uso de @InjectMocks na classe que vamos testar (O Service real) [cite: 267]
    @InjectMocks
    private CustomUserDetailsService service;

    private Usuario usuarioMock;

    @BeforeEach
    void setup() {
        // Preparamos um usuário fictício para usar nos testes
        usuarioMock = new Usuario();
        usuarioMock.setUsername("treinador");
        usuarioMock.setPassword("senhaCriptografada");
        usuarioMock.setRole("USER");
    }

    @Test
    @DisplayName("Deve carregar o usuário corretamente quando ele existir no banco")
    void deveRetornarUserDetailsQuandoUsuarioExiste() {
        // 3. Definir o comportamento esperado com when().thenReturn() [cite: 268]
        // Regra: "Quando chamarem o findByUsername passando 'treinador', devolva o nosso usuarioMock"
        when(repository.findByUsername("treinador")).thenReturn(Optional.of(usuarioMock));

        // Execução
        UserDetails resultado = service.loadUserByUsername("treinador");

        // 4. Assertivas garantem o resultado correto [cite: 270]
        assertNotNull(resultado, "O UserDetails não deve ser nulo");
        assertEquals("treinador", resultado.getUsername(), "O nome de usuário deve bater");
        assertEquals("senhaCriptografada", resultado.getPassword(), "A senha deve ser trazida do banco");

        // 5. Verificar chamadas com verify() [cite: 269]
        // Garante que a classe realmente usou o repositório para buscar o dado
        verify(repository, times(1)).findByUsername("treinador");
    }

    @Test
    @DisplayName("Deve lançar UsernameNotFoundException quando o usuário não existir")
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        // Regra: "Quando chamarem 'desconhecido', devolva vazio (Optional.empty)"
        when(repository.findByUsername("desconhecido")).thenReturn(Optional.empty());

        // Valida se a exceção específica é lançada [cite: 169]
        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("desconhecido");
        }, "Deveria ter lançado erro de usuário não encontrado");
        
        verify(repository, times(1)).findByUsername("desconhecido");
    }
}