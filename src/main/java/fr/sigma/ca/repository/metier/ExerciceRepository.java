package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository("ExerciceRepository")
public interface ExerciceRepository extends JpaRepository<Exercice, Long>,
    QuerydslPredicateExecutor<Exercice> {

}
