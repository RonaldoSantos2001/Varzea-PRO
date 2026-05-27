package br.edu.cefsa.faculdade.campeonato.controller;


import br.edu.cefsa.faculdade.campeonato.service.CampeonatoService;
import br.edu.cefsa.faculdade.campeonato.service.JogadorService;
import br.edu.cefsa.faculdade.campeonato.service.PartidaService;
import br.edu.cefsa.faculdade.campeonato.service.TimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final TimeService timeService;
    private final JogadorService jogadorService;
    private final PartidaService partidaService;
    private final CampeonatoService campeonatoService;

    public HomeController(TimeService timeService, JogadorService jogadorService, PartidaService partidaService, CampeonatoService campeonatoService) {
        this.timeService = timeService;
        this.jogadorService = jogadorService;
        this.partidaService = partidaService;
        this.campeonatoService = campeonatoService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        // Conta a quantidade de registros cadastrados no sistema
        model.addAttribute("totalTimes", timeService.listarTodos().size());
        model.addAttribute("totalJogadores", jogadorService.listarTodos().size());
        model.addAttribute("totalPartidas", partidaService.listarTodos().size());
        model.addAttribute("totalCampeonatos", campeonatoService.listarTodos().size());
        
        return "index"; // Renderiza a tela de dashboard
    }
}