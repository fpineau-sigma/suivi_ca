package fr.sigma.ca.service.metier;

import fr.sigma.ca.entite.metier.Commission;
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
        if (!CollectionUtils.isEmpty(negociateur.getObjectifs())) {
            negociateur.setObjectifs(mettreAJourObjectifs(negociateur.getObjectifs()));
        }
        return repository.save(negociateur);
    }

    @Transactional
    public Negociateur mettreAJour(Negociateur negociateur) {
        ValidationAssistant.valider(negociateur);
        Negociateur negociateurBdd = repository.findById(negociateur.getId())
            .orElseThrow(() -> new BusinessException(""));
        if (!CollectionUtils.isEmpty(negociateur.getObjectifs())) {
            negociateur.setObjectifs(mettreAJourObjectifs(negociateur.getObjectifs()));
        }
        ObjetUtilitaire.merge(negociateurBdd, negociateur, Negociateur.class);
        return repository.save(negociateurBdd);
    }

    @Transactional
    public Negociateur miseAjourObjectifRealise(Commission commission, Long exerciceId) {
        Negociateur negociateurBdd = this.repository.findById(commission.getNegociateur().getId())
            .orElseThrow(() -> new BusinessException(""));
        negociateurBdd.getObjectifs().stream()
            .filter(objectifDTO -> objectifDTO.getExerciceId().equals(exerciceId))
            .findFirst().ifPresent(objectifDTOExercice -> {
            objectifDTOExercice.setRealise(objectifDTOExercice.getRealise()
                .add(commission.getMontantHT()));
        });
        return repository.save(negociateurBdd);
    }

    @Transactional
    public List<Negociateur> lister() {
        return repository.findAll();
    }

    @Transactional
    public Collection<Objectif> mettreAJourObjectifs(Collection<Objectif> objectifs) {
        Collection<Objectif> objectifsAjour = new ArrayList<>();
        objectifs.forEach(objectif -> {
            objectifsAjour.add(objectifService.mettreAJour(objectif));
        });
        return objectifsAjour;
    }
}
