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
import sopra.formation.dao.IFiliereDao;
import sopra.formation.dao.IMatiereDao;
import sopra.formation.dao.IUtilisateurDao;
import sopra.formation.model.Cours;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;
import sopra.formation.model.Utilisateur;

@Controller
@RequestMapping("/cours")
public class CoursController {

	@Autowired
	private ICoursDao coursDao;
	
	@Autowired
	private IFiliereDao filiereDao;
	
	@Autowired
	private IUtilisateurDao utilisateurDao;
	
	@Autowired
	private IMatiereDao matiereDao;

	@GetMapping("")
	public String list(Model model) {
		List<Cours> cours = coursDao.findAll();

		model.addAttribute("mesCours", cours);

		return "cours/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("cours", new Cours());

		return "cours/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Optional<Cours> optCours = coursDao.findById(id);

		if (optCours.isPresent()) {
			model.addAttribute("cours", optCours.get());
		}
		
		List<Filiere> filieres = filiereDao.findAll();
		model.addAttribute("filieres", filieres);
		
		List<Utilisateur> formateurs = utilisateurDao.findAll();
		model.addAttribute("formateurs", formateurs);
		
		List<Matiere> matieres = matiereDao.findAll();
		model.addAttribute("matieres", matieres);

		return "cours/form";
	}

	
	@PostMapping("/save")
	public String saveBis(@ModelAttribute("cours") @Valid Cours cours, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "cours/form";
		}
		
		coursDao.save(cours);

		return "redirect:/cours";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/cours";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		coursDao.deleteById(id);

		return "redirect:/cours";
	}
}
