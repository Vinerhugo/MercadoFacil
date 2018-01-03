package br.una.prova.controller;

import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.una.prova.repository.PromocaoRepository;;


@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/contato")
    public String goContato(){
        return "contato";
    }
    
    @RequestMapping("/ajuda")
    public String goAjuda(){
        return "ajuda";
    }
    
    @RequestMapping("/erro")
	public String goErro(){
		return "erro";
	}
	@RequestMapping("/login")
	public String goLogin() {
		return "login";
	}
	
}




