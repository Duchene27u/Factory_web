package sopra.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.formation.model.Matiere;

public interface IMatiereDao extends JpaRepository<Matiere, Long> {

}
