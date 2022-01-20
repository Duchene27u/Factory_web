package sopra.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Matiere;

public interface IMatiereDao extends JpaRepository<Matiere, Long> {
//	@Query("select distinct m from Matiere m left join fetch m.cours left join fetch m.competences")
//	List<Matiere> findAllWithCoursAndCompetences();
	@Query("select distinct m from Matiere m left join fetch m.cours")
	List<Matiere> findAllWithCours();
	@Query("select distinct m from Matiere m left join fetch m.competences")
	List<Matiere> findAllWithCompetence();

//	@Query("select distinct m from Matiere m left join fetch m.competences"
//			+ " where m IN :matieresIn")
//	List<Matiere> findAllWithCompetenceKnowingCours(@Param("matieresIn") List<Matiere> matieresIn);
}
