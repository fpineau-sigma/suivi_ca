package fr.sigma.ca.service.metier;

import fr.sigma.ca.domain.metier.Commission;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.metier.CommissionRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommissionService {

  private final CommissionRepository repository;

  @Transactional
  public Commission creer(Commission commission) {
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

  @Transactional
  public Commission mettreAJour(Commission commission) {
    ValidationAssistant.valider(commission);
    if (null == commission.getId()) {
      return repository.save(commission);
    } else {
      Commission commissionBdd = repository.findById(commission.getId())
          .orElseThrow(() -> new BusinessException(""));
      ObjetUtilitaire.merge(commissionBdd, commission, Commission.class);
      return repository.save(commissionBdd);
    }
  }

}
