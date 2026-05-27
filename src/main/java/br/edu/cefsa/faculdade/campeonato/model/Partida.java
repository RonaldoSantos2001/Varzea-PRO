package br.edu.cefsa.faculdade.campeonato.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Selecione o torneio.")
    @ManyToOne
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;

    @NotNull(message = "Selecione a equipe mandante.")
    @ManyToOne
    @JoinColumn(name = "time_mandante_id")
    private Time timeMandante;

    @NotNull(message = "Selecione a equipe visitante.")
    @ManyToOne
    @JoinColumn(name = "time_visitante_id")
    private Time timeVisitante;

    @NotNull(message = "A data e hora do jogo são obrigatórias.")
    private LocalDateTime dataHora;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Time getTimeMandante() {
        return timeMandante;
    }

    public void setTimeMandante(Time timeMandante) {
        this.timeMandante = timeMandante;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}