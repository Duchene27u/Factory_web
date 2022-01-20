package sopra.formation.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.dao.IAbsenceDao;
import sopra.formation.dao.IUtilisateurDao;
import sopra.formation.model.Absence;


@Controller
@RequestMapping("/absence")
public class AbsenceController {
	
	@Autowired
	private IAbsenceDao absenceDao;
	@Autowired
	private IUtilisateurDao utilisateurDao;
	
	@GetMapping("")
	public String list(Model model) {
		List<Absence> absences = absenceDao.findAll();

		model.addAttribute("absences", absences);
		model.addAttribute("formateurs", utilisateurDao.findAllFormateur());
		
		return "absence/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		Absence absence = new Absence();
		model.addAttribute("absence", absence);
		model.addAttribute("formateurs", utilisateurDao.findAllFormateur());
		return "absence/form";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {
		Optional<Absence> absence = absenceDao.findById(id);
		
		if (absence.isPresent()) {
			model.addAttribute("absence",(Absence) absence.get());
			
		}
		model.addAttribute("formateurs", utilisateurDao.findAllFormateur());
		
		return "absence/form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("absence") Absence absence) {
		
		if(absence.getFormateur() != null && absence.getFormateur().getId() == null) {
			absence.setFormateur(null);
		}
		
		absenceDao.save(absence);

		return "redirect:/absence";
	}	
	
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/absence";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		absenceDao.deleteById(id);

		return "redirect:/absence";
	}
}
