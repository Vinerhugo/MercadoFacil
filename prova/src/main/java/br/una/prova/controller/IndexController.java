package br.una.prova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;;


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
	@RequestMapping("/vfolheto")
	public String govfolheto() {
		return "vfolheto";
	}
	
	@RequestMapping("/vpromocao")
	public String govpromocao() {
		return "vpromocao";
	}
	
}




