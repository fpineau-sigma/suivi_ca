package fr.sigma.ca.service;

import fr.sigma.ca.dto.PersonneDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("PersonneService")
@Transactional
public interface PersonneService {

    /**
     * Sauvegarder un nouveau sprint
     *
     * @param personneDTO
     * @return
     */
    UUID enregistrerPersonne(PersonneDTO personneDTO);

    /**
     * Retourne la liste de tous les sprints
     *
     * @return
     */
    List<PersonneDTO> findAll();

    /**
     * retourne le nombre d'elements
     *
     * @return
     */
    long countAll();
}
