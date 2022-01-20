package sopra.formation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sopra.formation.model.Matiere;


public class MatiereValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Matiere.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Matiere matiere = (Matiere) target;

		
	}

}
