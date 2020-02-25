package fr.sigma.ca.repository;

import fr.sigma.ca.entite.TypeDeBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("TypeDeBienRepository")
public interface TypeDeBienRepository extends JpaRepository<TypeDeBien, Long> {

}
