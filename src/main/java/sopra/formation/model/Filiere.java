package sopra.formation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Filiere {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	private int version;
	private LocalDate dateDebut;
	private int duree;
	@Enumerated(EnumType.STRING)
	private Dispositif dispositif;
	@ManyToOne
	@JoinColumn(name = "gestionnaire_id")
	private Utilisateur gestionnaire;
	@ManyToOne
	@JoinColumn(name = "referent_id")
	private Formateur referent;
	@ManyToOne
	@JoinColumn(name = "salle_id")
	private Salle salle;
	@OneToMany(mappedBy = "filiere")
	private List<Cursus> cursus = new ArrayList<>();
	@OneToMany(mappedBy = "filiere")
	private List<Cours> cours = new ArrayList<>();

	public Filiere() {
		super();
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

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Dispositif getDispositif() {
		return dispositif;
	}

	public void setDispositif(Dispositif dispositif) {
		this.dispositif = dispositif;
	}

	public Utilisateur getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Utilisateur gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}

	public Formateur getReferent() {
		return referent;
	}

	public void setReferent(Formateur referent) {
		this.referent = referent;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

}
