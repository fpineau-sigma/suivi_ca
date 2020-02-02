package fr.sigma.ca.repository;

import fr.sigma.ca.entite.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("VenteRepository")
public interface VenteRepository extends JpaRepository<Vente, Long> {

}
