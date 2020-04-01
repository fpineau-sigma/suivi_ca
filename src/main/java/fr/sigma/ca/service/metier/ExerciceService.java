package fr.sigma.ca.service.metier;

import fr.sigma.ca.entite.metier.Exercice;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.ExerciceRepository;
import fr.sigma.ca.service.metier.dto.ExerciceDTO;
import fr.sigma.ca.service.metier.mapper.ExerciceMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExerciceService {

    private final ExerciceRepository repository;
    private final ExerciceMapper mapper;

    @Transactional
    public ExerciceDTO creer(Exercice exercice) {
        ValidationAssistant.valider(exercice);
        return mapper.toDto(repository.save(exercice));
    }

    @Transactional
    public Collection<ExerciceDTO> lister() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional
    public ExerciceDTO mettreAJour(Exercice exercice) {
        ValidationAssistant.valider(exercice);
        Exercice exerciceBdd = repository.findById(exercice.getId())
            .orElseThrow(() -> new BusinessException(""));
        ObjetUtilitaire.merge(exerciceBdd, exercice, Exercice.class);
        return mapper.toDto(repository.save(exerciceBdd));
    }
}
