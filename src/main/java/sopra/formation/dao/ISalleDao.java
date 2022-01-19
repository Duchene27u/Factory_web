package sopra.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Salle;

public interface ISalleDao extends JpaRepository<Salle, Long> {
	// Lister les salles par ville
	@Query("select s from Salle s where s.adresse.ville = :ville")
	List<Salle> findByVille(@Param("ville") String ville);
}
