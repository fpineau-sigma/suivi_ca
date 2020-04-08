package fr.sigma.ca.service.metier;

import fr.sigma.ca.entite.metier.MontantTypeOrigine;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.MontantTypeOrigineRepository;
import fr.sigma.ca.service.metier.dto.MontantTypeOrigineDTO;
import fr.sigma.ca.service.metier.mapper.MontantTypeOrigineMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MontantTypeOrigineService {

    private final MontantTypeOrigineRepository repository;
    private final MontantTypeOrigineMapper mapper;

    @Transactional
    public MontantTypeOrigineDTO creer(MontantTypeOrigine montantTypeOrigine) {
        ValidationAssistant.valider(montantTypeOrigine);
        return mapper.toDto(repository.save(montantTypeOrigine));
    }

    @Transactional
    public Collection<MontantTypeOrigineDTO> lister() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional
    public MontantTypeOrigineDTO mettreAJour(MontantTypeOrigine montantTypeOrigine) {
        ValidationAssistant.valider(montantTypeOrigine);
        MontantTypeOrigine montantTypeOrigineBdd = repository.findById(montantTypeOrigine.getId())
            .orElseThrow(() -> new BusinessException(""));
        ObjetUtilitaire.merge(montantTypeOrigineBdd, montantTypeOrigine, MontantTypeOrigine.class);
        return mapper.toDto(repository.save(montantTypeOrigineBdd));
    }
}
