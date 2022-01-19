package sopra.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.formation.model.Absence;

public interface IAbsenceDao extends JpaRepository<Absence, Long> {

}
