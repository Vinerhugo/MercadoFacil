
package br.una.prova.controller;

import br.una.prova.entity.Produto;
import br.una.prova.repository.ProdutoRepository;
import net.sf.jasperreports.engine.PrintPageFormat;

import org.hibernate.result.Output;
import org.hsqldb.HsqlException;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.Printer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.swing.text.html.HTML;
import javax.validation.Valid;

import java.awt.print.PrinterException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.io.File;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	private ProdutoRepository produtoRepository;

	//Os upload são salvos neste caminho
    private static String UPLOADED_FOLDER = "C://Users//viner.araujo//Desktop//Facul//AS1-master//AS1-master//AS1-master//src//main//resources//static//img//upload//";
    
	List<String> files = new ArrayList<String>();

	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@GetMapping
	public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("produtos", produtoRepository.findAll(pageable));
		model.addAttribute("files",
				files.stream()
						.map(fileName -> MvcUriComponentsBuilder
								.fromMethodName(ProdutoController.class, "getFile", fileName).build().toString())
						.collect(Collectors.toList()));
		model.addAttribute("totalFiles", "TotalFiles: " + files.size());

		return "produto/listar";
	}

	@GetMapping("/editar")
	public String edit(Model model, @RequestParam Integer id) {
		model.addAttribute("produto", produtoRepository.findOne(id));
		return "produto/formulario";
	}

	@GetMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("produto", new Produto());
		return "produto/formulario";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Produto produto, BindingResult bindingResult, @RequestParam("file") MultipartFile file,
			Model model, RedirectAttributes redirectAttributes){

		if (file.isEmpty() && bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Por Favor Selecione um Arquivo");
			return "produto/formulario";
		}

		try {
		
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);       
            produto.setCaminhoimg(file.getOriginalFilename());
		} catch (Exception e) {

			model.addAttribute("message", "Falha No upload " + file.getOriginalFilename() + "!");
		}

		produtoRepository.save(produto);
		return "redirect:/produto";
	}

	@GetMapping("/excluir")
	public String excluir(Model model, @RequestParam Integer id, RedirectAttributes redirectAttributes) {
		try {
			produtoRepository.delete(id);

		} catch (Exception e) {
			return "redirect:/erro";
		}

		return "redirect:/produto";
	}

	@GetMapping("/buscar")
	public String buscar(Model model, @RequestParam String texto, @RequestParam String opcao,
			@PageableDefault(size = 5) Pageable pageable) {
		if (opcao.equals("nomeprod"))
			model.addAttribute("produtos", produtoRepository.findBynomeprodLike("%" + texto + "%", pageable));
		else if (opcao.equals("codbarra"))
			model.addAttribute("produtos", produtoRepository.findBycodbarraLike(Integer.parseInt(texto), pageable));
		return "produto/listar";

	}

	@GetMapping("/listfiles")
	public String getListFiles(Model model) {
		model.addAttribute("file",
				files.stream()
						.map(fileName -> MvcUriComponentsBuilder
								.fromMethodName(ProdutoController.class, "getFile", fileName).build().toString())
						.collect(Collectors.toList()));
		return "produto/listar";
	}

	
}
