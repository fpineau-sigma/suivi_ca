package fr.sigma.ca.service.metier;

import fr.sigma.ca.entite.metier.Agence;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.AgenceRepository;
import fr.sigma.ca.service.metier.dto.AgenceDTO;
import fr.sigma.ca.service.metier.mapper.AgenceMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AgenceService {

    private final AgenceRepository repository;
    private final AgenceMapper mapper;

    @Transactional
    public AgenceDTO creer(Agence agence) {
        ValidationAssistant.valider(agence);
        return mapper.toDto(repository.save(agence));
    }

    @Transactional
    public Collection<AgenceDTO> lister() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional
    public AgenceDTO mettreAJour(Agence agence) {
        ValidationAssistant.valider(agence);
        Agence agenceBdd = repository.findById(agence.getId())
            .orElseThrow(() -> new BusinessException(""));
        ObjetUtilitaire.merge(agenceBdd, agence, Agence.class);
        return mapper.toDto(repository.save(agenceBdd));
    }
}
