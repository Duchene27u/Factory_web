package sopra.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.formation.model.Competence;

public interface ICompetenceDao extends JpaRepository<Competence, Long> {

}
