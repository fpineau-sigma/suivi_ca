package fr.sigma.ca.repository.metier;

import fr.sigma.ca.domain.metier.TypeDeBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("TypeDeBienRepository")
public interface TypeDeBienRepository extends JpaRepository<TypeDeBien, Long> {

}
