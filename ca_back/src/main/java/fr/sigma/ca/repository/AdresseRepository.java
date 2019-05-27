package fr.sigma.ca.repository;

import fr.sigma.ca.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("AdresseRepository")
public interface AdresseRepository extends JpaRepository<Adresse, UUID> {

}
