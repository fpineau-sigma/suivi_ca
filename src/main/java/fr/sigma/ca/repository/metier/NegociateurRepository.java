package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Negociateur;
import fr.sigma.ca.integration.persistence.FiltreAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("NegociateurRepository")
@FiltreAgence
public interface NegociateurRepository extends JpaRepository<Negociateur, Long> {

}
