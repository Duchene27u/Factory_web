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

import sopra.formation.dao.ICompetenceDao;
import sopra.formation.dao.ICoursDao;
import sopra.formation.dao.IMatiereDao;
import sopra.formation.model.Matiere;
import sopra.formation.model.Competence;
import sopra.formation.model.Cours;
import sopra.formation.model.Niveau;

@Controller
@RequestMapping("/matiere")
public class MatiereController {

		@Autowired
		private IMatiereDao matiereDao;
		@Autowired
		private ICoursDao coursDao;
		@Autowired
		private ICompetenceDao competenceDao;


		@GetMapping("")
		public String list(Model model) {
//			List<Matiere> matieres = matiereDao.findAllWithCoursAndCompetences();
			List<Matiere> matieres = matiereDao.findAll();

			model.addAttribute("matieres", matieres);

			return "matiere/list";
		}

		@GetMapping("/add")
		public String add(Model model) {
			model.addAttribute("niveaux", Niveau.values());
			List<Competence> competences = competenceDao.findAll();
			List<Cours> coursz = coursDao.findAll();
			model.addAttribute("competences", competences);
			model.addAttribute("cours", coursz);
			model.addAttribute("matiere", new Matiere());

			return "Matiere/form";
		}

		@GetMapping("/edit")
		public String edit(@RequestParam Long id, Model model) {
			Optional<Matiere> optMatiere = matiereDao.findById(id);

			if (optMatiere.isPresent()) {
				model.addAttribute("matiere", optMatiere.get());
			}

			model.addAttribute("niveaux", Niveau.values());

			return "Matiere/form";
		}

//		@PostMapping("/save")
//		public String save(@RequestParam(required = false) Long id,
//				@RequestParam(required = false, defaultValue = "0") int version,
//				@RequestParam(required = false) Civilite civilite, @RequestParam String nom, @RequestParam String prenom,
//				@RequestParam(required = false, defaultValue = "0") int age, @RequestParam(name="adresse.rue") String rue,
//				@RequestParam(name="adresse.complement") String complement, @RequestParam(name="adresse.codePostal") String codePostal, @RequestParam(name="adresse.ville") String ville,
//				@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateNaissance) {
//			Matiere Matiere = new Matiere();
//			Matiere.setId(id);
//			Matiere.setVersion(version);
//			Matiere.setCivilite(civilite);
//			Matiere.setNom(nom);
//			Matiere.setPrenom(prenom);
//			Matiere.setAdresse(new Adresse(rue, complement, codePostal, ville));
//			
//			Matiere.setAge(age);
//			Matiere.setDateNaissance(dateNaissance);
	//
//			MatiereDao.save(Matiere);
	//
//			return "redirect:/Matiere";
//		}
		
		@PostMapping("/saveBis")
		public String saveBis(@ModelAttribute("Matiere") @Valid Matiere Matiere, BindingResult result, Model model) {
			matiereDao.save(Matiere);

			return "redirect:/matiere";
		}

		@GetMapping("/cancel")
		public String cancel() {
			return "forward:/matiere";
		}

		@GetMapping("/delete")
		public String delete(@RequestParam Long id) {
			matiereDao.deleteById(id);

			return "redirect:/matiere";
		}
	}