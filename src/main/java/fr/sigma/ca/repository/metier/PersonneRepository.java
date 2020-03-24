package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PersonneRepository")
public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
