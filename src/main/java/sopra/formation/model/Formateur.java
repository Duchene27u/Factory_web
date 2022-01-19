package sopra.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Formateur")
public class Formateur extends Utilisateur {
	@OneToMany(mappedBy = "formateur")
	private List<Absence> absences = new ArrayList<>();
	@OneToMany(mappedBy = "referent")
	private List<Filiere> filieres = new ArrayList<>();
	@OneToMany(mappedBy = "formateur")
	private List<Cours> cours = new ArrayList<>();
	@OneToMany(mappedBy = "formateur")
	private List<Competence> competences = new ArrayList<>();

	public Formateur() {
		super();
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

}
