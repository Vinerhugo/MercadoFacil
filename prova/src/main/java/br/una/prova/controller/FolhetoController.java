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

import br.una.prova.entity.Folheto;
import br.una.prova.repository.FolhetoRepository;

@Controller
@RequestMapping("/folheto")
public class FolhetoController {

	private FolhetoRepository folhetoRepository;
	

	//Os upload são salvos neste caminho
    private static String UPLOADED_FOLDER = "C://Users//viner.araujo//Desktop//Facul//AS1-master//AS1-master//AS1-master//src//main//resources//static//img//folheto//";
    
	List<String> files = new ArrayList<String>();


	public FolhetoController(FolhetoRepository folhetoRepository) {
		this.folhetoRepository = folhetoRepository;
	}

	@GetMapping
	public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("folhetos", folhetoRepository.findAll(pageable));
		model.addAttribute("files",
				files.stream()
						.map(fileName -> MvcUriComponentsBuilder
								.fromMethodName(FolhetoController.class, "getFile", fileName).build().toString())
						.collect(Collectors.toList()));
		model.addAttribute("totalFiles", "TotalFiles: " + files.size());

		return "folheto/listar";
	}

	@GetMapping("/editar")
	public String edit(Model model, @RequestParam Integer id) {
		model.addAttribute("folheto", folhetoRepository.findOne(id));
		return "folheto/formulario";
	}

	@GetMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("folheto", new Folheto());
		return "folheto/formulario";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Folheto folheto, BindingResult bindingResult, @RequestParam("file") MultipartFile file,
			Model model, RedirectAttributes redirectAttributes){

		if (file.isEmpty() && bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Por Favor Selecione um Arquivo");
			return "folheto/formulario";
		}

		try {
		
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);       
            folheto.setCaminhofolheto(file.getOriginalFilename());
		} catch (Exception e) {

			model.addAttribute("message", "Falha No upload " + file.getOriginalFilename() + "!");
		}

		folhetoRepository.save(folheto);
		return "redirect:/folheto";
	}

	@GetMapping("/excluir")
	public String excluir(Model model, @RequestParam Integer id, RedirectAttributes redirectAttributes) {
		try {
			folhetoRepository.delete(id);

		} catch (Exception e) {
			return "redirect:/erro";
		}

		return "redirect:/folheto";
	}

	@GetMapping("/buscar")
	public String buscar(Model model, @RequestParam String texto,
			@PageableDefault(size = 5) Pageable pageable) {
		
		return "folheto/listar";

	}
	
}
