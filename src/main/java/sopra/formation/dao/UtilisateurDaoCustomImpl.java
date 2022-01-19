package sopra.formation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import sopra.formation.model.Stagiaire;

public class UtilisateurDaoCustomImpl implements UtilisateurDaoCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Stagiaire> searchStagiaire(String nom, String prenom) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Stagiaire> cq = cb.createQuery(Stagiaire.class);

		Root<Stagiaire> root = cq.from(Stagiaire.class);

		cq.select(root);

		Predicate predicatFinal = cb.equal(cb.literal(1), 1);

		if (nom != null && !nom.isBlank()) {
			predicatFinal = cb.and(predicatFinal, cb.equal(root.get("nom"), nom));
		}

		if (prenom != null && !prenom.isBlank()) {
			predicatFinal = cb.and(predicatFinal, cb.equal(root.get("prenom"), prenom));
		}

		cq.where(predicatFinal);

		TypedQuery<Stagiaire> query = em.createQuery(cq);

		return query.getResultList();
	}

}
