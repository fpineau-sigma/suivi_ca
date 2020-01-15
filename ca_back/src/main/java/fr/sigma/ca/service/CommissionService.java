package fr.sigma.ca.service;

import fr.sigma.ca.entite.Commission;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.CommissionRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommissionService {

  private final CommissionRepository repository;

  @Transactional
  public Commission enregistrerCommission(Commission commission) {
    ValidationAssistant.valider(commission);
    return repository.save(commission);
  }

  @Transactional
  public Collection<Commission> lister() {
    return repository.findAll();
  }

  @Transactional
  public Collection<Commission> trouverParNegociateur(String nomCourt) {
    return repository.findByNegociateur_nomCourt(nomCourt);
  }
}
