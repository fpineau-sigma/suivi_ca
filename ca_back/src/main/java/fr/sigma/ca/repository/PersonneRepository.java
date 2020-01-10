package fr.sigma.ca.repository;

import fr.sigma.ca.entite.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("PersonneRepository")
public interface PersonneRepository extends JpaRepository<Personne, UUID> {
}
