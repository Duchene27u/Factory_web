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

import sopra.formation.dao.IMatiereDao;
import sopra.formation.model.Matiere;
import sopra.formation.model.Niveau;
import sopra.formation.validator.MatiereValidator;

@Controller
@RequestMapping("/matiere")
public class MatiereController {

		@Autowired
		private IMatiereDao matiereDao;


		@GetMapping("")
		public String list(Model model) {
//			List<Matiere> matieres = matiereDao.findAllWithCoursAndCompetences();
//			List<Matiere> matieres = matiereDao.findAll();
			List<Matiere> matieresDTOCours = matiereDao.findAllWithCours();
			List<Matiere> matieresDTOCompetence = matiereDao.findAllWithCompetence();
			
			for (Matiere mCours : matieresDTOCours) {
				for (Matiere mComp : matieresDTOCompetence) {
					if (mCours.getId() == mComp.getId()) {
						mCours.setCompetences(mComp.getCompetences());	
					}
				}
			}
			
			model.addAttribute("matieres", matieresDTOCours);

			return "matiere/list";
		}

		@GetMapping("/add")
		public String add(Model model) {
			model.addAttribute("niveaux", Niveau.values());
			model.addAttribute("matiere", new Matiere());

			return "matiere/form";
		}

		@GetMapping("/edit")
		public String edit(@RequestParam Long id, Model model) {
			Optional<Matiere> optMatiere = matiereDao.findById(id);

			if (optMatiere.isPresent()) {
				model.addAttribute("matiere", optMatiere.get());
			}

			model.addAttribute("niveaux", Niveau.values());

			return "matiere/form";
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
		public String saveBis(@Valid @ModelAttribute("Matiere") Matiere matiere, BindingResult result, Model model) {
			new MatiereValidator().validate(matiere, result);
			
			if(result.hasErrors()) {
				model.addAttribute("niveaux", Niveau.values());
				model.addAttribute("matiere", matiere);
				return "matiere/form";

			}
			matiereDao.save(matiere);
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