package br.edu.cefsa.faculdade.campeonato.model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class CampeonatoTest {

    private Campeonato campeonato;
    private Time time;

    @BeforeEach
    void setup() {
        campeonato = new Campeonato();
        campeonato.setNome("Paulist�o 2026");
        
        time = new Time();
        time.setNome("S�o Bernardo FC");
    }

    @Test
    @DisplayName("Deve inscrever um time no campeonato e garantir a via de m�o dupla")
    void deveInscreverTimeNoCampeonato() {
        // Execução da regra de negócio definida no Model
        campeonato.inscreverTime(time);

        // 1. Verifica se o time foi adicionado a lista do campeonato
        assertTrue(campeonato.getTimes().contains(time), 
                "O campeonato deveria conter o time inscrito.");

        // 2. Verifica se o time agora 'sabe' a qual campeonato pertence
        // Isso garante que a chave estrangeira será salva corretamente no H2
        assertNotNull(time.getCampeonato(), 
                "O time deveria ter uma refer�ncia ao campeonato.");
        
        assertEquals(campeonato, time.getCampeonato(), 
                "O campeonato vinculado ao time deve ser o mesmo que o inscreveu.");
    }
}