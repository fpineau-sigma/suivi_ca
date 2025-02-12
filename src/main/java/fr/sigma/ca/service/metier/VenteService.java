package fr.sigma.ca.service.metier;

import com.querydsl.core.BooleanBuilder;
import fr.sigma.ca.entite.metier.Commission;
import fr.sigma.ca.entite.metier.Objectif;
import fr.sigma.ca.entite.metier.Personne;
import fr.sigma.ca.entite.metier.QVente;
import fr.sigma.ca.entite.metier.Vente;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.MessageSourceAssistant;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.VenteRepository;
import fr.sigma.ca.service.metier.dto.VenteDTO;
import fr.sigma.ca.service.metier.mapper.VenteMapper;
import fr.sigma.ca.web.rest.metier.dto.CriteresRechercheVenteDto;
import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VenteService {

    private final VenteRepository repository;
    private final VenteMapper mapper;
    private final AdresseService adresseService;
    private final PersonneService personneService;
    private final CommissionService commissionService;
    private final ObjectifService objectifService;
    private final MontantTypeOrigineService montantTypeOrigineService;

    @Transactional
    public VenteDTO enregistrerVente(Vente vente) {
        ValidationAssistant.valider(vente);
        adresseService.creer(vente.getAdresse());
        vente.setAcquereurs(mettreAJourPersonnes(vente.getAcquereurs()));
        vente.setVendeurs(mettreAJourPersonnes(vente.getVendeurs()));
        vente.setCommissionsEntree(
            creerCommissions(vente.getCommissionsEntree(), vente.getExerciceId()));
        vente.setCommissionsSortie(
            creerCommissions(vente.getCommissionsSortie(), vente.getExerciceId()));
        ajouterObjectifs(vente);
        return mapper.toDto(repository.save(vente));
    }

    @Transactional
    public Page<VenteDTO> lister(Long exerciceId,
        CriteresRechercheVenteDto criteresRechercheVenteDto,
        Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(QVente.vente.exerciceId.eq(exerciceId));
        if (null != criteresRechercheVenteDto) {
            predicate.and(QVente.vente.commissionsEntree.any().negociateur
                .nomCourt.eq(criteresRechercheVenteDto.getNegociateur().getNomCourt())
                .or(QVente.vente.commissionsSortie.any().negociateur
                    .nomCourt.eq(criteresRechercheVenteDto.getNegociateur().getNomCourt())));
        }
        return repository.findAll(predicate, pageable).map(mapper::toDto);
    }

    @Transactional
    public VenteDTO lire(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new BusinessException(
            MessageSourceAssistant.getMessage("vente.id.introuvable"))));
    }

    @Transactional
    public VenteDTO mettreAJour(Vente vente) {
        ValidationAssistant.valider(vente);
        Vente venteBdd = repository.findById(vente.getId())
            .orElseThrow(() -> new BusinessException(""));
        adresseService.mettreAJour(vente.getAdresse());
        vente.setAcquereurs(mettreAJourPersonnes(vente.getAcquereurs()));
        vente.setVendeurs(mettreAJourPersonnes(vente.getVendeurs()));
        mettreAjourObjectifs(vente, venteBdd);
        vente.setCommissionsEntree(
            mettreAJourCommissions(vente.getCommissionsEntree(), vente.getExerciceId()));
        vente.setCommissionsSortie(
            mettreAJourCommissions(vente.getCommissionsSortie(), vente.getExerciceId()));
        ObjetUtilitaire.merge(venteBdd, vente, Vente.class);
        return mapper.toDto(repository.save(venteBdd));
    }

    @Transactional
    public Collection<Personne> mettreAJourPersonnes(Collection<Personne> personnes) {
        Collection<Personne> personnesAjour = new ArrayList<>();
        personnes.forEach(personne -> {
            personnesAjour.add(personneService.mettreAJour(personne));
        });
        return personnesAjour;
    }

    @Transactional
    public Collection<Commission> creerCommissions(Collection<Commission> commissions,
        Long exerciceId) {
        Collection<Commission> commissionsAjour = new ArrayList<>();
        commissions.forEach(commission -> {
            commission.setExerciceId(exerciceId);
            commissionsAjour.add(commissionService.creer(commission));
        });
        return commissionsAjour;
    }

    @Transactional
    public Collection<Commission> mettreAJourCommissions(Collection<Commission> commissions,
        Long exerciceId) {
        Collection<Commission> commissionsAjour = new ArrayList<>();
        commissions.forEach(commission -> {
            commission.setExerciceId(exerciceId);
            commissionsAjour.add(commissionService.mettreAJour(commission));
        });
        return commissionsAjour;
    }

    private void ajouterObjectifs(Vente vente) {
        Collection<Commission> commissions = new ArrayList<>();
        commissions.addAll(vente.getCommissionsEntree());
        commissions.addAll(vente.getCommissionsSortie());
        commissions.forEach(commission -> {
            commission.setExerciceId(vente.getExerciceId());
            Objectif objectif = objectifService.ajouterObjectif(commission);
            objectifService
                .ajoutMontantTypeOrigine(objectif, vente.getOrigine(), commission.getMontantHT());
        });
    }

    private void mettreAjourObjectifs(Vente vente, Vente venteBdd) {
        Collection<Commission> commissions = new ArrayList<>();
        commissions.addAll(vente.getCommissionsEntree());
        commissions.addAll(vente.getCommissionsSortie());
        commissions.forEach(commission -> {
            commission.setExerciceId(vente.getExerciceId());
            objectifService
                .mettreAjourObjectif(commission, vente.getOrigine(), venteBdd.getOrigine());
        });
    }
}
