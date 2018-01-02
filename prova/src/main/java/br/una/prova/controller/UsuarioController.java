package br.una.prova.controller;

import br.una.prova.entity.Usuario;
import br.una.prova.repository.UsuarioRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Properties;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("usuarios", usuarioRepository.findAll(pageable));
        return "usuario/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam Integer id) {
        model.addAttribute("usuario", usuarioRepository.findOne(id));
        return "usuario/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "usuario/formulario";
        }
        usuarioRepository.save(usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam Integer id,RedirectAttributes redirectAttributes) {
    	try{
    		usuarioRepository.delete(id);	
    	}
    	catch(Exception e){		
    		 return "redirect:/erro";
    	}
			return "redirect:/usuario";		
    }
    
    @GetMapping("/buscar")
    public String buscar(Model model,@RequestParam String nomeuser, @PageableDefault(size = 5) Pageable pageable) {
    model.addAttribute("usuarios", usuarioRepository.findBynomeuserLike("%" + nomeuser + "%", pageable));
            	
    		return "usuario/listar";
        
    }
}