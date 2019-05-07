package fr.sigma.ca.repository;

import fr.sigma.ca.domain.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("VenteRepository")
public interface VenteRepository extends JpaRepository<Vente, UUID> {
}
