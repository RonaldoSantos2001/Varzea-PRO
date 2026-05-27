package br.edu.cefsa.faculdade.campeonato.service;

import br.edu.cefsa.faculdade.campeonato.model.Time;
import br.edu.cefsa.faculdade.campeonato.repository.TimeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TimeServiceTest {

    @Mock
    private TimeRepository timeRepository;

    @InjectMocks
    private TimeService timeService;

    @Test
    public void deveSalvarEquipeComSucesso() {
        // 1. Cenario
        Time time = new Time();
        time.setNome("Varzea FC");

        // Simula o retorno do banco de dados
        when(timeRepository.save(time)).thenReturn(time);

        // 2. Execucao
        Time salvo = timeService.salvar(time);

        // 3. Validacao
        assertNotNull(salvo);
        assertEquals("Varzea FC", salvo.getNome());
        
        // Verifica se o metodo save foi chamado exatamente uma vez
        verify(timeRepository, times(1)).save(time);
    }

    @Test
    public void deveExcluirEquipePorId() {
        // 1. Cenario
        Long idAlvo = 1L;

        // 2. Execucao
        timeService.excluir(idAlvo);

        // 3. Validacao
        verify(timeRepository, times(1)).deleteById(idAlvo);
    }
}