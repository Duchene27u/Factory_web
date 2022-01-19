package sopra.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Cours;

public interface ICoursDao extends JpaRepository<Cours, Long> {
	// Lister les cours d'une filière (paramètre) qui ont durée différente de la
	// durée standard de la matière
	@Query("select c from Cours c join c.filiere f join c.matiere m where f.id = :idFiliere and c.duree != m.duree")
	List<Cours> findDifferentByFiliere(@Param("idFiliere") String id);

	// Lister les cours d'une filière (paramètre) en préchargeant la matière, le
	// formateur
	@Query("select c from Cours c join c.filiere f where f.id = :idFiliere")
	List<Cours> findByFiliereWithMatiereAndFormateur(@Param("idFiliere") String id);
}
