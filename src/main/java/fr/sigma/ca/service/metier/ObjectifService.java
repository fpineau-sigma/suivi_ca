package fr.sigma.ca.service.metier;

import fr.sigma.ca.entite.metier.Objectif;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.ObjectifRepository;
import fr.sigma.ca.service.metier.mapper.ObjectifMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ObjectifService {

    private final ObjectifRepository repository;
    private final ObjectifMapper mapper;

    @Transactional
    public Objectif creer(Objectif objectif) {
        ValidationAssistant.valider(objectif);
        return repository.save(objectif);
    }

    @Transactional
    public Objectif mettreAJour(Objectif objectif) {
        ValidationAssistant.valider(objectif);
        if (null == objectif.getId()) {
            return repository.save(objectif);
        } else {
            Objectif objectifBdd = repository.findById(objectif.getId())
                .orElseThrow(() -> new BusinessException(""));
            ObjetUtilitaire.merge(objectifBdd, objectif, Objectif.class);
            return repository.save(objectifBdd);
        }
    }
}
