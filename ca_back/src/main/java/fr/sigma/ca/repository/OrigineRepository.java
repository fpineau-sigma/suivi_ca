package fr.sigma.ca.repository;

import fr.sigma.ca.entite.Origine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("OrigineRepository")
public interface OrigineRepository extends JpaRepository<Origine, Long> {

}
