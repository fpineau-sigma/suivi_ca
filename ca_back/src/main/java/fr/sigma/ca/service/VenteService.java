package fr.sigma.ca.service;

import fr.sigma.ca.dto.VenteDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("VenteService")
@Transactional
public interface VenteService {
    /**
     * Sauvegarder un nouveau sprint
     *
     * @param venteDTO
     * @return
     */
    UUID enregistrerVente(VenteDTO venteDTO);

    /**
     * Retourne la liste de tous les sprints
     *
     * @return
     */
    List<VenteDTO> findAll();

    /**
     * retourne le nombre d'elements
     *
     * @return
     */
    long countAll();
}
