package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Cursus {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	private int version;
	@ManyToOne
	@JoinColumn(name = "stagiaire_id")
	private Stagiaire stagiaire;
	@ManyToOne
	@JoinColumn(name = "filiere_id")
	private Filiere filiere;
	private int noteC;
	@Column(length = 4000)
	private String commentairesC;
	private int noteT;
	@Column(length = 4000)
	private String commentairesT;

	public Cursus() {
		super();
	}

	public Cursus(Stagiaire stagiaire, Filiere filiere) {
		super();
		this.stagiaire = stagiaire;
		this.filiere = filiere;
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

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public int getNoteC() {
		return noteC;
	}

	public void setNoteC(int noteC) {
		this.noteC = noteC;
	}

	public String getCommentairesC() {
		return commentairesC;
	}

	public void setCommentairesC(String commentairesC) {
		this.commentairesC = commentairesC;
	}

	public int getNoteT() {
		return noteT;
	}

	public void setNoteT(int noteT) {
		this.noteT = noteT;
	}

	public String getCommentairesT() {
		return commentairesT;
	}

	public void setCommentairesT(String commentairesT) {
		this.commentairesT = commentairesT;
	}

}
