package sopra.formation.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Absence {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	private int version;
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name="formateur_id")
	private Formateur formateur;

	public Absence() {
		super();
	}

	public Absence(LocalDate date, Formateur formateur) {
		super();
		this.date = date;
		this.formateur = formateur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

}
