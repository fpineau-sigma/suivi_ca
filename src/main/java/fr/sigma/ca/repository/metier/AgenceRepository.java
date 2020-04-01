package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository("AgenceRepository")
public interface AgenceRepository extends JpaRepository<Agence, Long>,
    QuerydslPredicateExecutor<Agence> {

}
