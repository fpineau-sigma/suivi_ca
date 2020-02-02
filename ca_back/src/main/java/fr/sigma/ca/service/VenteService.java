package fr.sigma.ca.service;

import fr.sigma.ca.dto.VenteDTO;
import fr.sigma.ca.entite.Commission;
import fr.sigma.ca.entite.Personne;
import fr.sigma.ca.entite.Vente;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.MessageSourceAssistant;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.VenteRepository;
import fr.sigma.ca.service.mapper.VenteMapper;
import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
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

  @Transactional
  public VenteDTO enregistrerVente(Vente vente) {
    ValidationAssistant.valider(vente);
    adresseService.creer(vente.getAdresse());
    vente.setAcquereurs(mettreAJourPersonnes(vente.getAcquereurs()));
    vente.setVendeurs(mettreAJourPersonnes(vente.getVendeurs()));
    vente.setCommissionsEntree(mettreAJourCommissions(vente.getCommissionsEntree()));
    vente.setCommissionsSortie(mettreAJourCommissions(vente.getCommissionsSortie()));
    return mapper.toDto(repository.save(vente));
  }

  @Transactional
  public Collection<VenteDTO> lister() {
    return mapper.toDto(repository.findAll());
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
    vente.setCommissionsEntree(mettreAJourCommissions(vente.getCommissionsEntree()));
    vente.setCommissionsSortie(mettreAJourCommissions(vente.getCommissionsSortie()));
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
  public Collection<Commission> mettreAJourCommissions(Collection<Commission> commissions) {
    Collection<Commission> commissionsAjour = new ArrayList<>();
    commissions.forEach(commission -> {
      commissionsAjour.add(commissionService.mettreAJour(commission));
    });
    return commissionsAjour;
  }
}
