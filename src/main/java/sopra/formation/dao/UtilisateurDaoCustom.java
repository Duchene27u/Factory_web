package sopra.formation.dao;

import java.util.List;

import sopra.formation.model.Stagiaire;

public interface UtilisateurDaoCustom {
	// Rechercher des stagiaires : soit par nom et/ou prénom
	List<Stagiaire> searchStagiaire(String nom, String prenom);
}
