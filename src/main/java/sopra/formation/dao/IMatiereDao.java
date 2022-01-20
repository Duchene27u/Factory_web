package sopra.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sopra.formation.model.Matiere;

public interface IMatiereDao extends JpaRepository<Matiere, Long> {
//	@Query("select distinct m from Matiere m left join fetch m.cours left join fetch m.competences")
//	List<Matiere> findAllWithCoursAndCompetences();
}
