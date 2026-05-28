package br.edu.cefsa.faculdade.campeonato.controller;

import br.edu.cefsa.faculdade.campeonato.model.Partida;
import br.edu.cefsa.faculdade.campeonato.service.CampeonatoService;
import br.edu.cefsa.faculdade.campeonato.service.PartidaService;
import br.edu.cefsa.faculdade.campeonato.service.TimeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/partidas")
public class PartidaController {

    private final PartidaService partidaService;
    private final TimeService timeService;
    private final CampeonatoService campeonatoService;

    // O construtor injeta os serviços corretamente
    public PartidaController(PartidaService partidaService, TimeService timeService, CampeonatoService campeonatoService) {
        this.partidaService = partidaService;
        this.timeService = timeService;
        this.campeonatoService = campeonatoService;
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("partida", new Partida());
        model.addAttribute("listaCampeonatos", campeonatoService.listarTodos());
        model.addAttribute("listaTimes", timeService.listarTodos());
        return "partida-form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Partida partida, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("listaCampeonatos", campeonatoService.listarTodos());
            model.addAttribute("listaTimes", timeService.listarTodos());
            return "partida-form";
        }
        
        try {
            partidaService.salvar(partida);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erroRegraNegocio", e.getMessage());
            model.addAttribute("listaCampeonatos", campeonatoService.listarTodos());
            model.addAttribute("listaTimes", timeService.listarTodos());
            return "partida-form";
        }
        
        return "redirect:/partidas/listar";
    }

    @GetMapping("/listar")
    public String listarPartidas(Model model) {
        model.addAttribute("partidas", partidaService.listarTodos());
        return "partida-list";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Partida partida = partidaService.buscarPorId(id); 
        model.addAttribute("partida", partida);
        
        // Usamos os serviços para carregar os selects
        model.addAttribute("listaCampeonatos", campeonatoService.listarTodos());
        model.addAttribute("listaTimes", timeService.listarTodos());
        
        return "partida-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            partidaService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Partida excluída com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir a partida.");
        }
        return "redirect:/partidas/listar";
    }
}