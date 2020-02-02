package fr.sigma.ca.repository;

import fr.sigma.ca.entite.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AdresseRepository")
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

}
