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

import sopra.formation.dao.ICursusDao;
import sopra.formation.dao.IFiliereDao;
import sopra.formation.dao.IUtilisateurDao;
import sopra.formation.model.Cursus;
import sopra.formation.model.Filiere;
import sopra.formation.model.Stagiaire;


@Controller
@RequestMapping("/cursus")
public class CursusController {
	
	@Autowired
	private ICursusDao cursusDao;
	
	@Autowired
	private IUtilisateurDao utilisateurDao;
	
	@Autowired
	private IFiliereDao filiereDao;

	
	@GetMapping("")
	public String list(Model model) {
		List<Cursus> cursus = cursusDao.findAll();

		model.addAttribute("cursus", cursus);

		return "cursus/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		
		List<Stagiaire> stagiaires = utilisateurDao.findAllStagiaires();
		List<Filiere> filieres = filiereDao.findAll();
		
		model.addAttribute("stagiaires", stagiaires);
		model.addAttribute("filieres", filieres);
		
		model.addAttribute("cursus", new Cursus());
	

		return "cursus/form";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		
		List<Stagiaire> stagiaires = utilisateurDao.findAllStagiaires();
		List<Filiere> filieres = filiereDao.findAll();
		
		Optional<Cursus> optCursus = cursusDao.findCursusById(id);

		if (optCursus.isPresent()) {
			model.addAttribute("cursus", optCursus.get());
		}
		
		model.addAttribute("stagiaires", stagiaires);
		model.addAttribute("filieres", filieres);
		
		return "cursus/form";
	}
	
		
	@PostMapping("/save")
	public String save(@ModelAttribute("cursus") @Valid Cursus cursus, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "cursus/form";
		}
		
		cursusDao.save(cursus);

		return "redirect:/cursus";
	}

	
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/cursus";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		cursusDao.deleteById(id);

		return "redirect:/cursus";
	}
}

