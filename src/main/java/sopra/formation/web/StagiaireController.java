package sopra.formation.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.dao.IUtilisateurDao;
import sopra.formation.model.Adresse;
import sopra.formation.model.Droit;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.Utilisateur;


@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {
	
	@Autowired
	private IUtilisateurDao utilisateurDao;
	
	@GetMapping("")
	public String list(Model model) {
		List<Utilisateur> stagiaires = utilisateurDao.findAllStagiaires();

		model.addAttribute("stagiaires", stagiaires);

		return "stagiaire/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("stagiaire", new Utilisateur());

		return "stagiaire/form";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Optional<Utilisateur> optUtilisateur = utilisateurDao.findStagiaireById(id);

		if (optUtilisateur.isPresent()) {
			model.addAttribute("stagiaire", optUtilisateur.get());
		}


		return "stagiaire/form";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id,
			@RequestParam(required = false, defaultValue = "0") int version,
			@RequestParam String nom, @RequestParam String prenom, 
			@RequestParam String email, @RequestParam Droit droit, 
			@RequestParam String telephone,
			@RequestParam String identifiant, @RequestParam String motDePasse,
			@RequestParam(required = false, defaultValue = "0") int age, @RequestParam(name="adresse.rue") String rue,
			@RequestParam(name="adresse.complement") String complement, @RequestParam(name="adresse.codePostal") String codePostal, @RequestParam(name="adresse.ville") String ville,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateNaissance) {
		
		Stagiaire stagiaire = new Stagiaire();
		stagiaire.setId(id);
		stagiaire.setVersion(version);
		stagiaire.setNom(nom);
		stagiaire.setPrenom(prenom);
		stagiaire.setEmail(email);
		stagiaire.setDroit(droit);
		stagiaire.setTelephone(telephone);
		stagiaire.setIdentifiant(identifiant);
		stagiaire.setMotDePasse(motDePasse);
		stagiaire.setAdr(new Adresse(rue, complement, codePostal, ville));
		
		stagiaire.setDateNaissance(dateNaissance);

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
