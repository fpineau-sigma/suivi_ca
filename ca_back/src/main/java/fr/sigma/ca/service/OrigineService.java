package fr.sigma.ca.service;

import fr.sigma.ca.dto.OrigineDTO;
import fr.sigma.ca.dto.PersonneDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("OrigineService")
@Transactional
public interface OrigineService {

    /**
     * Sauvegarder une nouvelle origine
     *
     * @param OrigineDTO
     * @return
     */
    UUID enregistrerOrigine(OrigineDTO origineDTO);

    /**
     * Retourne la liste de toutes les origines
     *
     * @return
     */
    List<OrigineDTO> findAll();

    /**
     * retourne le nombre d'elements
     *
     * @return
     */
    long countAll();
}
