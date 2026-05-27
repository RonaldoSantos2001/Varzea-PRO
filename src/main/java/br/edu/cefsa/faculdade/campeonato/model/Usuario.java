package br.edu.cefsa.faculdade.campeonato.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos mapeados no HTML (Thymeleaf)
    private String nomeCompleto;
    private String username;
    private String senha;
    
    // Atributos exigidos pelo Backend (Services e Security)
    private String role;
    private String email;

    public Usuario() {
    }

    // --- GETTERS E SETTERS ---
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // --- MÉTODOS PONTE PARA O SPRING SECURITY E SERVICES ---
    // Eles permitem que o backend chame getPassword() sem dar erro, lendo a variável senha
    
    public String getPassword() {
        return this.senha;
    }

    public void setPassword(String password) {
        this.senha = password;
    }

    // --- GETTERS E SETTERS FALTANTES ---

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}