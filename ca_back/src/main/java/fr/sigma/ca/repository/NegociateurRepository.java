package fr.sigma.ca.repository;

import fr.sigma.ca.entite.Negociateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("NegociateurRepository")
public interface NegociateurRepository extends JpaRepository<Negociateur, Long> {

}
