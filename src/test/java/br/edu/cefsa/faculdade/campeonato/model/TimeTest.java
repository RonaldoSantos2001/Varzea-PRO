package br.edu.cefsa.faculdade.campeonato.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    @Test
    public void deveAtribuirERecuperarNomeDoTime() {
        Time time = new Time();
        time.setNome("Juventus");
        
        assertEquals("Juventus", time.getNome());
    }
}   