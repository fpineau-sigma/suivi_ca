package fr.sigma.ca.service;

import fr.sigma.ca.dto.CommissionDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("CommissionService")
@Transactional
public interface CommissionService {

    /**
     * Sauvegarder une nouvelle Commission
     *
     * @param commissionDTO
     * @return
     */
    UUID enregistrerCommission(CommissionDTO commissionDTO);

    /**
     * Retourne la liste de toutes les commissions
     *
     * @return
     */
    List<CommissionDTO> findAll();

    /**
     * retourne le nombre d'elements
     *
     * @return
     */
    long countAll();

    /**
     * Retourne la liste de toutes les commissions
     *
     * @return
     */
    List<CommissionDTO> find(String nomCourt);

    /**
     * Retourne la liste de toutes les commissions
     *
     * @return
     */
    List<CommissionDTO> find(Date date);

    /**
     * Retourne la liste de toutes les commissions
     *
     * @return
     */
    List<CommissionDTO> find(String nomCourt, Date date);


}
