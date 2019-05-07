package fr.sigma.ca.service;

        import fr.sigma.ca.dto.NegociateurDTO;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.UUID;

@Service("NegociateurService")
@Transactional
public interface NegociateurService {

        /**
         * Sauvegarder un nouveau sprint
         *
         * @param negociateurDTO
         * @return
         */
        UUID enregistrerNegociateur(NegociateurDTO negociateurDTO);

        /**
         * Retourne la liste de tous les sprints
         *
         * @return
         */
        List<NegociateurDTO> findAll();

        /**
         * retourne le nombre d'elements
         *
         * @return
         */
        long countAll();
}
