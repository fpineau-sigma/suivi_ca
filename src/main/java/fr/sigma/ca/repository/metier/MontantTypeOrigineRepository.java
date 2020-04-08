package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.MontantTypeOrigine;
import fr.sigma.ca.integration.persistence.FiltreAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository("MontantTypeOrigineRepository")
@FiltreAgence
public interface MontantTypeOrigineRepository extends JpaRepository<MontantTypeOrigine, Long>,
    QuerydslPredicateExecutor<MontantTypeOrigine> {

}
