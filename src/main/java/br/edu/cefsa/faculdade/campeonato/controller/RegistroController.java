package br.edu.cefsa.faculdade.campeonato.controller;

import br.edu.cefsa.faculdade.campeonato.model.Usuario;
import br.edu.cefsa.faculdade.campeonato.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String exibirFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro/salvar")
    public String salvarUsuario(@Valid Usuario usuario, BindingResult result, String confirmaSenha, Model model) {
        // 1. Necessário para o th:if="${#fields.hasAnyErrors()}" do HTML funcionar sem quebrar
        if (result.hasErrors()) {
            return "registro";
        }

        try {
            // 2. Executa a regra de validação de senhas do Service
            usuarioService.registrarUsuario(usuario, confirmaSenha);
            return "redirect:/login?sucesso";
            
        } catch (IllegalArgumentException e) {
            // Captura erros de negócio previstos (ex: senhas não batem)
            model.addAttribute("erro", e.getMessage());
            return "registro";
        } catch (Exception e) {
            // Captura qualquer outro erro imprevisto (banco de dados, SQL, etc.) e joga na tela em vez de dar Whitelabel
            model.addAttribute("erro", "Erro interno no servidor: " + e.getMessage());
            return "registro";
        }
    }
}