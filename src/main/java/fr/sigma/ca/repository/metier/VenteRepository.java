package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository("VenteRepository")
public interface VenteRepository extends JpaRepository<Vente, Long>,
    QuerydslPredicateExecutor<Vente> {

}
