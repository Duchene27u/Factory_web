package sopra.formation.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.dao.IUtilisateurDao;
import sopra.formation.model.Droit;
import sopra.formation.model.Stagiaire;


@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {
	
	@Autowired
	private IUtilisateurDao utilisateurDao;
	
	
	@GetMapping("")
	public String list(Model model) {
		List<Stagiaire> stagiaires = utilisateurDao.findAllStagiaires();

		model.addAttribute("stagiaires", stagiaires);

		return "stagiaire/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		
		model.addAttribute("stagiaire", new Stagiaire());
		model.addAttribute("droits", Droit.values());

		return "stagiaire/form";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Optional<Stagiaire> optStagiaire = utilisateurDao.findStagiaireById(id);

		if (optStagiaire.isPresent()) {
			model.addAttribute("stagiaire", optStagiaire.get());
		}
		
		model.addAttribute("droits", Droit.values());
		
		return "stagiaire/form";
	}
	
		
	@PostMapping("/save")
	public String save(@ModelAttribute("stagiaire") @Valid Stagiaire stagiaire, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "stagiaire/form";
		}
		
		utilisateurDao.save(stagiaire);

		return "redirect:/stagiaire";
	}

	
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/stagiaire";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		utilisateurDao.deleteById(id);

		return "redirect:/stagiaire";
	}
}
