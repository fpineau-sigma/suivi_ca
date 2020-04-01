package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Vente;
import fr.sigma.ca.integration.persistence.FiltreAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository("VenteRepository")
@FiltreAgence
public interface VenteRepository extends JpaRepository<Vente, Long>,
    QuerydslPredicateExecutor<Vente> {

}
