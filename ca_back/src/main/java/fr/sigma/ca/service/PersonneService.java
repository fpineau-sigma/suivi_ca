package fr.sigma.ca.service;

import fr.sigma.ca.entite.Personne;
import fr.sigma.ca.integration.exception.BusinessException;
import fr.sigma.ca.integration.utilitaire.ObjetUtilitaire;
import fr.sigma.ca.integration.utilitaire.ValidationAssistant;
import fr.sigma.ca.repository.PersonneRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonneService {

  private final PersonneRepository repository;

  @Transactional
  public Personne creer(Personne personne) {
    ValidationAssistant.valider(personne);
    return repository.save(personne);
  }

  @Transactional
  public Collection<Personne> lister() {
    return repository.findAll();
  }

  @Transactional
  public Personne mettreAJour(Personne personne) {
    ValidationAssistant.valider(personne);
    if (null == personne.getId()) {
      return repository.save(personne);
    } else {
      Personne personneBdd = repository.findById(personne.getId())
          .orElseThrow(() -> new BusinessException(""));
      ObjetUtilitaire.merge(personneBdd, personne, Personne.class);
      return repository.save(personneBdd);
    }
  }


}
