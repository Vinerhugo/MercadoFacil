package br.una.prova.controller;

import br.una.prova.entity.Carrinho;
import br.una.prova.repository.UsuarioRepository;
import br.una.prova.repository.ProdutoRepository;
import br.una.prova.repository.CarrinhoRepository;

import org.hsqldb.HsqlException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Properties;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
    private CarrinhoRepository carrinhoRepository;
    private ProdutoRepository produtoRepository;
    private UsuarioRepository usuarioRepository;

    public CarrinhoController(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("carrinhos", carrinhoRepository.findAll(pageable));
        return "carrinho/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam Integer id) {
        model.addAttribute("carrinho", carrinhoRepository.findOne(id));
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "carrinho/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("carrinho", new Carrinho());
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "carrinho/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Carrinho carrinho, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "carrinho/formulario";
        }
        carrinhoRepository.save(carrinho);
        return "redirect:/carrinho";
    }
   
    @GetMapping("/excluir")
    public String excluir(Model model,@RequestParam Integer id) {
    	carrinhoRepository.delete(id);	 
    	return "redirect:/carrinho";
    }
    
    @GetMapping("/buscar")
    public String buscar(Model model,@RequestParam String texto, @PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("carrinhos", carrinhoRepository.findBynomecarrinhoLike("%" + texto + "%", pageable));
        	
		return "carrinho/listar";
    }

    @RequestMapping(value = "/relatorio", method = RequestMethod.GET)
    public String relatorio(Model model, @RequestParam(defaultValue = "pdf") String format, HttpServletResponse response) {
        model.addAttribute("datasource", carrinhoRepository.findAll());
        model.addAttribute("format", format);
        return "reports/carrinhos";
    }
 
    
}
