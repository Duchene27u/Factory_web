package sopra.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.formation.model.Cursus;

public interface ICursusDao extends JpaRepository<Cursus, Long> {

}
