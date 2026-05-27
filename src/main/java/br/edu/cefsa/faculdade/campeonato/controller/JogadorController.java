package br.edu.cefsa.faculdade.campeonato.controller;

import br.edu.cefsa.faculdade.campeonato.model.Jogador;
import br.edu.cefsa.faculdade.campeonato.service.JogadorService;
import br.edu.cefsa.faculdade.campeonato.service.TimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;
    private final TimeService timeService;

    public JogadorController(JogadorService jogadorService, TimeService timeService) {
        this.jogadorService = jogadorService;
        this.timeService = timeService;
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("jogador", new Jogador());
        model.addAttribute("listaTimes", timeService.listarTodos());
        return "jogador-form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Jogador jogador, BindingResult result, Model model) {
        // Se a validação falhar, recarrega o formulário com as mensagens de erro
        if (result.hasErrors()) {
            model.addAttribute("listaTimes", timeService.listarTodos());
            return "jogador-form";
        }
        
        jogadorService.salvar(jogador);
        return "redirect:/jogadores/listar"; 
    }

    @GetMapping("/listar")
    public String listarJogadores(Model model) {
        model.addAttribute("jogadores", jogadorService.listarTodos());
        return "jogador-list";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        jogadorService.excluir(id);
        return "redirect:/jogadores/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        // Busca o jogador existente no banco
        model.addAttribute("jogador", jogadorService.buscarPorId(id));
        // Carrega a lista de times para o <select>
        model.addAttribute("listaTimes", timeService.listarTodos());
        return "jogador-form"; 
    }
}