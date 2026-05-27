package br.edu.cefsa.faculdade.campeonato.service;

import br.edu.cefsa.faculdade.campeonato.model.Partida;
import br.edu.cefsa.faculdade.campeonato.model.Time;
import br.edu.cefsa.faculdade.campeonato.repository.PartidaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PartidaServiceTest {

    @Mock
    private PartidaRepository partidaRepository;

    @InjectMocks
    private PartidaService partidaService;

    @Test
    public void deveLancarExcecaoQuandoTimesForemIguais() {
        // 1. Cenário (Arrange)
        Time timeA = new Time();
        timeA.setId(1L);

        Partida partida = new Partida();
        partida.setTimeMandante(timeA);
        partida.setTimeVisitante(timeA); // Mesmo ID, deve falhar

        // 2. Execução e Validação (Act & Assert)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            partidaService.salvar(partida);
        });

        // Validação ignorando acentos
        assertTrue(exception.getMessage().contains("contra si mesma"));
        
        // Verifica se o repositório foi impedido de salvar no banco
        verify(partidaRepository, never()).save(any(Partida.class));
    }

    @Test
    public void deveSalvarPartidaQuandoTimesForemDiferentes() {
        // 1. Cenário (Arrange)
        Time timeA = new Time();
        timeA.setId(1L);
        
        Time timeB = new Time();
        timeB.setId(2L);

        Partida partida = new Partida();
        partida.setTimeMandante(timeA);
        partida.setTimeVisitante(timeB); // IDs diferentes, deve passar

        // Simula o comportamento do repositório
        when(partidaRepository.save(partida)).thenReturn(partida);

        // 2. Execução (Act)
        Partida partidaSalva = partidaService.salvar(partida);

        // 3. Validação (Assert)
        assertNotNull(partidaSalva);
        verify(partidaRepository, times(1)).save(partida); // Verifica se o save() foi chamado exatamente 1 vez
    }
}