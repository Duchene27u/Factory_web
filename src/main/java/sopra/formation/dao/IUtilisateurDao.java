package sopra.formation.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Formateur;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.Utilisateur;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long>, UtilisateurDaoCustom {
	// Trouver un formateur par son email
	Formateur findByEmail(String email);
	
	// Lister les stagiaires en préchargeant les cursus de chacun
	@Query("select distinct s from Stagiaire s left join fetch s.cursus")
	List<Stagiaire> findWithCursus();

	// Lister les formateurs en préchargeant ses cours et matières
	@Query("select distinct f from Formateur f left join fetch f.cours c left join fetch c.matiere m")
	List<Formateur> findWithCoursAndMatiere();

	// Rechercher un formateur par son nom et prénom (paramètres) en préchargeant
	// les cours dans lesquels il intervient (avec les matières)
	@Query("select distinct f from Formateur f left join fetch f.cours c left join fetch c.matiere where f.nom = :nom and f.prenom = :prenom")
	Formateur findFormateurByNomAndPrenomWithCoursAndMatiere(@Param("nom") String nom, @Param("prenom") String prenom);

	// Lister les formateurs disponibles (paramètre : plage de date et nom de
	// matière)
	@Query("select ff from Formateur ff join ff.cours c join c.matiere m where ff not in ("
			+ "select distinct f from Formateur f join f.absences a where a.date >= :dtDebut and a.date <= :dtFin"
			+ ") and m.titre = :nomMatiere")
	List<Formateur> findByDispoAndMatiere(@Param("nomMatiere") String nomMatiere, @Param("dtDebut") LocalDate dtDebut, @Param("dtFin") LocalDate dtFin);
}
