package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Adresse;
import fr.sigma.ca.integration.persistence.FiltreAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AdresseRepository")
@FiltreAgence
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

}
