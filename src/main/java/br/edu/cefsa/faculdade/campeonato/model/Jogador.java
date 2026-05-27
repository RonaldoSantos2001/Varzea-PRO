package br.edu.cefsa.faculdade.campeonato.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do atleta é obrigatório.")
    private String nome;
    
    @NotBlank(message = "A posição não pode ficar em branco.")
    private String posicao;

    @NotNull(message = "O atleta deve ser vinculado a um time.")
    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}