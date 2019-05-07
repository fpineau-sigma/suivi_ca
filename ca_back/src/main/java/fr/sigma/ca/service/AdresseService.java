package fr.sigma.ca.service;

import fr.sigma.ca.dto.AdresseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("AdresseService")
@Transactional
public interface AdresseService {

    /**
     * Sauvegarder une nouvelle Adresse
     *
     * @param adresseDTO
     * @return
     */
    UUID enregistrerAdresse(AdresseDTO adresseDTO);

    /**
     * Retourne la liste de toutes les adresses
     *
     * @return
     */
    List<AdresseDTO> findAll();

    /**
     * retourne le nombre d'elements
     *
     * @return
     */
    long countAll();
}
