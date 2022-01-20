package sopra.formation.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.dao.ICompetenceDao;
import sopra.formation.dao.IMatiereDao;
import sopra.formation.dao.IUtilisateurDao;
import sopra.formation.model.Adresse;
import sopra.formation.model.Competence;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;
import sopra.formation.model.Niveau;

@Controller
@RequestMapping("/competence")
public class CompetenceController {

	@Autowired
	private ICompetenceDao CompetenceDao;
	@Autowired
	private IMatiereDao MatiereDao;
	@Autowired
	private IUtilisateurDao UtilisateurDao;


	@GetMapping("")
	public String list(Model model) {
		List<Competence> competences = CompetenceDao.findAll();
		model.addAttribute("competences", competences);

		return "competence/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("niveaux", Niveau.values());
		List<Matiere> matieres = MatiereDao.findAll();
		List<Formateur> formateurs = UtilisateurDao.findAllFormateur();
		model.addAttribute("matieres", matieres);
		model.addAttribute("formateurs", formateurs);
		model.addAttribute("competence", new Competence());

		return "competence/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Optional<Competence> optcompetence = CompetenceDao.findById(id);

		if (optcompetence.isPresent()) {
			model.addAttribute("competence", optcompetence.get());
		}

		model.addAttribute("niveaux", Niveau.values());

		return "competence/form";
	}

//	@PostMapping("/save")
//	public String save(@RequestParam(required = false) Long id,
//			@RequestParam(required = false, defaultValue = "0") int version,
//			@RequestParam(required = false) Civilite civilite, @RequestParam String nom, @RequestParam String prenom,
//			@RequestParam(required = false, defaultValue = "0") int age, @RequestParam(name="adresse.rue") String rue,
//			@RequestParam(name="adresse.complement") String complement, @RequestParam(name="adresse.codePostal") String codePostal, @RequestParam(name="adresse.ville") String ville,
//			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateNaissance) {
//		Competence competence = new Competence();
//		competence.setId(id);
//		competence.setVersion(version);
//		competence.setCivilite(civilite);
//		competence.setNom(nom);
//		competence.setPrenom(prenom);
//		competence.setAdresse(new Adresse(rue, complement, codePostal, ville));
//		
//		competence.setAge(age);
//		competence.setDateNaissance(dateNaissance);
//
//		CompetenceDao.save(competence);
//
//		return "redirect:/competence";
//	}
	
	@PostMapping("/saveBis")
	public String saveBis(@ModelAttribute("competence") @Valid Competence competence, BindingResult result, Model model) {
		CompetenceDao.save(competence);

		return "redirect:/competence";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/competence";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		CompetenceDao.deleteById(id);

		return "redirect:/competence";
	}
}
