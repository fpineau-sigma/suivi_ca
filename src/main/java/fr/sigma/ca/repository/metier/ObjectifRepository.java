package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Objectif;
import fr.sigma.ca.integration.persistence.FiltreAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ObjectifRepository")
@FiltreAgence
public interface ObjectifRepository extends JpaRepository<Objectif, Long> {

}
