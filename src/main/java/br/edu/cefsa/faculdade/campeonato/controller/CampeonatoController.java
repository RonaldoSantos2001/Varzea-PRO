package br.edu.cefsa.faculdade.campeonato.controller;


import br.edu.cefsa.faculdade.campeonato.model.Campeonato;
import br.edu.cefsa.faculdade.campeonato.service.CampeonatoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/campeonatos")
public class CampeonatoController {

    private final CampeonatoService campeonatoService;

    // Injeção de dependência via construtor (Boa prática exigida no mercado)
    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    // Rota para abrir a tela de cadastro
    @GetMapping("/novo")
    public String novo(Model model) {
    // É crucial enviar um novo objeto para o Thymeleaf preencher o formulário
         model.addAttribute("campeonato", new Campeonato());
        return "campeonato-form"; 
    }

    // Rota para processar o botão "Salvar" do formulário
    @PostMapping("/salvar")
    public String salvar(@Valid Campeonato campeonato, BindingResult result) {
        if (result.hasErrors()) {
            return "campeonato-form";
        }
        campeonatoService.salvar(campeonato);
        return "redirect:/campeonatos/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("campeonato", campeonatoService.buscarPorId(id));
        return "campeonato-form";
    }
    
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("campeonatos", campeonatoService.listarTodos());
        return "campeonato-list";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
        campeonatoService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Excluído com sucesso!");
        } catch (Exception e) {
        // ISSO VAI MOSTRAR O ERRO NO CONSOLE DO NETBEANS
        e.printStackTrace(); 
        redirectAttributes.addFlashAttribute("erro", "Erro: Este torneio possui partidas cadastradas!");
        }
        return "redirect:/campeonatos/listar";
        }
}