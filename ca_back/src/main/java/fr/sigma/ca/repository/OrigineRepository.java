package fr.sigma.ca.repository;

import fr.sigma.ca.domain.Origine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("OrigineRepository")
public interface OrigineRepository extends JpaRepository<Origine, UUID> {
}
