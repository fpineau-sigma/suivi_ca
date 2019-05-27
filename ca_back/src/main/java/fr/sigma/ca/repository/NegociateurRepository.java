package fr.sigma.ca.repository;

import fr.sigma.ca.entities.Negociateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("NegociateurRepository")
public interface NegociateurRepository extends JpaRepository<Negociateur, UUID> {
}
