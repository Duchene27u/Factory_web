package sopra.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Cursus;

public interface ICursusDao extends JpaRepository<Cursus, Long> {

	@Query("select c from Cursus where c.id = :id")
	Cursus findCursusById(@Param("id") Long Id);
}
