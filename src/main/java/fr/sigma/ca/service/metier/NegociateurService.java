package fr.sigma.ca.service.metier;

import fr.sigma.ca.entite.metier.Negociateur;
import fr.sigma.ca.entite.metier.Objectif;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.NegociateurRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class NegociateurService {

    private final NegociateurRepository repository;
    private final ObjectifService objectifService;

    @Transactional
    public Negociateur creer(Negociateur negociateur) {
        ValidationAssistant.valider(negociateur);
        Negociateur negociateurBDD = repository.save(negociateur);
        // Obligatoire car idchild non setté lors du premier save : https://github.com/jhipster/generator-jhipster/issues/9639
        if (!CollectionUtils.isEmpty(negociateur.getObjectifs())) {
            negociateur
                .setObjectifs(mettreAJourObjectifs(negociateurBDD, negociateur.getObjectifs()));
        }
        return negociateurBDD;
    }

    @Transactional
    public Negociateur mettreAJour(Negociateur negociateur) {
        ValidationAssistant.valider(negociateur);
        Negociateur negociateurBdd = repository.findById(negociateur.getId())
            .orElseThrow(() -> new BusinessException(""));
        if (!CollectionUtils.isEmpty(negociateur.getObjectifs())) {
            negociateur.setObjectifs(mettreAJourObjectifs(negociateur, negociateur.getObjectifs()));
        }
        ObjetUtilitaire.merge(negociateurBdd, negociateur, Negociateur.class);
        return repository.save(negociateurBdd);
    }


    @Transactional
    public List<Negociateur> lister() {
        return repository.findAll();
    }

    @Transactional
    public Collection<Objectif> mettreAJourObjectifs(Negociateur negociateur,
        Collection<Objectif> objectifs) {
        Collection<Objectif> objectifsAjour = new ArrayList<>();
        objectifs.forEach(objectif -> {
            objectif.setNegociateur(negociateur);
            objectifsAjour.add(objectifService.mettreAJour(objectif));
        });
        return objectifsAjour;
    }
}
