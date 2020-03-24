package fr.sigma.ca.service.metier;

import fr.sigma.ca.entite.metier.Adresse;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.AdresseRepository;
import fr.sigma.ca.service.metier.dto.AdresseDTO;
import fr.sigma.ca.service.metier.mapper.AdresseMapper;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdresseService {

    private final AdresseRepository repository;
    private final AdresseMapper mapper;

    @Transactional
    public Adresse creer(Adresse adresse) {
        ValidationAssistant.valider(adresse);
        return repository.save(adresse);
    }

    @Transactional
    public Collection<Adresse> lister() {
        return repository.findAll();
    }

    @Transactional
    public AdresseDTO mettreAJour(Adresse adresse) {
        ValidationAssistant.valider(adresse);
        Adresse adresseBdd = repository.findById(adresse.getId())
            .orElseThrow(() -> new BusinessException(""));
        ObjetUtilitaire.merge(adresseBdd, adresse, Adresse.class);
        return mapper.toDto(repository.save(adresseBdd));
    }
}
