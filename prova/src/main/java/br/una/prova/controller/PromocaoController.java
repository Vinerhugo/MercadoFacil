package br.una.prova.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.una.prova.entity.Promocao;
import br.una.prova.repository.PromocaoRepository;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {

	private PromocaoRepository promocaoRepository;
	

	//Os upload são salvos neste caminho
    private static String UPLOADED_FOLDER = "C://Users//viner.araujo//Desktop//Facul//AS1-master//AS1-master//AS1-master//src//main//resources//static//img//promocao//";
    
	List<String> files = new ArrayList<String>();


	public PromocaoController(PromocaoRepository promocaoRepository) {
		this.promocaoRepository = promocaoRepository;
	}

	@GetMapping
	public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("promocoes", promocaoRepository.findAll(pageable));
		model.addAttribute("files",
				files.stream()
						.map(fileName -> MvcUriComponentsBuilder
								.fromMethodName(PromocaoController.class, "getFile", fileName).build().toString())
						.collect(Collectors.toList()));
		model.addAttribute("totalFiles", "TotalFiles: " + files.size());

		return "promocao/listar";
	}

	@GetMapping("/editar")
	public String edit(Model model, @RequestParam Integer id) {
		model.addAttribute("promocao", promocaoRepository.findOne(id));
		return "promocao/formulario";
	}

	@GetMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("promocao", new Promocao());
		return "promocao/formulario";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Promocao promocao, BindingResult bindingResult, @RequestParam("file") MultipartFile file,
			Model model, RedirectAttributes redirectAttributes){

		if (file.isEmpty() && bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Por Favor Selecione um Arquivo");
			return "promocao/formulario";
		}

		try {
		
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);       
            promocao.setCaminhopromocao(file.getOriginalFilename());
		} catch (Exception e) {

			model.addAttribute("message", "Falha No upload " + file.getOriginalFilename() + "!");
		}

		promocaoRepository.save(promocao);
		return "redirect:/promocao";
	}

	@GetMapping("/excluir")
	public String excluir(Model model, @RequestParam Integer id, RedirectAttributes redirectAttributes) {
		try {
			promocaoRepository.delete(id);

		} catch (Exception e) {
			return "redirect:/erro";
		}

		return "redirect:/promocao";
	}

	@GetMapping("/buscar")
	public String buscar(Model model, @RequestParam String texto,
			@PageableDefault(size = 5) Pageable pageable) {
		
			model.addAttribute("promocoes", promocaoRepository.findBynomepromocaoLike("%" + texto + "%", pageable));
		
		return "promocao/listar";

	}

	@GetMapping("/listfiles")
	public String getListFiles(Model model) {
		model.addAttribute("file",
				files.stream()
						.map(fileName -> MvcUriComponentsBuilder
								.fromMethodName(PromocaoController.class, "getFile", fileName).build().toString())
						.collect(Collectors.toList()));
		return "promocao/listar";
	}

	
	
	
}
