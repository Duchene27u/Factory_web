package sopra.formation.web;

import java.time.LocalDate;
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
import sopra.formation.model.Formateur;
import sopra.formation.model.Utilisateur;


@Controller
@RequestMapping("/formateur")
public class FormateurController {
	
	@Autowired
	private IUtilisateurDao personneDao;
	
	@GetMapping("")
	public String list(Model model) {
		List<Formateur> formateurs = personneDao.findAllFormateur();

		model.addAttribute("formateurs", formateurs);

		return "formateur/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		Formateur formateur = new Formateur();
		formateur.setDroit(Droit.FORMATEUR);
		model.addAttribute("formateur", formateur);
		
		return "formateur/form";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {
		Optional<Utilisateur> formateur = personneDao.findById(id);
		
		if (formateur.isPresent()) {
			formateur.get().setDroit(Droit.FORMATEUR);
			model.addAttribute("formateur",(Formateur) formateur.get());
			
		}
		
		return "formateur/form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("formateur") Formateur formateur) {
		
		personneDao.save(formateur);

		return "redirect:/formateur";
	}	
	
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/formateur";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		personneDao.deleteById(id);

		return "redirect:/formateur";
	}
}
