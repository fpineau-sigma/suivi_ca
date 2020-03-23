package fr.sigma.ca.repository.metier;

import fr.sigma.ca.domain.metier.Origine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("OrigineRepository")
public interface OrigineRepository extends JpaRepository<Origine, Long> {

}
