package sopra.formation.web;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormateurController {

	@GetMapping("")
	public String defaut() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(Model model, @RequestParam(required = false) String username) {
		
		model.addAttribute("utilisateur", username);
		model.addAttribute("dtJour", LocalDate.now());
		
		return "home";
	}

}
