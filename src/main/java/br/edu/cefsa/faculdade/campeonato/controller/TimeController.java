package br.edu.cefsa.faculdade.campeonato.controller;

import br.edu.cefsa.faculdade.campeonato.model.Time;
import br.edu.cefsa.faculdade.campeonato.service.TimeService;
import br.edu.cefsa.faculdade.campeonato.service.CampeonatoService; // Import necessário
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;
    private final CampeonatoService campeonatoService; // Declarado aqui

    // Injeção de dependência via construtor (mais seguro que @Autowired)
    public TimeController(TimeService timeService, CampeonatoService campeonatoService) {
        this.timeService = timeService;
        this.campeonatoService = campeonatoService;
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("time", new Time());
        // Uso do serviço para listar campeonatos
        model.addAttribute("listaCampeonatos", campeonatoService.listarTodos());
        return "time-form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Time time, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Em caso de erro, recarregamos a lista para o select não sumir
            model.addAttribute("listaCampeonatos", campeonatoService.listarTodos());
            return "time-form";
        }
        timeService.salvar(time);
        return "redirect:/times/listar"; 
    }

    @GetMapping("/listar")
    public String listarTimes(Model model) {
        model.addAttribute("times", timeService.listarTodos());
        return "time-list";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Time time = timeService.buscarPorId(id);
        model.addAttribute("time", time);
        
        model.addAttribute("listaCampeonatos", campeonatoService.listarTodos());
        
        return "time-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            timeService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Equipe excluída com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir equipe.");
        }
        return "redirect:/times/listar";
    }
}