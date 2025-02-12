package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Commission;
import fr.sigma.ca.integration.persistence.FiltreAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository("CommissionRepository")
@FiltreAgence
public interface CommissionRepository extends JpaRepository<Commission, Long>,
    QuerydslPredicateExecutor<Commission> {

}
