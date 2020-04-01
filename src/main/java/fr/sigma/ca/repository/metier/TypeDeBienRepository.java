package fr.sigma.ca.repository.metier;

import fr.sigma.ca.entite.metier.TypeDeBien;
import fr.sigma.ca.integration.persistence.FiltreAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("TypeDeBienRepository")
@FiltreAgence
public interface TypeDeBienRepository extends JpaRepository<TypeDeBien, Long> {

}
