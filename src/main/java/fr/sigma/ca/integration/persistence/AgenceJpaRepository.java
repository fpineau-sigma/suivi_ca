package fr.sigma.ca.integration.persistence;

import java.io.Serializable;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AgenceJpaRepository<T, ID extends Serializable>
    extends JpaRepository<T, ID> {

    /**
     * Ajoute le critère de agence exercice dans toutes les requètes
     */
    void setAgenceCourant();

    /**
     * Supprime le critère de agence exercice
     */
    void unsetAgenceCourant();

    Session getSession();
}
