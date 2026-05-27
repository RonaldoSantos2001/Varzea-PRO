package br.edu.cefsa.faculdade.campeonato.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

@Entity
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do torneio é obrigatório.")
    private String nome;
    private Integer ano;
    private String formato;
    private String status = "ABERTO";

    // 1 Campeonato tem Muitos Times
    @OneToMany(mappedBy = "campeonato")
    private List<Time> times = new ArrayList<>();

    // 1 Campeonato tem Muitas Partidas
    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partida> partidas;

    public Campeonato() {
    }

    public void inscreverTime(Time time) {
        times.add(time);
        time.setCampeonato(this);
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Time> getTimes() { return times; }
    public void setTimes(List<Time> times) { this.times = times; }

    public List<Partida> getPartidas() { return partidas; }
    public void setPartidas(List<Partida> partidas) { this.partidas = partidas; }
}