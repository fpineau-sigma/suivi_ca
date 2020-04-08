package fr.sigma.ca.service.metier;

import com.querydsl.core.BooleanBuilder;
import fr.sigma.ca.entite.metier.Commission;
import fr.sigma.ca.entite.metier.MontantTypeOrigine;
import fr.sigma.ca.entite.metier.Negociateur;
import fr.sigma.ca.entite.metier.Objectif;
import fr.sigma.ca.entite.metier.Origine;
import fr.sigma.ca.entite.metier.QObjectif;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.CommissionRepository;
import fr.sigma.ca.repository.metier.NegociateurRepository;
import fr.sigma.ca.repository.metier.ObjectifRepository;
import fr.sigma.ca.service.metier.dto.ObjectifDTO;
import fr.sigma.ca.service.metier.mapper.ObjectifMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class ObjectifService {

    private final ObjectifRepository repository;
    private final ObjectifMapper mapper;
    private final NegociateurRepository negociateurRepository;
    private final CommissionRepository commissionRepository;

    @Transactional
    public Objectif creer(Objectif objectif) {
        ValidationAssistant.valider(objectif);
        return repository.save(objectif);
    }

    @Transactional
    public List<ObjectifDTO> lister(Long exerciceId) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(QObjectif.objectif.exerciceId.eq(exerciceId));
        return mapper.toDto(StreamSupport
            .stream(repository.findAll(predicate).spliterator(), false)
            .collect(Collectors.toList()));
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

    @Transactional
    public Objectif ajouterObjectif(Commission commission) {
        Negociateur negociateurBdd = this.negociateurRepository
            .findById(commission.getNegociateur().getId())
            .orElseThrow(() -> new BusinessException(""));

        Optional<Objectif> objectifEnCours = negociateurBdd.getObjectifs().stream()
            .filter(
                objectif -> objectif.getExerciceId().equals(commission.getExerciceId()))
            .findFirst();
        if (objectifEnCours.isPresent()) {
            Objectif obj = objectifEnCours.get();
            obj.setRealise(obj.getRealise()
                .add(commission.getMontantHT()));
            return repository.save(obj);
        } else {
            Objectif obj = new Objectif();
            obj.setNegociateur(negociateurBdd);
            obj.setRealise(commission.getMontantHT());
            obj.setExerciceId(commission.getExerciceId());
            return repository.save(obj);
        }
    }

    @Transactional
    public void mettreAjourObjectif(Commission commission, Origine origine, Origine origineBdd) {
        // On soustrait l'ancient montant
        commissionRepository.findById(commission.getId()).ifPresent(commissionBdd -> {
            Negociateur negociateurBdd = this.negociateurRepository
                .findById(commissionBdd.getNegociateur().getId())
                .orElseThrow(() -> new BusinessException(""));
            negociateurBdd.getObjectifs().stream()
                .filter(
                    objectif -> objectif.getExerciceId().equals(commissionBdd.getExerciceId()))
                .findFirst().ifPresent(objectif -> {
                objectif.setRealise(objectif.getRealise().subtract(commissionBdd.getMontantHT()));
                objectif = repository.save(objectif);
                // Soustraction a faire avec l'ancienne vente..
                objectif.getMontantTypeOrigines().stream()
                    .filter(
                        montantTypeOrigine -> montantTypeOrigine.getTypeOrigine()
                            .equals(origineBdd.getTypeOrigine())).findFirst()
                    .ifPresent(montantTypeOrigine -> {
                        montantTypeOrigine
                            .setMontant(montantTypeOrigine.getMontant()
                                .subtract(commissionBdd.getMontantHT()));
                    });
            });
        });
        // Et on ajoute le nouveu montant
        Objectif objectif = this.ajouterObjectif(commission);

        ajoutMontantTypeOrigine(objectif, origine, commission.getMontantHT());
    }

    public void ajoutMontantTypeOrigine(Objectif objectifExercice, Origine origine,
        BigDecimal montant) {
        if (!CollectionUtils.isEmpty(objectifExercice.getMontantTypeOrigines())) {
            Optional<MontantTypeOrigine> mnt = objectifExercice.getMontantTypeOrigines().stream()
                .filter(
                    montantTypeOrigine -> montantTypeOrigine.getTypeOrigine()
                        .equals(origine.getTypeOrigine())).findFirst();
            if (mnt.isPresent()) {
                MontantTypeOrigine montantTypeOrigineTrouve = mnt.get();
                montantTypeOrigineTrouve
                    .setMontant(montantTypeOrigineTrouve.getMontant().add(montant));
            } else {
                MontantTypeOrigine montantTypeOrigine = new MontantTypeOrigine();
                montantTypeOrigine.setObjectif(objectifExercice);
                montantTypeOrigine.setTypeOrigine(origine.getTypeOrigine());
                montantTypeOrigine.setExerciceId(objectifExercice.getExerciceId());
                montantTypeOrigine.setMontant(montant);
                objectifExercice.getMontantTypeOrigines().add(montantTypeOrigine);
            }
        } else {
            objectifExercice.setMontantTypeOrigines(new ArrayList<>());
            MontantTypeOrigine montantTypeOrigine = new MontantTypeOrigine();
            montantTypeOrigine.setObjectif(objectifExercice);
            montantTypeOrigine.setTypeOrigine(origine.getTypeOrigine());
            montantTypeOrigine.setExerciceId(objectifExercice.getExerciceId());
            montantTypeOrigine.setMontant(montant);
            objectifExercice.getMontantTypeOrigines().add(montantTypeOrigine);
        }
    }
}
