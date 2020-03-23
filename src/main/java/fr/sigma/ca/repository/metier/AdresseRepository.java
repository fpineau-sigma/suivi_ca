package fr.sigma.ca.repository.metier;

import fr.sigma.ca.domain.metier.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AdresseRepository")
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

}
