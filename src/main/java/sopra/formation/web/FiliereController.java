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

import sopra.formation.dao.ICoursDao;
import sopra.formation.dao.ICursusDao;
import sopra.formation.dao.IFiliereDao;
import sopra.formation.dao.ISalleDao;
import sopra.formation.dao.IUtilisateurDao;
import sopra.formation.model.Filiere;

@Controller
@RequestMapping("/filiere")
public class FiliereController {

	@Autowired
	private ICoursDao coursDao;
	@Autowired
	private ICursusDao cursusDao;
	@Autowired
	private IUtilisateurDao utilisateurDao;
	@Autowired
	private IFiliereDao filiereDao;
	@Autowired
	private ISalleDao salleDao;
	
	@GetMapping("")
	public String list(Model model) {
		List<Filiere> filiere = filiereDao.findAll();

		model.addAttribute("mesFiliere", filiere);

		return "filiere/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("filiere", new Filiere());

		model.addAttribute("cursus", cursusDao.findAll());
		
		model.addAttribute("formateurs", utilisateurDao.findAll());
		
		model.addAttribute("cours", coursDao.findAll());
		
		model.addAttribute("salle", salleDao.findAll());
		
		return "filiere/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Optional<Filiere> optFiliere = filiereDao.findById(id);

		if (optFiliere.isPresent()) {
			model.addAttribute("filiere", optFiliere.get());
		}
		
		model.addAttribute("cursus", cursusDao.findAll());
		
		model.addAttribute("formateurs", utilisateurDao.findAll());
		
		model.addAttribute("cours", coursDao.findAll());
		
		model.addAttribute("salle", salleDao.findAll());

		return "filiere/form";
	}

	
	@PostMapping("/save")
	public String saveBis(@ModelAttribute("filiere") @Valid Filiere filiere, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "filiere/form";
		}
		
		filiereDao.save(filiere);

		return "redirect:/filiere";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/filiere";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		filiereDao.deleteById(id);

		return "redirect:/filiere";
	}
}
