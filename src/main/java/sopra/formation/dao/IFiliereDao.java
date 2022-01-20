package sopra.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Filiere;

public interface IFiliereDao extends JpaRepository<Filiere, Long> {
	// Lister toutes les filières qui ont eu lieu dans une ville (salle)
	@Query("select f from Filiere f join f.salle s where s.adresse.ville = :ville")
	List<Filiere> findByVille(@Param("ville") String ville);

	// Rechercher une filière par son id en préchargeant la salle, le référent et les cours
	@Query("select distinct f from Filiere f left join fetch f.cours c left join fetch c.matiere where f.id = :id")
	Filiere findByIdWithSalleReferentCours(@Param("id") Long id);
<<<<<<< HEAD

	List<Filiere> findAll();
	
=======
	
	List<Filiere> findAll();
>>>>>>> 9b83f8e0250caf436953e37522b95bf1f3870ed9
	@Query("select distinct f from Filiere f left join fetch f.cours")
	List<Filiere> findAllWithCours(); 
	
	@Query("select distinct f from Filiere f left join fetch f.cursus c")
	List<Filiere> findAllWithCursus();
<<<<<<< HEAD

=======
>>>>>>> 9b83f8e0250caf436953e37522b95bf1f3870ed9
}
